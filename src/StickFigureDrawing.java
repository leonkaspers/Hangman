import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StickFigureDrawing extends JFrame {
    private int step = 0;   //für step counter umd nach fehlern zu malen
    public int getStep() {     //für die Anzahl der Steps um diesen, falls falsch, zu erhöhen
        return step;
    }
    public StickFigureDrawing() //fenster specs + button
     {
        setTitle("Hanman");    //game fenster Titel
        setSize(1600, 900);   //Fester Größe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //nur übergangsweise bis initialisiert das bei False count +1
        DrawPanel drawPanel = new DrawPanel();
        JButton nextButton = new JButton("Falsch Geraten");

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                step++;
                drawPanel.repaint();
            }
        }
        );
        add(drawPanel, BorderLayout.CENTER);
        add(nextButton, BorderLayout.SOUTH);
    }
    class DrawPanel extends JPanel //was im Fenster ist
    {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawHill(g);
            drawPost(g);
        }
//koordinaten wie Folgt, x für die stelle auf der x Achse des Fensters in dem fall von 0-1599.
// y für die höhe 0-899 und malt von x1 bis x2 bei y1 bis y2 von oben nach unten

        private void drawHill(Graphics g) //bild für den Hügel
        {
            g.setColor(new Color(34, 139, 34));
            g.fillArc(1000, 700, 400, 700, 5, 180); // Shifted to the right bottom
        }

        private void drawPost(Graphics g) //Pfosten merkmale +game Over screen
        {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(139, 69, 19)); // Brown
            g2.setStroke(new BasicStroke(5)); // 5 pixel dicke Pfosten


            // durchgehen des Galgens stück für stück    // >= nicht == sonst wird überschrieben
            if (step >= 1) {
                g2.drawLine(1200, 700, 1200, 300);
            }
            if (step >= 2) {
                g2.drawLine(1200, 300, 1400, 300);
            }
            if (step >= 3) {
                g2.drawLine(1200, 400, 1300, 300);
            }
            // Seil
            if (step >= 4) {
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(1400, 300, 1400, 350);
            }

            if (step >= 5) {
                g.drawOval(1375, 350, 50, 50);

            }
            if (step >= 6) {
                g.drawLine(1400, 400, 1400, 500);
            }
            if (step >= 7) {
                g.drawLine(1400, 420, 1350, 480); //y1 begin arm y2 ende arm
            }
            if (step >= 8) {
                g.drawLine(1400, 420, 1450, 480);
            }
            if (step >= 9) {
                // Draw left leg
                g.drawLine(1400, 500, 1350, 600);
            }
            if (step >= 10) {
                // Draw right leg
                g.drawLine(1400, 500, 1450, 600);
            }
            if (step >= 11){

                // game over screen + "Sad Face"
                setBackground(Color.BLACK);
                g.setColor(Color.RED);
                g.setFont(new Font("SansSerif", Font.BOLD, 80));
                g.drawString("GAME OVER", 600, 400);
                g.setFont(new Font("SansSerif", Font.BOLD, 30));
                g.drawString("Better luck next time...", 600, 500);
                g.setColor(Color.WHITE);
                    g.fillOval(1385, 365, 5, 5);
                    g.fillOval(1410, 365, 5, 5);
                    g.drawArc(1385, 375, 30, 20, 0, 180);

// galgenmännchen nochmal in farbe als overlay das sichtbar da schwarz
                g.setColor(Color.WHITE);
//haare
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



        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StickFigureDrawing().setVisible(true);
            }
        });
    }
}
