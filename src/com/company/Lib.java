package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Lib {
    /**
     * @param name      Name of Panel
     * @param mainPanel Main Panel that the new panel will be added to
     * @return Return the created panel
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
                panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
                panel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
                panel.setMaximumSize(new Dimension(40000, 100));
                //panel.setMinimumSize(new Dimension(99, 200));
                panel.setBackground(Color.DARK_GRAY);
                break;
        }
        mainPanel.add(panel);
        return panel;
    }

    /**
     * @param sortPanel  Panel the rectangles are on
     * @param rectangles Arraylist of rectangles
     * @param highlight  Rectangle to highlight
     * @param array
     * @param color      Color of highlight
     */
    public static void highlightRectangle(JPanel sortPanel, ArrayList<rect> rectangles, int highlight, ArrayList<Integer> array, Color color) {
//        rectangles.get(highlight).setBackground(color);
//        rectangles.get(highlight).revalidate();
//        rectangles.get(highlight).repaint();
//        rectangles.get(highlight).validate();
//        rectangles.get(highlight).updateUI();
//        repaint(rectangles, sortPanel.getWidth(), sortPanel.getHeight(), array);
//        sortPanel.revalidate();
//        sortPanel.repaint();
//        sortPanel.validate();
    }

    /**
     * @param number Amount of numbers to generate
     * @return Array of numbers generated
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
     * @return Return the new button
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
     * @return Return the new label
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
