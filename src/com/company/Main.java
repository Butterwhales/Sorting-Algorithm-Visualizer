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
    private static final int DELAY = 0;
    static ArrayList<Integer> array = Lib.generateNumbers(5);
    static ArrayList<rect> rectangles = new ArrayList<>();
    static JPanel sortPanel;
    static int operations = 1;
    static int sortIterator = 1;
    static boolean isSorted = false;

    public static void main(String[] args) {
        //TODO put in methods
        //Frame & Panel Creation
        JFrame frame = new JFrame("Sorting Algorithm Visualizer");
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        background.setBackground(Color.DARK_GRAY);
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.setBackground(Color.DARK_GRAY);
        infoPanel.setMaximumSize(new Dimension(40000, 100));
        sortPanel = Lib.createPanel("sortPanel", background);
        JPanel buttonPanel = Lib.createPanel("buttonPanel", background);

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
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        JButton stopButton = Lib.createButton("Stop", buttonPanel, Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        JButton randButton = Lib.createButton("Randomize", buttonPanel, Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        Lib.createLabel("Delay ms", buttonPanel, Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        SpinnerNumberModel delaySpinnerModel = new SpinnerNumberModel(0, 0, 10000, 100);
        JSpinner delaySpinner = new JSpinner(delaySpinnerModel);
        delaySpinner.setForeground(Color.WHITE);
        delaySpinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        delaySpinner.setMaximumSize(new Dimension(50, 20));
        delaySpinner.getModel().setValue(DELAY);
        buttonPanel.add(delaySpinner);

        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        Lib.createLabel("Algorithm", buttonPanel, Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        String[] algorithms = {"Bogosort", "Bogobogosort", "Insertion Sort"};
        JComboBox<String> sortDropdown = new JComboBox<>(algorithms);
        //sortDropdown.setForeground(Color.WHITE);
        sortDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        sortDropdown.setMaximumSize(new Dimension(150, 20));
        sortDropdown.setSelectedIndex(1);
        buttonPanel.add(sortDropdown);

        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        Lib.createLabel("Items", buttonPanel, Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        SpinnerNumberModel rectSpinnerModel = new SpinnerNumberModel(array.size(), 1, 10000, 1);
        JSpinner rectSpinner = new JSpinner(rectSpinnerModel);
        rectSpinner.setForeground(Color.WHITE);
        rectSpinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        rectSpinner.setMaximumSize(new Dimension(50, 20));
        buttonPanel.add(rectSpinner);

        //Frame
        DisplayGraphics canvas = new DisplayGraphics();
        //canvas.repaint();
        //canvas.init();
        sortPanel.add(canvas);
        sortPanel.setDoubleBuffered(true);
        background.add(infoPanel);
        background.add(buttonPanel);
        background.add(sortPanel);
        frame.add(background);
        frame.setMinimumSize(new Dimension(array.size() + 18, 400));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.pack();
        frame.setVisible(true);
        frame.revalidate();
        int frameDiff = frame.getHeight() - sortPanel.getHeight();
        canvas.paintRect(frame.getWidth(), (frame.getHeight() - frameDiff), array);
        System.out.println(buttonPanel.getWidth());

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                canvas.repaintRect(frame.getWidth(), (frame.getHeight() - frameDiff), array);
            }
        });

        Timer t = new Timer((Integer) delaySpinner.getModel().getValue(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //operations = 0;
//                !SortingLib.isSorted(array, array.size() - 1, false)
                //Lib.highlightRectangle(sortPanel, rectangles, sortIterator - 1, array, Color.BLUE);
                if (!isSorted) {
                    algorithmLabel.setText(sortDropdown.getModel().getSelectedItem().toString());
                    switch (sortDropdown.getSelectedItem().toString().toLowerCase()) {
                        case "bogosort" -> SortingLib.bogoSort(array);
                        case "bogobogosort" -> SortingLib.bogobogoSort(array);
                        case "insertion sort" -> {
                            SortingLib.insertionSort(array, sortIterator, rectangles, (Integer) delaySpinner.getModel().getValue(), sortPanel);
                            Lib.highlightRectangle(sortPanel, rectangles, sortIterator - 1, array, Color.GREEN);
                        }
                        default -> {
                            System.out.println("Not a valid sorting algorithm");
                            Timer self = (Timer) evt.getSource();
                            self.stop();
                        }
                    }
                    canvas.repaintRect(frame.getWidth(), (frame.getHeight() - frameDiff), array);
                    //System.out.println("H: "+frame.getHeight() + " W: " + frame.getWidth());
                    operationsLabel.setText(String.valueOf(operations));
                    //java.awt.Toolkit.getDefaultToolkit().beep();
                    operations++;
                } else {
                    operations = 0;
                    sortIterator = 1;
                    isSorted = false;
                    Timer self = (Timer) evt.getSource();
                    self.stop();
                }
            }
        });
        sortButton.addActionListener(e -> {
            //t.setDelay((Integer) delaySpinner.getModel().getValue());
            t.start();
        });
        randButton.addActionListener(e -> {
            t.stop();
            Collections.shuffle(array);
            canvas.repaintRect(frame.getWidth(), (frame.getHeight() - frameDiff), array);
            sortPanel.revalidate();
            operationsLabel.setText("0");
            operations = 0;
            Lib.highlightRectangle(sortPanel, rectangles, sortIterator - 1, array, Color.BLUE);
            sortIterator = 1;
            System.out.println(frame.getWidth());
        });
        stopButton.addActionListener(e -> t.stop());
        delaySpinner.addChangeListener(e -> t.setDelay((Integer) delaySpinner.getModel().getValue()));
        rectSpinner.addChangeListener(e -> {
            t.stop();
            int spinnerVal = (Integer) rectSpinner.getModel().getValue();
            array = Lib.generateNumbers(spinnerVal);
            rectangles.clear();
            operationsLabel.setText("0");
            operations = 0;
            sortIterator = 1;

            if (spinnerVal > 15 && spinnerVal < 700) frame.setMinimumSize(new Dimension(718, 400));
            else if (spinnerVal >= 700) frame.setMinimumSize(new Dimension(spinnerVal + 18, 400));
            else frame.setMinimumSize(new Dimension(700, 400));
            canvas.paintRect(frame.getWidth(), (frame.getHeight() - frameDiff), array);
            canvas.repaintRect(frame.getWidth(), (frame.getHeight() - frameDiff), array);
        });
        sortDropdown.addActionListener(e -> {
            switch (sortDropdown.getSelectedItem().toString().toLowerCase()) {
                case "bogosort" -> {
                    algorithmLabel.setText("Bogosort");
                    bestLabel.setText("O(1)");
                    AverageLabel.setText("O((n+1)!)");
                    worstLabel.setText("O(I)");
                }
                case "bogobogosort" -> {
                    algorithmLabel.setText("Bogobogosort");
                    bestLabel.setText("O(1)");
                    AverageLabel.setText("O(n*(n!)^n)");
                    worstLabel.setText("O(I)");
                }
                case "insertion sort" -> {
                    algorithmLabel.setText("Insertion Sort");
                    bestLabel.setText("O(n)");
                    AverageLabel.setText("O(n^2)");
                    worstLabel.setText("O(n^2)");
                }
                default -> throw new IllegalStateException("Unexpected value: " + sortDropdown.getSelectedItem().toString().toLowerCase());
            }
        });

        //Lib.highlightRectangle(rectangles, 5);
    }
}


