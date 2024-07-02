import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


// Gui für wort raten mit rückgabe von false oder variabler + 1 sonst draw im besten fall in eine sich erneuernde Zeile

public class NewGui extends JFrame implements ActionListener {

    JPanel panel;
    JLabel label; // static machen
    JLabel usedLetter;
    JTextField inputField;
    JTextArea output;
    JButton input;
    JButton reset;
    JLabel outputField;
    JButton help;
    StickFigureDrawing.DrawPanel graphic;

    int state;

    Game game;

    public NewGui() throws IOException {

        //Creating the Frame
        JFrame frame = new JFrame("Hangman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);

        //creating Basic Layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Creating TOP Panel and Help Button
        JPanel header = new JPanel();
        JLabel title = new JLabel("HangmanAI");
        help = new JButton("Help");
        header.add(title);
        header.add(help);

        //Creating the panel at bottom and adding components
        panel = new JPanel(); // the panel is not visible in output
        label = new JLabel("Enter Text");
        usedLetter = new JLabel("Benutze Buchstaben:");
        inputField = new JTextField(15); // accepts upto 10 characters
        input = new JButton("Eingabe");
        reset = new JButton("Reset");   // Als Resetbutton
        panel.add(label); // Components Added using Flow Layout
        panel.add(inputField);
        panel.add(input);
        panel.add(reset);


        // Add Action Listener
        input.addActionListener(this);
        reset.addActionListener(this);

        // Text Area at the Center
        output = new JTextArea();
        output.setEditable(false);


        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, output);
        frame.setVisible(true);

        state = 0;
        updateGUI();

    }

    public static void main(String[] args) throws IOException {
        NewGui gui = new NewGui();
        gui.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.input && game == null) {
            if (inputField.getText().isEmpty()) {
                this.game = new Game();
            } else {
                this.game = new Game(inputField.getText());
                inputField.setText("");
            }

            output.append(game.getWord());

        } else if (ae.getSource() == this.input && game != null) {
            output.append("Eingabe" + inputField.getText() + "\n");
            game.tryInput(inputField.getText());
            output.append(game.getCurrentLettersOutputString() + "\n");
            state = game.getState();
            updateGUI();
            inputField.setText("");
        } else if (ae.getSource() == this.reset) {
            this.game = null;
            state = 0;
            updateGUI();

        }
    }

    private void updateGUI() {
        if (!(game.getUsedLetters() == null)) {
            usedLetter.setText(game.getUsedLetters().toString());
        }
        graphic.pa
        switch (this.state) {
            case 0: {
                output.append("Bitte Wort eingeben, leer lassen für random word");
                input.setVisible(true);
                break;
            }
            case 1: {
                output.append("Noch 10 Versuche\n");
                break;
            }
            case 2: {
                output.append("Noch 9 Versuche\n");
                break;
            }
            case 3: {
                output.append("Noch 8 Versuche\n");
                break;
            }
            case 4: {
                output.append("Noch 7 Versuche\n");
                break;
            }
            case 5: {
                output.append("Noch 6 Versuche\n");
                break;
            }
            case 6: {
                output.append("Noch 5 Versuche\n");
                break;
            }
            case 7: {
                output.append("Noch 4 Versuche\n");
                break;
            }
            case 8: {
                output.append("Noch 3 Versuche\n");
                break;
            }
            case 9: {
                output.append("Noch 2 Versuche\n");
                break;
            }
            case 10: {
                output.append("Noch 1 Versuche\n");
                break;
            }
            case 11: {
                output.append("Game Over\n");
                input.setVisible(false);
                break;
            }
            case 12: {
                output.append("SIEEEEEEEEEEEEEEEEEGGGGGGGGGGGGGG\n");
                input.setVisible(false);
                break;
            }
        }
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

            if (state <= 11) {
                g.setFont(new Font("Consolas", Font.BOLD, 80));
                g.drawString("Hangman", 650, 100);
                g.drawString("Lösung", 200, 400);

            }

            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(139, 69, 19)); // Brown
            g2.setStroke(new BasicStroke(5)); // 5 pixel dicke Pfosten

            // durchgehen des Galgens stück für stück // >= nicht == sonst wird überschrieben
            if (state >= 2) {
                g2.drawLine(1200, 700, 1200, 300);
            }
            if (state >= 3) {
                g2.drawLine(1200, 300, 1400, 300);
            }
            if (state >= 4) {
                g2.drawLine(1200, 400, 1300, 300);
            }
            // Seil
            if (state >= 5) {
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(1400, 300, 1400, 350);
            }

            if (state >= 6) {
                g.drawOval(1375, 350, 50, 50);

            }
            if (state >= 7) {
                g.drawLine(1400, 400, 1400, 500);
            }
            if (state >= 8) {
                g.drawLine(1400, 420, 1350, 480); //y1 begin arm y2 ende arm
            }
            if (state >= 9) {
                g.drawLine(1400, 420, 1450, 480);
            }
            if (state >= 10) {
                // Draw left leg
                g.drawLine(1400, 500, 1350, 600);
            }
            if (state >= 11) {
                // Draw right leg
                g.drawLine(1400, 500, 1450, 600);
            }
            if (state == 12) {

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
            if (state >= 13) {
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
