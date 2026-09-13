import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class ComputeGUI extends JFrame {

    private JTextField reqScoreField;
    private JTextField gamesField;
//    private JTextField rateField;
//    private JTextField firstScoreField;
//    private JTextArea outputArea;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public ComputeGUI() {
        setTitle("Compute GUI");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        reqScoreField = new JTextField();
        gamesField = new JTextField();
//        rateField = new JTextField();
//        firstScoreField = new JTextField();
//        outputArea = new JTextArea();
//        outputArea.setEditable(false);

        // jtable for output list
        String[] columns = {"Rank", "University", "Team Name", "Final Score", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        resultTable = new JTable(tableModel);

        JButton runButton = new JButton("Run Compute");

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 8, 8));

        inputPanel.add(new JLabel("Required score:"));
        inputPanel.add(reqScoreField);

        inputPanel.add(new JLabel("Number of games:"));
        inputPanel.add(gamesField);

//        inputPanel.add(new JLabel("Rate of increase:"));
//        inputPanel.add(rateField);

//        inputPanel.add(new JLabel("First game score:"));
//        inputPanel.add(firstScoreField);

        inputPanel.add(new JLabel(""));
        inputPanel.add(runButton);

        add(inputPanel, BorderLayout.NORTH);
        // switched to jtable from terminal output
        add(new JScrollPane(resultTable), BorderLayout.CENTER);

        runButton.addActionListener(e -> runCompute());
    }

    private void runCompute() {
    InputStream originalIn = System.in;
    PrintStream originalOut = System.out;

        try {
            String input =
                reqScoreField.getText() + "\n" +
                gamesField.getText() + "\n";
//                rateField.getText() + "\n" +
//                firstScoreField.getText() + "\n";

            ByteArrayInputStream fakeInput =
                new ByteArrayInputStream(input.getBytes());
//
//            ByteArrayOutputStream fakeOutput =
//                new ByteArrayOutputStream();
//
            System.setIn(fakeInput);
//            System.setOut(new PrintStream(fakeOutput));

            Compute program = new Compute();
            ArrayList<TeamResult> results = program.compute();
            tableModel.setRowCount(0);

            int i = 0;
            float lastScore = 0;
            for (TeamResult team : results) {
                if (lastScore != team.score) ++i; // i know this looks weird its just to make ties have the same rank
                lastScore = team.score;
                // populate the table
                String status = team.qualified ? "QUALIFIED" : "NOT QUALIFIED";
                tableModel.addRow(new Object[]{i, team.uniName, team.teamName, String.format("%.2f", team.score), status});
            }

//            outputArea.setText(fakeOutput.toString());

        } catch (Exception ex) {
//            resultTable.("Error: " + ex.getMessage());
//            System.err.println("Error: " + ex.getMessage());
            tableModel.setRowCount(0);
            tableModel.addRow(new Object[]{"Error", ex.getMessage(), ""});
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
    }
}
}