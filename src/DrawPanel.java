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
