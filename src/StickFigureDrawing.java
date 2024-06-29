import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StickFigureDrawing extends JFrame {
    private final JButton restartButton; /////////////////////////////////////////////////
    private int step = 0;   //für step counter umd nach fehlern zu malen
    private JTextField letterInputField; // Eingabefeld für den Buchstaben
    private JTextArea feedbackArea;
    private Game game;

    public StickFigureDrawing() //fenster specs + button
    { /////Willkommen bei HangmanAI
        setTitle("HangmanAI");    //game fenster Titel
        setSize(1600, 900);   //Fester Größe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //nur übergangsweise bis initialisiert das bei False count +1
        DrawPanel drawPanel = new DrawPanel();
        JButton nextButton = new JButton("Spiel Starten?");
        nextButton.setBounds(720, 150, 180, 40);
        restartButton = new JButton("-> Neues Spiel <-");
        restartButton.setVisible(false); ///////////////////////////////////////////

        //neues Game erstellen
        this.game = new Game();


// Eingabefeld und Feedback-Bereich initialisieren
        letterInputField = new JTextField(10);
        feedbackArea = new JTextArea();
        feedbackArea.setEditable(false);

        nextButton.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             String input = letterInputField.getText();
                                             game.tryInput(input); // Verwenden der tryInput-Methode der Game-Klasse

                                             step = game.getState(); // Aktualisieren des step-Werts basierend auf dem Zustand des Spiels

                                             if (step == 12 || step == 13) {
                                                 restartButton.setVisible(true);
                                             }

                                             drawPanel.repaint();
                                             if (step >= 0) {
                                                 nextButton.setVisible(true);
                                             }
                                         }
                                     }
        );
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                step = 0;
                restartButton.setVisible(false);
                drawPanel.setBackground(Color.WHITE); // Hintergrundfarbe zurücksetzen
                drawPanel.repaint();
            }
        });


        setLayout(new BorderLayout());

        // Panel für Eingabe und Buttons erstellen
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Buchstabe eingeben:"));
        inputPanel.add(letterInputField);
        inputPanel.add(nextButton);

        // Panel für Feedback-Bereich und Neues-Spiel-Button erstellen
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputPanel, BorderLayout.NORTH);
        bottomPanel.add(restartButton, BorderLayout.CENTER);

        // Feedback-Bereich unter dem Eingabefeld hinzufügen
        bottomPanel.add(new JScrollPane(feedbackArea), BorderLayout.SOUTH);

        // Komponenten zum Hauptfenster hinzufügen
        add(drawPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);


    }

    public int getStep() {     //für die Anzahl der Steps um diesen, falls falsch, zu erhöhen
        return this.step;

    }

    class DrawPanel extends JPanel //definiert das Fenster
    {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawAll(g);

        }

        private void drawAll(Graphics g) //
        {
            g.setColor(new Color(34, 139, 34));
            g.fillArc(1000, 700, 400, 700, 5, 180); // Shifted to the right bottom

            if (step <= 11) {
                g.setFont(new Font("Consolas", Font.BOLD, 80));
                g.drawString("Hangman", 650, 100);
                g.drawString("Lösung", 200, 400);

            }

            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(139, 69, 19)); // Brown
            g2.setStroke(new BasicStroke(5)); // 5 pixel dicke Pfosten

            // durchgehen des Galgens stück für stück // >= nicht == sonst wird überschrieben
            if (step >= 2) {
                g2.drawLine(1200, 700, 1200, 300);
            }
            if (step >= 3) {
                g2.drawLine(1200, 300, 1400, 300);
            }
            if (step >= 4) {
                g2.drawLine(1200, 400, 1300, 300);
            }
            // Seil
            if (step >= 5) {
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(1400, 300, 1400, 350);
            }

            if (step >= 6) {
                g.drawOval(1375, 350, 50, 50);

            }
            if (step >= 7) {
                g.drawLine(1400, 400, 1400, 500);
            }
            if (step >= 8) {
                g.drawLine(1400, 420, 1350, 480); //y1 begin arm y2 ende arm
            }
            if (step >= 9) {
                g.drawLine(1400, 420, 1450, 480);
            }
            if (step >= 10) {
                // Draw left leg
                g.drawLine(1400, 500, 1350, 600);
            }
            if (step >= 11) {
                // Draw right leg
                g.drawLine(1400, 500, 1450, 600);
            }
            if (step == 12) {

                // game over screen + "Sad Face"
                setBackground(Color.BLACK);
                g.setColor(Color.RED);
                g.setFont(new Font("Consolas", Font.BOLD, 80));
                g.drawString("GAME OVER", 600, 400);
                g.setFont(new Font("Consolas", Font.BOLD, 30));
                g.drawString("Better luck next time...", 600, 500);
                g.setColor(Color.WHITE);
                g.fillOval(1385, 365, 5, 5);
                g.fillOval(1410, 365, 5, 5);
                g.drawArc(1385, 375, 30, 20, 0, 180);
                g.setFont(new Font("Consolas", Font.BOLD, 80));
                g.setColor(Color.RED);
                g.drawString("Hangman", 600, 100);

// galgenmännchen nochmal in farbe als overlay das sichtbar da schwarz
                g.setColor(Color.WHITE);
                g.drawLine(1390, 340, 1400, 350);
                g.drawLine(1400, 340, 1400, 350);
                g.drawLine(1410, 340, 1400, 350);

                //Galgenmenchen
                g.drawOval(1375, 350, 50, 50);
                g.drawLine(1400, 400, 1400, 500);
                g.drawLine(1400, 420, 1350, 480);
                g.drawLine(1400, 420, 1450, 480);
                g.drawLine(1400, 500, 1350, 600);
                g.drawLine(1400, 500, 1450, 600);


            }
            if (step >= 13) {
                g2.drawLine(1200, 400, 1300, 300);
                setBackground(Color.WHITE);
                g.setColor(Color.GREEN);
                g.setFont(new Font("Consolas", Font.BOLD, 80));
                g.drawString("Hangman", 650, 100);
                g.drawString("Richtig Geraten", 300, 400);
                g.setFont(new Font("Consolas", Font.BOLD, 30));
                g.drawString("Du hast das Wort " + "Lösung" + " richtig geraten", 300, 500); //
                g.setColor(Color.BLACK);
                g.fillOval(1385, 365, 5, 5);
                g.fillOval(1410, 365, 5, 5);
                g.drawArc(1385, 375, 30, 20, 0, -180);


            }

        }
    }
}


