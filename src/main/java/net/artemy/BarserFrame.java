package net.artemy;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Artemy on 22.10.2020.
 */
public class BarserFrame extends JFrame {
    private ExcelWorker excelWorker;
    private JPanel mainPanel;
    private JFileChooser fileChooser;
    private JTextField textField;
    private JLabel selectFileLabel;
    private JLabel teacherLabel;
    private JButton fileChooseButton;
    private JButton startParseButton;
    private JLabel  warningLabel;
    private JLabel calculateResultQ1;
    private JTabbedPane jTabbedPane;
    private int mode;
    private JPanel q1;
    private JPanel q2;
    private JPanel q3;
    private JPanel q4;
    private JPanel year;
    private JLabel q1Label;
    private JLabel q2Label;
    private JLabel q3Label;
    private JLabel q4Label;
    private JLabel yearLabel;


    GridBagConstraints c;

    public JTextField getTextField() {
        return textField;
    }

    public BarserFrame(ExcelWorker excelWorker) {
        super("БАРСер");
        this.excelWorker = excelWorker;
        setSize(750, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        initGbc();

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        initFileChooserPanel(mainPanel);
        initCalculatePanel(mainPanel);
        initResultPanel(mainPanel);
        add(mainPanel);

        setVisible(true);
    }

    private void initResultPanel(JPanel mainPanel) {
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 3;
        teacherLabel = new JLabel();
        mainPanel.add(teacherLabel, c);
        c.gridy = 3;
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Результат:"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        resultPanel.setPreferredSize(new Dimension(740, 400));
        jTabbedPane = new JTabbedPane();
        initTabbedPane(jTabbedPane);
        resultPanel.add(jTabbedPane, BorderLayout.CENTER);
        mainPanel.add(resultPanel, c);
    }

    private void initCalculatePanel(JPanel mainPanel) {
        c.gridx = 0;
        c.gridy = 1;
        startParseButton = new JButton("Посчитать");

        startParseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startParseButtonActionPerformed();
            }
        });
        calculateResultQ1 = new JLabel();
        mainPanel.add(startParseButton, c);
        c.gridx = 1;
        warningLabel = new JLabel();
        warningLabel.setForeground(Color.RED);
        mainPanel.add(warningLabel, c);
    }

    private void initFileChooserPanel(JPanel mainPanel) {
        c.gridx = 0;
        c.gridy = 0;
        selectFileLabel = new JLabel("Укажите файл: ");
        mainPanel.add(selectFileLabel, c);
        c.gridx = 1;
        fileChooser = new JFileChooser();
        FileFilter filter = new FileTypeFilter(".xls", "Excel Files");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File("C:/"));

        fileChooseButton = new JButton("Browse");
        fileChooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                chooseButtonActionPerformed(evt);
            }
        });
        textField = new JTextField(29);
        mainPanel.add(textField,c);
        c.gridx = 2;
        mainPanel.add(fileChooseButton, c);
    }

    private void initTabbedPane(JTabbedPane tabbedPane) {
        //tabbedPane.setTabLayoutPolicy(JTabbedPane.HORIZONTAL);
        //tabbedPane.setSize(200, 300);
        q1 = new JPanel();
        q2 = new JPanel();
        q3 = new JPanel();
        q4 = new JPanel();
        year = new JPanel();
        q1Label = new JLabel();
        q2Label = new JLabel();
        q3Label = new JLabel();
        q4Label = new JLabel();
        yearLabel = new JLabel();
        q1.add(q1Label);
        q2.add(q2Label);
        q3.add(q3Label);
        q4.add(q4Label);
        year.add(yearLabel);

        tabbedPane.add("1/4", q1);
        tabbedPane.add("2/4", q2);
        tabbedPane.add("3/4", q3);
        tabbedPane.add("4/4", q4);
        tabbedPane.add("ГОД", year);
    }

    private void setText(String text, JPanel panel) {
        JLabel l = new JLabel(text);
        panel.add(l);
    }

    private void clearPreviousResults() {
       q1Label.setText("");
       q2Label.setText("");
       q3Label.setText("");
       q4Label.setText("");
       teacherLabel.setText("");
       yearLabel.setText("");
    }


    private void startParseButtonActionPerformed() {
        clearPreviousResults();
        if (textField.getText() == null || textField.getText().equals("")) {
            warningLabel.setText("Не задан путь к файлу");
            return;
        }
        SchoolClass schoolClass = new SchoolClass();
        String errorText = excelWorker.parseExcel(textField.getText(), schoolClass);
        if (!errorText.equals("")) {
            warningLabel.setText("Неверный формат файла или данные не могут быть прочитаны");
            return;
        }
        if (!schoolClass.getTeacher().isEmpty()) {
            teacherLabel.setText(schoolClass.getTeacher());
        }
        schoolClass.countStatistics();
        String result1stQ = "<html>" + schoolClass.getResult1stQuarter().replaceAll("\n", "<br>") + "</html>";
        setText(result1stQ, q1Label);
        String result2ndQ = "<html>" + schoolClass.getResult2ndQuarter().replaceAll("\n", "<br>") + "</html>";
        setText(result2ndQ, q2Label);
        String result3rdQ = "<html>" + schoolClass.getResult3rdQuarter().replaceAll("\n", "<br>") + "</html>";
        setText(result3rdQ, q3Label);
        String result4thQ = "<html>" + schoolClass.getResult4thQuarter().replaceAll("\n", "<br>") + "</html>";
        setText(result4thQ, q4Label);
        String resultYear = "<html>" + schoolClass.getResultYear().replaceAll("\n", "<br>") + "</html>";
        setText(resultYear, yearLabel);
    }

    private void setText(String text, JLabel label) {
        label.setText(text);
    }

    private void chooseButtonActionPerformed(ActionEvent evt) {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
        warningLabel.setText("");
    }

    private void initGbc() {
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
    }
}
