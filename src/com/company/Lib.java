package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Lib {
    /**
     * @param name      Name of Panel
     * @param mainPanel Main Panel that the new panel will be added to
     * @return          Return the created panel
     */
    public static JPanel createPanel(String name, JPanel mainPanel) {
        JPanel panel = new JPanel();
        switch (name) {
            case "sortPanel":
                panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
                panel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
                //panel.setLayout(new FlowLayout());
                panel.setMaximumSize(new Dimension(40000, 40000));
                panel.setBackground(Color.GRAY);
                break;
            case "buttonPanel":
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
                panel.setMaximumSize(new Dimension(200, 40000));
                panel.setMinimumSize(new Dimension(99, 200));
                panel.setBackground(Color.DARK_GRAY);
                break;
        }
        mainPanel.add(panel);
        return panel;
    }

    /**
     * @param numbers       Numbers for the heights of the rectangles
     * @param panel         The panel the rectangles will be placed on
     * @param rectangles    Arraylist of already placed rectangles
     */
    public static void drawRectangles(ArrayList<Integer> numbers, JPanel panel, ArrayList<JPanel> rectangles) {
        int numOfBars = numbers.size();
        int largestNum = Collections.max(numbers);
        //System.out.println(height + " " + largestNum + " " + (height / 2) / largestNum);
        for (int num : numbers) {
            JPanel rectangle = new JPanel();
            rectangle.setBackground(Color.BLUE);
            rectangle.setMaximumSize(new Dimension((panel.getWidth() / numOfBars), (panel.getHeight() / largestNum) * num));
            //rectangle.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            rectangle.setAlignmentY(Component.BOTTOM_ALIGNMENT);
            panel.add(rectangle);
            rectangles.add(rectangle);
            panel.revalidate();
        }
    }

    /**
     * @param rectangles    Arraylist of rectangles
     * @param width         Width of panel the rectangles are on
     * @param height        Height of panel the rectangles are on
     * @param numbers       Numbers for the heights of the rectangles
     */
    public static void repaint(ArrayList<JPanel> rectangles, int width, int height, ArrayList<Integer> numbers) {
        //System.out.println(numbers.size());
        int numOfBars = rectangles.size();
        int largestNum = Collections.max(numbers);
        for (int i = 0; i < numOfBars; i++) {
            JPanel rectangle = rectangles.get(i);
            rectangle.setMaximumSize(new Dimension((width/numOfBars), (height / largestNum) * numbers.get(i)));
            //TODO change to double precision somehow
            //rectangle.setMaximumSize(Dimension2D.setSize((width / (numOfBars + 1)), (height / largestNum) * numbers.get(i)));
            //rectangle.repaint();
        }
    }

    /**
     * @param sortPanel     Panel the rectangles are on
     * @param rectangles    Arraylist of rectangles
     * @param highlight     Rectangle to highlight
     * @param array
     * @param color         Color of highlight
     */
    public static void highlightRectangle(JPanel sortPanel, ArrayList<JPanel> rectangles, int highlight, ArrayList<Integer> array, Color color) {
        rectangles.get(highlight).setBackground(color);
        rectangles.get(highlight).revalidate();
        rectangles.get(highlight).repaint();
        rectangles.get(highlight).validate();
        rectangles.get(highlight).updateUI();
        repaint(rectangles, sortPanel.getWidth(), sortPanel.getHeight(), array);
        sortPanel.revalidate();
        sortPanel.repaint();
        sortPanel.validate();
    }

    /**
     * @param number        Amount of numbers to generate
     * @return              Array of numbers generated
     */
    public static ArrayList<Integer> generateNumbers(int number) {
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            array.add(i);
        }
        Collections.shuffle(array);
        return array;
    }

    /**
     * @param text      Text that will be on the button
     * @param panel     Panel the button will be added to
     * @param alignment Alignment of the button
     * @return          Return the new button
     */
    public static JButton createButton(String text, JPanel panel, float alignment) {
        JButton button = new JButton();
        button.setText(text);
        button.setAlignmentX(alignment);
        panel.add(button);
        return button;
    }

    /**
     * @param text      Text that will be on the label
     * @param panel     Panel the label will be added to
     * @param alignment Alignment of the label
     * @return          Return the new label
     */
    public static JLabel createLabel(String text, JPanel panel, float alignment) {
        JLabel label = new JLabel();
        label.setText(text);
        label.setAlignmentX(alignment);
        label.setForeground(Color.WHITE);
        panel.add(label);
        return label;
    }
}
