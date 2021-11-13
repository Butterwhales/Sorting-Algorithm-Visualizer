package com.company;

import com.company.sorts.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends JFrame {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;
    public static double delay = 0.1;
    ArrayList<Integer> array = Lib.generateNumbers(250);

    static Thread sortThread;
    private Thread currentThread;
    static ArrayList<rect> rectangles = new ArrayList<>();
    JFrame frame;
    JPanel sortPanel;
    DisplayGraphics canvas = new DisplayGraphics();
    JComboBox<String> sortDropdown;
    JSpinner rectSpinner;
    JLabel comparisonsLabel, swapsLabel, algorithmLabel, bestLabel, averageLabel, worstLabel;
    int frameDiff;
    public static long swaps = 0;
    public static long comparisons = 0;
    static int sortIterator = 0;
    public static boolean isSorted = false;
    public static boolean interruptLoop = false;

    /**
     * Main method where everything starts
     */
    public Main() {
        //TODO put in methods
        //Frame & Panel Creation
        frame = new JFrame("Sorting Algorithm Visualizer");
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
        algorithmLabel = Lib.createLabel("Bogosort", infoPanel, Component.LEFT_ALIGNMENT);
        infoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        Lib.createLabel("Best:", infoPanel, Component.LEFT_ALIGNMENT);
        bestLabel = Lib.createLabel("O(1)", infoPanel, Component.LEFT_ALIGNMENT);
        Lib.createLabel("Average:", infoPanel, Component.LEFT_ALIGNMENT);
        averageLabel = Lib.createLabel("O((n+1)!)", infoPanel, Component.LEFT_ALIGNMENT);
        Lib.createLabel("Worst:", infoPanel, Component.LEFT_ALIGNMENT);
        worstLabel = Lib.createLabel("O(i)", infoPanel, Component.LEFT_ALIGNMENT);
        infoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        Lib.createLabel("Comparisons:", infoPanel, Component.LEFT_ALIGNMENT);
        comparisonsLabel = Lib.createLabel("0", infoPanel, Component.LEFT_ALIGNMENT);
        Lib.createLabel("Swaps:", infoPanel, Component.LEFT_ALIGNMENT);
        swapsLabel = Lib.createLabel("0", infoPanel, Component.LEFT_ALIGNMENT);

        //Button Panel
        JButton sortButton = Lib.createButton("Sort", buttonPanel, Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        JButton stopButton = Lib.createButton("Stop", buttonPanel, Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        JButton randButton = Lib.createButton("Randomize", buttonPanel, Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        Lib.createLabel("Delay ms", buttonPanel, Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        SpinnerNumberModel delaySpinnerModel = new SpinnerNumberModel(delay, 0, 10000, .1);
        JSpinner delaySpinner = new JSpinner(delaySpinnerModel);
        delaySpinner.setForeground(Color.WHITE);
        delaySpinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        delaySpinner.setMaximumSize(new Dimension(50, 20));
        delaySpinner.getModel().setValue(delay);
        buttonPanel.add(delaySpinner);

        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        Lib.createLabel("Algorithm", buttonPanel, Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        String[] algorithms = {"Bogosort", "Bogobogosort", "Insertion Sort", "Selection Sort", "Bubble Sort", "Bubble Sort Flag", "Merge Sort", "Shell Sort", "Cocktail Shaker Sort"};
        sortDropdown = new JComboBox<>(algorithms);
        sortDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        sortDropdown.setMaximumSize(new Dimension(150, 20));
        sortDropdown.setSelectedIndex(8);
        buttonPanel.add(sortDropdown);

        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        Lib.createLabel("Items", buttonPanel, Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        SpinnerNumberModel rectSpinnerModel = new SpinnerNumberModel(array.size(), 1, 1920, 1);
        rectSpinner = new JSpinner(rectSpinnerModel);
        rectSpinner.setForeground(Color.WHITE);
        rectSpinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        rectSpinner.setMaximumSize(new Dimension(50, 20));
        buttonPanel.add(rectSpinner);

        //Frame
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
        frameDiff = frame.getHeight() - sortPanel.getHeight();
        canvas.paintRect(frame.getWidth(), (frame.getHeight() - frameDiff), array);
        Sort.setStatistics(Objects.requireNonNull(sortDropdown.getSelectedItem()).toString().toLowerCase(), algorithmLabel, bestLabel, averageLabel, worstLabel);

        //Action Listeners
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                canvas.repaintRect(frame.getWidth(), (frame.getHeight() - frameDiff), array);
            }
        });

        sortButton.addActionListener(e -> {
            if (sortThread != null && sortThread.isAlive())
                return;
            if (sortThread == null) throw new AssertionError();
            currentThread = new Thread(sortThread);
            currentThread.start();
        });

        randButton.addActionListener(e -> {
            if (this.currentThread != null) interruptLoop = true;
            Collections.shuffle(array);
            canvas.repaintRect(frame.getWidth(), (frame.getHeight() - frameDiff), array);
            sortPanel.revalidate();
            setSwapsAndComparisons();
            sortIterator = 0;
        });

        stopButton.addActionListener(e -> {
            if (currentThread != null) interruptLoop = true;
        });

        rectSpinner.addChangeListener(e -> {
            if (currentThread != null) interruptLoop = true;
            int spinnerVal = (Integer) rectSpinner.getModel().getValue();
            array = Lib.generateNumbers(spinnerVal);
            rectangles.clear();
            setSwapsAndComparisons();
            if (spinnerVal > 15 && spinnerVal < 700) frame.setMinimumSize(new Dimension(718, 400));
            else if (spinnerVal >= 700) frame.setMinimumSize(new Dimension(spinnerVal + 18, 400));
            else frame.setMinimumSize(new Dimension(700, 400));
            canvas.paintRect(frame.getWidth(), (frame.getHeight() - frameDiff), array);
            canvas.repaintRect(frame.getWidth(), (frame.getHeight() - frameDiff), array);
        });

        sortDropdown.addActionListener(e -> {
            if (currentThread != null) interruptLoop = true;
            setSwapsAndComparisons();
            Sort.setStatistics(Objects.requireNonNull(sortDropdown.getSelectedItem()).toString().toLowerCase(), algorithmLabel, bestLabel, averageLabel, worstLabel);
        });

        //Sort Thread
        sortThread = new Thread(() -> {
            setSwapsAndComparisons();
            isSorted = false;
            interruptLoop = false;
            switch (Objects.requireNonNull(sortDropdown.getSelectedItem()).toString().toLowerCase()) {
                case "bogosort" -> BogoSort.runSort(array, rectangles);
                case "bogobogosort" -> BogobogoSort.runSort(array, rectangles);
                case "insertion sort" -> InsertionSort.runSort(array, rectangles);
                case "selection sort" -> SelectionSort.runSort(array, rectangles);
                case "bubble sort" -> BubbleSort.runSort(array, rectangles);
                case "bubble sort flag" -> BubbleSortFlag.runSort(array, rectangles);
                case "merge sort" -> MergeSort.runSort(array, 0, array.size() - 1, rectangles);
                case "shell sort" -> ShellSort.runSort(array, rectangles);
                case "cocktail shaker sort" -> CocktailShakerSort.runSort(array, rectangles);

                default -> System.out.println("Not a valid sorting algorithm");
            }
        });

        //Drawing Thread
        DecimalFormat comaFormat = new DecimalFormat("#");
        comaFormat.setGroupingUsed(true);
        comaFormat.setGroupingSize(3);

        Thread drawThread = new Thread(() -> {
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.scheduleAtFixedRate(() -> {
                canvas.repaintRect(frame.getWidth(), (frame.getHeight() - frameDiff), array);
                comparisonsLabel.setText(comaFormat.format(comparisons));
                swapsLabel.setText(comaFormat.format(swaps));
                delay = (Double) delaySpinner.getModel().getValue();
            }, 0, 20, TimeUnit.MILLISECONDS);
        });
        drawThread.start();
    }

    public void setSwapsAndComparisons() {
        comparisonsLabel.setText("0");
        swapsLabel.setText("0");
        swaps = 0;
        comparisons = 0;
    }

    public static void main(String[] args) {
        new Main();
    }
}


