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
     * @param numbers    Numbers for the heights of the rectangles
     * @param panel      The panel the rectangles will be placed on
     * @param rectangles Arraylist of already placed rectangles
     */
//    public static void drawRectangles(ArrayList<Integer> numbers, JPanel panel, ArrayList<rect> rectangles) {
//        int numOfBars = numbers.size();
//        int largestNum = Collections.max(numbers);
//        //System.out.println(height + " " + largestNum + " " + (height / 2) / largestNum);
//        double scaleY = (double) (panel.getHeight() - 96) / largestNum;
//        double scaleX = (double) (panel.getWidth() - 40) / numOfBars;
//        System.out.println(" Panel Dims: " + panel.getHeight() + " " + panel.getWidth() + " Larg Rect: " + largestNum + " scaleY: " + scaleY + " scaleX:" + scaleX + " Dimension: " + new Dimension((int) Math.floor(scaleX), (int) Math.floor(scaleY)));
//        int i = 0;
//        for (int num : numbers) {
//            rect rectangle = new rect();
//            rectangle.setColor(Color.BLUE);
////            if (numOfBars > 50){
////                rectangle.setMaximumSize(new Dimension(1, (panel.getHeight() / largestNum) * num));
////            }else
//            rectangle.setMaximumSize((int) 1, (int) Math.floor(scaleY * num));
//            if (i == 191) rectangle.setColor(Color.RED);
//            //rectangle.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//            //rectangle.setAlignmentY(Component.BOTTOM_ALIGNMENT);
//            //panel.add(rectangle);
//            rectangles.add(rectangle);
//            panel.revalidate();
//            i++;
//        }
//    }

    /**
     * @param rectangles Arraylist of rectangles
     * @param width      Width of panel the rectangles are on
     * @param height     Height of panel the rectangles are on
     * @param numbers    Numbers for the heights of the rectangles
     */
//    public static void paint(ArrayList<rect> rectangles, int width, int height, ArrayList<Integer> numbers) {
//        int numOfBars = rectangles.size();
//        int largestNum = Collections.max(numbers);
//        double scaleY = (double) (height - 96) / largestNum;
//        double scaleX = (double) (width - 20) / numOfBars;
//        for (int i = 0; i < numOfBars; i++) {
//            rect rectangle = rectangles.get(i);
////            if (numOfBars > 50){
////                rectangle.setMaximumSize(new Dimension(1, (height / largestNum) * numbers.get(i)));
////            }else
//            rectangle.setMaximumSize((int) 1, (int) Math.floor(scaleY * numbers.get(i)));
//            if (i == 191) rectangle.setColor(Color.RED);
//
//
//        }
//    }

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
