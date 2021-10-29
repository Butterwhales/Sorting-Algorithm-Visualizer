package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main extends JFrame {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;
    private static final int DELAY = 150;
    static ArrayList<Integer> array = Lib.generateNumbers(15);
    static ArrayList<JPanel> rectangles = new ArrayList<>();
    static JPanel sortPanel;
    static int operations = 1;
    static int sortIterator = 1;

    public static void main(String[] args) {
        //Frame Creation
        JFrame frame = new JFrame("Sorting Algorithm Visualizer");
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        background.setBackground(Color.DARK_GRAY);
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.setBackground(Color.DARK_GRAY);
        infoPanel.setMaximumSize(new Dimension(40000, 100));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        sortPanel = Lib.createPanel("sortPanel", mainPanel);
        JPanel buttonPanel = Lib.createPanel("buttonPanel", mainPanel);

        //Info Panel
        JLabel algorithmLabel = Lib.createLabel("Bogosort", infoPanel, Component.LEFT_ALIGNMENT);
        infoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        Lib.createLabel("Best:", infoPanel, Component.LEFT_ALIGNMENT);
        JLabel bestLabel = Lib.createLabel("O(1)", infoPanel, Component.LEFT_ALIGNMENT);
        Lib.createLabel("Average:", infoPanel, Component.LEFT_ALIGNMENT);
        JLabel AverageLabel = Lib.createLabel("O((n+1)!)", infoPanel, Component.LEFT_ALIGNMENT);
        Lib.createLabel("Worst:", infoPanel, Component.LEFT_ALIGNMENT);
        JLabel worstLabel = Lib.createLabel("O(i)", infoPanel, Component.LEFT_ALIGNMENT);

        infoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        Lib.createLabel("Operations:", infoPanel, Component.LEFT_ALIGNMENT);
        JLabel operationsLabel = Lib.createLabel("0", infoPanel, Component.LEFT_ALIGNMENT);

        //Button Panel
        JButton sortButton = Lib.createButton("Sort", buttonPanel, Component.CENTER_ALIGNMENT);
        JButton stopButton = Lib.createButton("Stop", buttonPanel, Component.CENTER_ALIGNMENT);
        JButton randButton = Lib.createButton("Randomize", buttonPanel, Component.CENTER_ALIGNMENT);
        Lib.createLabel("Delay ms", buttonPanel, Component.CENTER_ALIGNMENT);

        int delay = 10;
        SpinnerNumberModel delaySpinnerModel = new SpinnerNumberModel(delay, 0, 10000, 100);
        JSpinner delaySpinner = new JSpinner(delaySpinnerModel);
        delaySpinner.setForeground(Color.WHITE);
        delaySpinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        delaySpinner.setMaximumSize(new Dimension(200, 20));
        delaySpinner.getModel().setValue(DELAY);
        buttonPanel.add(delaySpinner);

        Lib.createLabel("Algorithm", buttonPanel, Component.CENTER_ALIGNMENT);

        String[] algorithms = {"Bogosort", "Insertion Sort"};
        JComboBox<String> sortDropdown = new JComboBox<>(algorithms);
        //sortDropdown.setForeground(Color.WHITE);
        sortDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        sortDropdown.setMaximumSize(new Dimension(200, 20));
        buttonPanel.add(sortDropdown);
        //

        Lib.createLabel("Items", buttonPanel, Component.CENTER_ALIGNMENT);

        SpinnerNumberModel rectSpinnerModel = new SpinnerNumberModel(array.size(), 2, 10000, 1);
        JSpinner rectSpinner = new JSpinner(rectSpinnerModel);
        rectSpinner.setForeground(Color.WHITE);
        rectSpinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        rectSpinner.setMaximumSize(new Dimension(200, 20));
        buttonPanel.add(rectSpinner);

        //Frame
        mainPanel.setBackground(Color.darkGray);
        background.add(infoPanel);
        background.add(mainPanel);
        frame.add(background);
        frame.setMinimumSize(new Dimension(300, 400));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.pack();
        frame.setVisible(true);
        frame.revalidate();
        Lib.drawRectangles(array, sortPanel, rectangles);
        System.out.println(buttonPanel.getWidth());
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Lib.repaint(rectangles, sortPanel.getWidth(), sortPanel.getHeight(), array);
            }
        });

        Timer t = new Timer((Integer) delaySpinner.getModel().getValue(), new ActionListener() {



            @Override
            public void actionPerformed(ActionEvent evt) {
                //operations = 0;
                Lib.highlightRectangle(sortPanel, rectangles, sortIterator - 1, array, Color.BLUE);
                if (!SortingLib.isSorted(array, array.size() - 1, false)) {
                    algorithmLabel.setText(sortDropdown.getModel().getSelectedItem().toString());
                    switch (sortDropdown.getSelectedItem().toString().toLowerCase()) {
                        case "bogosort":
                            SortingLib.bogoSort(array);
                            break;
                        case "insertion sort":
                            sortIterator = SortingLib.insertionSort(array, sortIterator, rectangles, (Integer) delaySpinner.getModel().getValue(), sortPanel);
                            Lib.highlightRectangle(sortPanel, rectangles, sortIterator - 1, array, Color.GREEN);
                            break;
                        default:
                            System.out.println("Not a valid sorting algorithm");
                            Timer self = (Timer) evt.getSource();
                            self.stop();
                            break;
                    }
                    //System.out.println("Shuffled");
                    Lib.repaint(rectangles, sortPanel.getWidth(), sortPanel.getHeight(), array);
                    //sortPanel.setMaximumSize(new Dimension((int) (1.5* rectangles.size()),sortPanel.getHeight()));
                    operationsLabel.setText(String.valueOf(operations));
                    sortPanel.revalidate();
                    //java.awt.Toolkit.getDefaultToolkit().beep();
                    operations++;
                } else {
                    operations = 0;
                    sortIterator = 1;
                    Timer self = (Timer) evt.getSource();
                    self.stop();
                }
            }
        });
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //t.setDelay((Integer) delaySpinner.getModel().getValue());
                t.start();
            }
        });
        randButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.stop();
                Collections.shuffle(array);
                Lib.repaint(rectangles, sortPanel.getWidth(), sortPanel.getHeight(), array);
                sortPanel.revalidate();
                operationsLabel.setText("0");
                operations = 0;
                Lib.highlightRectangle(sortPanel, rectangles, sortIterator - 1, array, Color.BLUE);
                sortIterator = 1;
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.stop();
            }
        });
        delaySpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                t.setDelay((Integer) delaySpinner.getModel().getValue());
            }
        });
        rectSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int spinnerVal = (Integer) rectSpinner.getModel().getValue();
                array = Lib.generateNumbers(spinnerVal);
                sortPanel.removeAll();
                rectangles.clear();
                sortPanel.repaint();
                if (spinnerVal > 15) frame.setMinimumSize(new Dimension(100 + (spinnerVal * 5), 400));
                else frame.setMinimumSize(new Dimension(300, 400));
                Lib.drawRectangles(array, sortPanel, rectangles);
            }
        });
        sortDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (sortDropdown.getSelectedItem().toString().toLowerCase()) {
                    case "bogosort":
                        algorithmLabel.setText("Bogosort");
                        bestLabel.setText("O(1)");
                        AverageLabel.setText("O((n+1)!)");
                        worstLabel.setText("O(I)");
                        break;
                    case "insertion sort":
                        algorithmLabel.setText("Insertion Sort");
                        bestLabel.setText("O(n)");
                        AverageLabel.setText("O(n^2)");
                        worstLabel.setText("O(n^2)");
                        break;
                }
            }
        });

        //Lib.highlightRectangle(rectangles, 5);
    }
}


