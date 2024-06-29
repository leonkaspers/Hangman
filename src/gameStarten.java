import javax.swing.*;

public class gameStarten {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StickFigureDrawing().setVisible(true);
            }
        });
    }
}
