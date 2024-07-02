import javax.swing.*;
import java.awt.*;


class DrawPanel extends JPanel //definiert das Fenster
{
    public void setGui(NewGui gui) {
        this.gui = gui;
    }

    NewGui gui;

    public DrawPanel(NewGui gui) {
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

        g.setColor(new Color(0, 255, 0));
        g.fillArc(1000, 700, 400, 700, 5, 180); // Shifted to the right bottom

        if (state <= 11) {
            g.setFont(new Font("Consolas", Font.BOLD, 80));

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
            String text2 = "Mehr Glück beim nächsten mal :)";
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
            g.fillOval(1385, 365, 5, 5);
            g.fillOval(1410, 365, 5, 5);
            g.drawArc(1385, 375, 30, 20, 0, 180);
            g.setFont(new Font("Consolas", Font.BOLD, 80));

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
            g.setColor(Color.BLACK);
            g.fillOval(1385, 365, 5, 5);
            g.fillOval(1410, 365, 5, 5);
            g.drawArc(1385, 375, 30, 20, 0, -180);

            //eckdaten Zu GAME OVER SCREEN
            g.setFont(new Font("Consolas", Font.BOLD, 80));
            FontMetrics metrics = g.getFontMetrics();
            // Texte
            String text1 = "Richtig Geraten";
            int width3 = metrics.stringWidth(text1);
            // Position
            int x3 = (getWidth() - width3) / 2; // horizontal mittig für Text 1
            int y3 = (getHeight() / 2) - 50; // vertikal positionieren für Text 1
            g.drawString(text1, x3, y3);
            g.setFont(new Font("Consolas", Font.BOLD, 40));
            metrics = g.getFontMetrics();
            String text4 = "Das Wort ist"+ "Lösung";
            int width4 = metrics.stringWidth(text4);
            // Position
            int x4 = (getWidth() - width4) / 2; // horizontal mittig für Text 2
            int y4 = (getHeight() / 2) + 50; // vertikal positionieren für Text 2
            g.drawString(text4, x4, y4);



        }
    }


}