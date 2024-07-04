import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Confetti {
    private int x, y;
    private int diameter;
    private Color color;
    private int ySpeed;
    private Random random;

    public Confetti(int x, int y) {
        this.x = x;
        this.y = y;
        this.diameter = 5 + new Random().nextInt(10);
        this.color = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
        this.ySpeed = 1 + new Random().nextInt(5);
        this.random = new Random();
    }

    public void update() {
        y += ySpeed;
        if (y > 600) { // Reset position when it goes out of bounds (assuming a height of 600)
            y = 0;
            x = random.nextInt(800); // Reset x position (assuming a width of 800)
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.fill(new Ellipse2D.Double(x, y, diameter, diameter));
    }
}
