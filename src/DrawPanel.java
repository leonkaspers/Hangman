import javax.swing.*;
import java.awt.*;


public class DrawPanel extends JPanel  {

    private int state;

    public DrawPanal(){
        this.state = 0;
    }

    public void setState (int state){
        this.state = state;
        repaint();
    }

    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        drawAll(g);
    }
    private void drawAll(Graphics g) {
        g.setColor(new Color(17, 153, 13));
        g.fillArc(1000, 700, 400, 700, 5, 180);

        if (state <= 11) {
            g.setFont(new Font("Consolas", Font.BOLD, 80));
            g.drawString("Hangman", 650, 100);
            g.drawString("Lösung", 200, 400);
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(139, 69, 19));
        g2.setStroke(new BasicStroke(5));

        if (state >= 2) {
            g2.drawLine(1200, 700, 1200, 300);
        }
        if (state >= 3) {
            g2.drawLine(1200, 300, 1400, 300);
        }
        if (state >= 4) {
            g2.drawLine(1200, 400, 1300, 300);
        }
        if (state >= 5) {
            g2.setColor(Color.BLACK);
            g2.drawLine(1200, 400, 1400, 300);
            g2.drawLine(1400, 300, 1400, 350);
        }
        if (state >= 6) {
            g2.drawOval(1375, 350, 50, 50);
        }
        if (state >= 7) {
            g2.drawLine(1400, 400, 1400, 500);
        }
        if (state >= 8) {
            g2.drawLine(1400, 500, 1450, 550);
        }
        if (state >= 9) {
            g2.drawLine(1400, 500, 1350, 550);
        }
        if (state >= 10) {
            g2.drawLine(1400, 450, 1450, 425);
        }
        if (state >= 11) {
            g2.drawLine(1400, 450, 1350, 425);
        }
    }
}