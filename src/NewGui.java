import javax.swing.*;
import java.awt.*;

public class NewGui {

    public NewGui(){

        //Creating the Frame
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(15); // accepts upto 10 characters
        JButton send = new JButton("Eingabe");
        JButton reset = new JButton("Reset");   // Als Resetbutton
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // Text Area at the Center
        JTextArea feedbackArea = new JTextArea();
        feedbackArea.setEditable(false);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, feedbackArea);
        frame.setVisible(true);

    }
}
