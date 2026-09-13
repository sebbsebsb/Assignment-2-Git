import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ComputeGUI extends JFrame {

    private JTextField reqScoreField;
    private JTextField gamesField;
    private JTextField rateField;
    private JTextField firstScoreField;
    private JTextArea outputArea;

    public ComputeGUI() {
        setTitle("Compute GUI");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        reqScoreField = new JTextField();
        gamesField = new JTextField();
        rateField = new JTextField();
        firstScoreField = new JTextField();
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JButton runButton = new JButton("Run Compute");

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 8, 8));

        inputPanel.add(new JLabel("Required score:"));
        inputPanel.add(reqScoreField);

        inputPanel.add(new JLabel("Number of games:"));
        inputPanel.add(gamesField);

        inputPanel.add(new JLabel("Rate of increase:"));
        inputPanel.add(rateField);

        inputPanel.add(new JLabel("First game score:"));
        inputPanel.add(firstScoreField);

        inputPanel.add(new JLabel(""));
        inputPanel.add(runButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        runButton.addActionListener(e -> runCompute());
    }

    private void runCompute() {
    InputStream originalIn = System.in;
    PrintStream originalOut = System.out;

        try {
            String input =
                reqScoreField.getText() + "\n" +
                gamesField.getText() + "\n" +
                rateField.getText() + "\n" +
                firstScoreField.getText() + "\n";

            ByteArrayInputStream fakeInput =
                new ByteArrayInputStream(input.getBytes());

            ByteArrayOutputStream fakeOutput =
                new ByteArrayOutputStream();

            System.setIn(fakeInput);
            System.setOut(new PrintStream(fakeOutput));

            Compute program = new Compute();
            program.compute();

            outputArea.setText(fakeOutput.toString());

        } catch (Exception ex) {
            outputArea.setText("Error: " + ex.getMessage());

        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
    }
}
}