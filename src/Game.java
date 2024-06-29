public class Game {

    //Fields
    private int state;

    private String word;

    private char[] wordLetters;

    private char[] currentLetters;

    // Konstruktor für Random Word
    public Game() {
        this.state = 1;
        this.word = Words.getRandomWord();
        this.wordLetters = this.word.toCharArray();
        this.currentLetters = new char[this.word.length()];
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
            return;
        } else if (t.length() == 1) {
            tryLetter(t.charAt(0));
        } else {
            tryWord(t);
        }

    }

    private void tryLetter(char c) {

    }

}
