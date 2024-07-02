import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


// Gui für wort raten mit rückgabe von false oder variabler + 1 sonst draw im besten fall in eine sich erneuernde Zeile

public class NewGui extends JFrame implements ActionListener {

    JPanel panel;
    JLabel label; // static machen
    JLabel usedLetter;
    JTextField inputField;
    JTextArea output;
    JButton input;
    JButton reset;
    JLabel outputField;
    JButton help;
    DrawPanel graphic;


    @Override
    public int getState() {
        return state;
    }

    int state;

    Game game;

    public NewGui()  {
        //Creating the Frame
        JFrame frame = new JFrame("Hangman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);

        //creating Basic Layout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Creating TOP Panel and Help Button
        JPanel header = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel title = new JLabel("HangmanAI");
        centerPanel.add(title);

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        help = new JButton("Help");
        leftPanel.add(help);

        header.add(leftPanel, BorderLayout.WEST);
        header.add(centerPanel, BorderLayout.CENTER);

        // Creating live Panel
        JPanel live = new JPanel();
        outputField = new JLabel();
        live.add(outputField);

        //creating Draw Panel
        graphic = new DrawPanel(this);

        // Creating usedPanel
        JPanel usedPanel = new JPanel();
        JLabel usedLettersText = new JLabel("Benutze Buchstaben:");
        usedLetter = new JLabel();
        usedPanel.add(usedLettersText);
        usedPanel.add(usedLetter);

        //Creating the panel at bottom and adding components
        panel = new JPanel(); // the panel is not visible in output
        label = new JLabel("Enter Text");
        inputField = new JTextField(15); // accepts upto 10 characters
        input = new JButton("Eingabe");
        reset = new JButton("Reset");   // Als Resetbutton
        panel.add(label); // Components Added using Flow Layout
        panel.add(inputField);
        panel.add(input);
        panel.add(reset);

        // Add Action Listener
        input.addActionListener(this);
        reset.addActionListener(this);
        help.addActionListener(this);

        // Text Area at the Center
        output = new JTextArea();
        output.setEditable(false);

        // Creating north and south panels
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.add(header);
        northPanel.add(live);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.add(usedPanel);
        southPanel.add(panel);

        // Adding components to main panel
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(graphic, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        //Adding Components to the frame.
        frame.add(mainPanel);
        frame.setVisible(true);

        state = 0;
        updateGUI();
    }

    public static void main(String[] args) throws IOException {
        NewGui gui = new NewGui();
        gui.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.input && game == null) {
            if (inputField.getText().isEmpty()) {
                this.game = new Game();
            } else {
                this.game = new Game(inputField.getText());
                inputField.setText("");
            }
            this.state = game.getState();
            inputField.setText(game.getWord());

        } else if (ae.getSource() == this.input && game != null) {
            output.append("Eingabe" + inputField.getText() + "\n");
            game.tryInput(inputField.getText());
            output.append(game.getCurrentLetters() + "\n");
            outputField.setText(game.getCurrentLetters());
            state = game.getState();
            inputField.setText("");
        } else if (ae.getSource() == this.reset) {
            this.game = null;
            state = 0;

        } else if (ae.getSource() == this.help) {
            // create new Textbox
            // TODO Hilfe schreiben und evlt. anderen Hilfe Button
            JOptionPane.showMessageDialog(
                    null,
                    "Willkommen in der HamgmanAi Hilfezentrale\n" +
                            "Hier die Spielregeln:\n" +
                            "\n" +
                            "Spielablauf:\n" +
                            "\n" +
                            "Buchstaben raten: Der Spieler, also DU, rät eine Reihe an Buchstaben um ein Verdecktes Wort zu erraten.\n" +
                            "Die Rateversuche sind begrenzt.\n" +
                            "\n" +
                            "Richtiger Buchstaben: Wenn ein geratener Buchstabe im Wort vorkommt, schreibt das Spiel\n" +
                            "diesen Buchstaben an die entsprechenden Stellen, die durch die Striche markiert sind.\n" +
                            "\n" +
                            "Falsche Buchstaben: Wird ein falscher Buchstabe genannt, der nicht im Wort vorkommt,\n" +
                            "wird vom Spiel einen Teil zum Galgenmännchen hinzu.\n" +
                            "Dies wie Folgt, Galgen, Strick, Kopf und Körper des Galgenmännchens was das Maximum\n" +
                            "an Fehlversuchen ist erreicht, wenn das Männchen „gehängt“ ist.\n" +
                            "\n" +
                            "Gewinn: Der ratende Spieler gewinnt das Spiel, wenn das Wort erraten wurde \n" +
                            "bevor das Galgenmännchen komplett gezeichnet ist.\n" +
                            "\n" +
                            "Niederlage: Kann das Wort nicht vor Vervollständigung des Galgenmännchens erraten werden,\n" +
                            "gilt das Spiel als verloren und ein neues Spiel kann begonnen werden. ",
                    "Hilfe",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        updateGUI();
    }

    private void updateGUI() {
        if (!(this.game == null)) {
            usedLetter.setText(this.game.getUsedOutputString());
        }

        Graphics g = this.getGraphics();

        if (!(g == null)) {
            graphic.repaint();
        }

        switch (this.state) {
            case 0: {
                output.append("Bitte Wort eingeben, leer lassen für random word");
                input.setVisible(true);
                break;
            }
            case 1: {
                output.append("Noch 10 Versuche\n");
                break;
            }
            case 2: {
                output.append("Noch 9 Versuche\n");
                break;
            }
            case 3: {
                output.append("Noch 8 Versuche\n");
                break;
            }
            case 4: {
                output.append("Noch 7 Versuche\n");
                break;
            }
            case 5: {
                output.append("Noch 6 Versuche\n");
                break;
            }
            case 6: {
                output.append("Noch 5 Versuche\n");
                break;
            }
            case 7: {
                output.append("Noch 4 Versuche\n");
                break;
            }
            case 8: {
                output.append("Noch 3 Versuche\n");
                break;
            }
            case 9: {
                output.append("Noch 2 Versuche\n");
                break;
            }
            case 10: {
                output.append("Noch 1 Versuche\n");
                break;
            }
            case 11: {
                output.append("Game Over\n");
                input.setVisible(false);
                break;
            }
            case 12: {
                output.append("SIEEEEEEEEEEEEEEEEEGGGGGGGGGGGGGG\n");
                input.setVisible(false);
                break;
            }
        }
    }
}


//Grafika teil Das Galgenmänchen

