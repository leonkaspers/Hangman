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
            System.out.println(game.getCurrentLetters());
        }

        if (game.getState() == 12) {
            System.out.println("You Loose");
            System.out.println(game.getCurrentLetters());
        } else if (game.getState() == 13) {
            System.out.println("You Win");
            System.out.println(game.getCurrentLetters());
        } else {
            System.out.println("something went wrong");
        }

    }
}
