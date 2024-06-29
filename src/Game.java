public class Game {

    private final String word;
    //Fields
    private int state;
    private final char[] wordLetters;

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

    private void tryInput(String t) {
        if (t.length() == 0) {
        } else if (t.length() == 1) {
            tryLetter(t.charAt(0));
        } else {
            tryWord(t);
        }

    }


    //Letter Code

    private void tryLetter(char c) {
        for (int i = 0; i < this.wordLetters.length; i++) {
            if (c == this.wordLetters[i]) {
                correctLetter(c);
            }
        }

        wrongInput();
    }

    //updates currenLetters Array and GUI if letter correct
    private void correctLetter(char c) {
        for (int i = 0; i < this.wordLetters.length; i++) {
            if (c == this.wordLetters[i]) {
                this.currentLetters[i] = c;
            }
        }
        generateNewOutputString();

        // Update GUI TODO
    }


    // Word Code

    private void tryWord(String w) {
        if (w.equals(this.word)) {
            this.currentLetters = this.wordLetters;
            generateNewOutputString();
            win();
        } else {
            wrongInput();
        }
    }


    //updates GUI if letter incorrect
    private void wrongInput() {
        this.state = state + 1;

        // Update GUI TODO
    }


    //Hilsfunktionen
    private void generateNewOutputString() {
//TODO
    }

}
