import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Gui für wort raten mit rückgabe von false oder variabler + 1 sonst draw im besten fall in eine sich erneuernde Zeile

public class NewGui extends JFrame implements ActionListener {

    JPanel panel;
    JLabel label;
    JTextField textfield;
    JTextArea output;
    JButton input;
    JButton reset;

    int state;

    Game game;

    public NewGui() {

        //Creating the Frame
        JFrame frame = new JFrame("Hangman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);

        //Creating the panel at bottom and adding components
        panel = new JPanel(); // the panel is not visible in output
        label = new JLabel("Enter Text");
        textfield = new JTextField(15); // accepts upto 10 characters
        input = new JButton("Eingabe");
        reset = new JButton("Reset");   // Als Resetbutton
        panel.add(label); // Components Added using Flow Layout
        panel.add(textfield);
        panel.add(input);
        panel.add(reset);

        // Add Action Listener
        input.addActionListener(this);
        reset.addActionListener(this);

        // Text Area at the Center
        output = new JTextArea();
        output.setEditable(false);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, output);
        frame.setVisible(true);

        state = 0;
        updateGUI();

    }

    public static void main(String[] args) {
        NewGui gui = new NewGui();
        gui.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.input && game == null) {
            if (textfield.getText().isEmpty()) {
                this.game = new Game();
            } else {
                this.game = new Game(textfield.getText());
                textfield.setText("");
            }

            output.append(game.getWord());

        } else if (ae.getSource() == this.input && game != null) {
            output.append("Eingabe" + textfield.getText() + "\n");
            game.tryInput(textfield.getText());
            output.append(game.getCurrentLettersOutputString() + "\n");
            state = game.getState();
            updateGUI();
            textfield.setText("");
        } else if (ae.getSource() == this.reset) {
            this.game = null;
            state = 0;
            updateGUI();

        }
    }

    private void updateGUI() {
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
