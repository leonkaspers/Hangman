import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


class DrawPanel extends JPanel //definiert das Fenster
{
    Hangman gui;


    public DrawPanel(Hangman gui) {
        super();
        this.gui = gui;



    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawAll(g);

    }

    private void drawAll(Graphics g) //
    {
        int state = gui.getState();
        setBackground(Color.WHITE);
        if (state == 13) {
            g.setColor(new Color(31, 186, 31));
            g.fillArc(990, 600, 420, 600, 5, 180); // Shifted to the right bottom
        }
        if (state <= 11 && state >= 1) {
            g.setColor(new Color(31, 186, 31));
            g.fillArc(990, 600, 420, 600, 5, 180); // Shifted to the right bottom
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(139, 69, 19)); // Brown
        g2.setStroke(new BasicStroke(5)); // 5 pixel dicke Pfosten

        // durchgehen des Galgens stück für stück // >= nicht == sonst wird überschrieben

        if (state == 0)
        {g.setColor(Color.BLACK);
            g.setFont(new Font("Consolas", Font.BOLD, 30));
            g.drawString("Willkommen\n", 100, 230);
            g.drawString("Drücke auf \"Spiel Starten\", um das Spiel mit einem zufälligen Wort zu starten.",100,300  );
            g.drawString("Gebe ein Wort ein und drücke \"Spiel Starten\", um mit diesem das Spiel zu spielen.", 100, 340);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Consolas", Font.BOLD, 15));
            g.drawString("Unter Hilfe findest du die Regeln :)", 100, 380);

            //willkommemmmmmmmmmmenn "klicke starten um ein random wort zu erraten oder eingabe Eines Wortes um dieses im zweispieler modus zu erraten"

        }
        if (state >= 2) {
            g2.drawLine(1200, 600, 1200, 200);
        }
        if (state >= 3) {
            g2.drawLine(1200, 200, 1400, 200);
            g2.drawLine(1200, 600, 1200, 200);
        }
        if (state >= 4) {
            g2.drawLine(1200, 300, 1300, 200);
        }
        // Seil
        if (state >= 5) {

            g2.setStroke(new BasicStroke(2));
            g2.drawLine(1400, 200, 1400, 250);
        }
        g2.setColor(new Color(0, 0, 0));
        if (state >= 6) {
            g.drawOval(1375, 250, 50, 50);

        }
        //"körper"
        if (state >= 7) {
            g.drawLine(1400, 300, 1400, 400);
        }
        //arme
        if (state >= 8) {
            g.drawLine(1400, 320, 1350, 380); //y1 begin arm y2 ende arm
        }
        if (state >= 9) {
            g.drawLine(1400, 320, 1450, 380);
        }
        if (state >= 10) {
            // Draw left leg
            g.drawLine(1400, 400, 1350, 500);
        }
        if (state >= 11) {
            // Draw right leg
            g.drawLine(1400, 400, 1450, 500);
        }
        if (state == 12) {

            // game over screen + "Sad Face"
            setBackground(Color.BLACK);
            g.setColor(Color.RED);

            //eckdaten Zu GAME OVER SCREEN
            g.setFont(new Font("Consolas", Font.BOLD, 80));
            FontMetrics metrics = g.getFontMetrics();
            // Texte
            String text1 = "GAME OVER";
            int width1 = metrics.stringWidth(text1);
            // Position
            int x1 = (getWidth() - width1) / 2; // horizontal mittig für Text 1
            int y1 = (getHeight() / 2) - 50; // vertikal positionieren für Text 1
            g.drawString(text1, x1, y1);
            g.setFont(new Font("Consolas", Font.BOLD, 40));
            metrics = g.getFontMetrics();
            String text2 = "Mehr Glück beim nächsten Mal :)";
            int width2 = metrics.stringWidth(text2);
            // Position
            int x2 = (getWidth() - width2) / 2; // horizontal mittig für Text 2
            int y2 = (getHeight() / 2) + 50; // vertikal positionieren für Text 2
            g.drawString(text2, x2, y2);

            //  g.setFont(new Font("Consolas", Font.BOLD, 80));
            //  g.drawString("GAME OVER", 600, 400);                  OLD ONE
            // g.setFont(new Font("Consolas", Font.BOLD, 30));
            // g.drawString("Mehr Glück beim nächsten mal", 600, 500);

            //GALGENMÄNCHEN gesicht
            g.setColor(Color.WHITE);
            g.fillOval(1385, 265, 5, 5);
            g.fillOval(1410, 265, 5, 5);
            g.drawArc(1385, 275, 30, 20, 0, 180);
            g.setFont(new Font("Consolas", Font.BOLD, 80));

            // galgenmännchen nochmal in farbe als overlay das sichtbar da schwarz
            g.setColor(Color.WHITE);
            g.drawLine(1390, 240, 1400, 250);
            g.drawLine(1400, 240, 1400, 250);
            g.drawLine(1410, 240, 1400, 250);

            //Galgenmenchen
            g.drawOval(1375, 250, 50, 50);
            g.drawLine(1400, 300, 1400, 400);
            g.drawLine(1400, 320, 1350, 380);
            g.drawLine(1400, 320, 1450, 380);
            g.drawLine(1400, 400, 1350, 500);
            g.drawLine(1400, 400, 1450, 500);
            g.setColor(new Color(73, 73, 73));
            g.fillArc(990, 600, 420, 600, 5, 180);


        }
        if (state == 13) {
            g2.drawLine(1200, 300, 1300, 200);
            setBackground(Color.WHITE);
            g.setColor(new Color(31, 186, 31));
            g.setFont(new Font("Consolas", Font.BOLD, 50));
            g.drawString("Du hast richtig geraten",500,400 );
            g.setColor(Color.BLACK);
            g.fillOval(1385, 265, 5, 5);
            g.fillOval(1410, 265, 5, 5);
            g.drawArc(1385, 275, 30, 20, 0, -180);



        }
    }


}