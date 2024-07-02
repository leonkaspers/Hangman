import java.util.Arrays;
import java.util.LinkedList;

public class Game {

    // Fields

    // The current play word and its char Array representation
    private final String word;
    private final char[] wordLetters;

    // A list of the already used words and the string generated from that list
    private final LinkedList<String> used = new LinkedList<>();

    // The current game state
    private int state;

    // the current letters entered by the player which were correct
    private char[] currentLetters;
    private String currentLettersOutputString;

    // saves the current input entered in the try Method
    private String currentInput;


    // Constructors

    /**
     * Constructor for a game with a random word.
     */
    public Game() {
        this.state = 1;
        this.word = Words.getRandomWord();
        this.wordLetters = this.word.toCharArray();
        this.currentLetters = new char[this.word.length()];
        generateNewOutputString();
    }

    /**
     * Constructor for a game with a user-defined word.
     *
     * @param string The word to be guessed in the game.
     */
    public Game(String string) {
        this.state = 1;
        this.word = string;
        this.wordLetters = this.word.toCharArray();
        this.currentLetters = new char[this.word.length()];
    }


    // Getter and Setter

    /**
     * @return The string representation of the used letters.
     */
    public String getUsedOutputString() {
        return used.toString();
    }

    /**
     * @return The current state of the game.
     */
    public int getState() {
        return state;
    }

    /**
     * @return The word to be guessed in the game (for debug purposes only)
     */
    public String getWord() {
        return word;
    }

    /**
     * @return The current letters guessed by the player.
     */
    public String getCurrentLetters() {
        generateNewOutputString();
        return currentLettersOutputString;
    }


    // Try Algorithm

    /**
     * Tries the input given by the player.
     *
     * @param t The input given by the player.
     */
    public void tryInput(String t) {

        // safe input with correct casing still intact
        currentInput = t;

        // set input to lower case to ease all future operations
        t = t.toLowerCase();

        // decide if input is letter or word
        if (t.isEmpty()) {
            return;
        } else if (t.length() == 1) {
            tryLetter(t.charAt(0));
        } else {
            tryWord(t);
        }

        // add the input to the used list
        addToUsed();
    }

    /**
     * Tries a letter in the word.
     *
     * @param c The letter to be tried.
     */
    private void tryLetter(char c) {

        // search the game word for the given letter, proceed based on finding
        for (char wordLetter : this.wordLetters) {
            if (c == Character.toLowerCase(wordLetter)) {
                correctLetter(c);
                return;
            }
        }

        // if the letter was not found
        wrongInput();
    }

    /**
     * Updates currentLetters Array if letter is correct.
     *
     * @param c The correct letter.
     */
    private void correctLetter(char c) {

        // for every instance of the letter in the game word replace the blank in the current letter array with the correct letter
        for (int i = 0; i < this.wordLetters.length; i++) {
            if (c == Character.toLowerCase(this.wordLetters[i])) {
                this.currentLetters[i] = this.wordLetters[i];
            }
        }

        // check if the game is won
        if (Arrays.equals(this.wordLetters, this.currentLetters)) {
            win();
        }
    }

    /**
     * Tries a word in the game.
     *
     * @param w The word to be tried.
     */
    private void tryWord(String w) {

        // checks if the given word is the correct word
        if (w.equals(this.word.toLowerCase())) {
            win();
        } else {
            wrongInput();
        }
    }

    /**
     * Updates game state if letter is incorrect.
     */
    private void wrongInput() {
        this.state = state + 1;
    }

    /**
     * Sets the game to a win state.
     */
    private void win() {
        this.state = 13;
        this.currentLetters = this.wordLetters;
    }


    //Helper Functions

    /**
     * Generates a new output string for the current letters.
     */
    private void generateNewOutputString() {
        StringBuilder s = new StringBuilder();
        for (char currentLetter : this.currentLetters) {
            if (currentLetter == '\u0000') {
                s.append("_");
            } else {
                s.append(currentLetter);
            }
            s.append(" ");
        }
        this.currentLettersOutputString = s.toString();
    }

    /**
     * Adds the current input to the used letters.
     */
    private void addToUsed() {
        if (!this.used.contains(currentInput)) {
            used.add(currentInput);
        }
    }

}
