import java.util.Arrays;

public class Game {

    private final String word;
    private final char[] wordLetters;
    private int state;
    private char[] currentLetters;
    private String currentLettersOutputString;

    // Konstruktor für Random Word
    public Game() {
        this.state = 1;
        this.word = Words.getRandomWord();
        this.wordLetters = this.word.toCharArray();
        this.currentLetters = new char[this.word.length()];
        generateNewOutputString();
    }

    // Konstruktor für benutzerdefiniertes Word
    public Game(String string) {
        this.state = 1;
        this.word = string;
        this.wordLetters = this.word.toCharArray();
        this.currentLetters = new char[this.word.length()];
    }

    public int getState() {
        return state;
    }

    public String getWord() {
        return word;
    }

    public void setState(int state) {
        this.state = state;
    }

    public char[] getWordLetters() {
        return wordLetters;
    }

    public char[] getCurrentLetters() {
        return currentLetters;
    }

    public void setCurrentLetters(char[] currentLetters) {
        this.currentLetters = currentLetters;
    }

    public String getCurrentLettersOutputString() {
        return currentLettersOutputString;
    }

    public void setCurrentLettersOutputString(String currentLettersOutputString) {
        this.currentLettersOutputString = currentLettersOutputString;
    }

    public void tryInput(String t) {
        t = t.toLowerCase();
        if (t.length() == 0) {
            return;
        } else if (t.length() == 1) {
            tryLetter(t.charAt(0));
        } else {
            tryWord(t);
        }

    }


    //Letter Code

    private void tryLetter(char c) {
        for (int i = 0; i < this.wordLetters.length; i++) {
            if (c == Character.toLowerCase(this.wordLetters[i])) {
                correctLetter(c);
                return;
            }
        }

        wrongInput();
    }

    //updates currenLetters Array and GUI if letter correct
    private void correctLetter(char c) {
        for (int i = 0; i < this.wordLetters.length; i++) {
            if (c == Character.toLowerCase(this.wordLetters[i])) {
                this.currentLetters[i] = this.wordLetters[i];
            }
        }
        generateNewOutputString();

        if (Arrays.equals(this.wordLetters, this.currentLetters)) {
            win();
        }

        // Update GUI TODO
    }


    // Word Code

    private void tryWord(String w) {
        if (w.equals(this.word.toLowerCase())) {
            win();
        } else {
            wrongInput();
        }
    }


    //updates GUI if letter incorrect
    private void wrongInput() {
        this.state = state + 1;
        if (state == 11) {
            gameOver();
        }

        // Update GUI TODO
    }

    private void win() {
        this.state = 12;
        this.currentLetters = this.wordLetters;
        generateNewOutputString();
    }

    private void gameOver() {

    }


    //Hilsfunktionen
    private void generateNewOutputString() {
        String s = "";
        for (int i = 0; i < this.currentLetters.length; i++) {
            if (currentLetters[i] == '\u0000') {
                s = s + "_";
            } else {
                s = s + currentLetters[i];
            }

            s = s + " ";

        }

        this.currentLettersOutputString = s;
    }

}
