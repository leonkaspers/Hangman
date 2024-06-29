import java.util.Scanner;

public class GameTest {
    public static void main(String[] args) {
        Game game = new Game();

        Scanner scanner = new Scanner(System.in);

        System.out.println(game.getWord());

        while (game.getState() < 12) {
            System.out.println("Enter something:");
            String input = scanner.nextLine();

            game.tryInput(input);
            System.out.println(game.getCurrentLettersOutputString());
        }

        if (game.getState() == 12) {
            System.out.println("You Loose");
            System.out.println(game.getCurrentLettersOutputString());
        } else if (game.getState() == 13) {
            System.out.println("You Win");
            System.out.println(game.getCurrentLettersOutputString());
        } else {
            System.out.println("something went wrong");
        }

    }
}

/*
@ -0,0 +1,11 @@
import javax.swing.*;

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            new StickFigureDrawing().setVisible(true);
        }
    });
}

 */
