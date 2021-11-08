package com.company;

import static com.company.Main.rectangles;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class DisplayGraphics extends Canvas {
    int panelHeight = 0;

    /**
     * Paints rectangles to canvas (Called with the repaint command)
     *
     * @param g Canvas
     */
    public void paint(Graphics g) {
        super.paint(g);
        int i = 0;
        for (rect rectangle : rectangles) {
            g.setColor(rectangle.getColor());
            g.fillRect(rectangle.getOffset() + (rectangle.getWidth() * i), panelHeight - rectangle.getHeight(), rectangle.getWidth(), rectangle.getHeight());
            i++;
        }
    }

    /**
     * Updates the canvas
     *
     * @param g Canvas
     */
    public void update(Graphics g) {
        Image doubleBuffer;
        Dimension d = getSize();
        Graphics g2;
        doubleBuffer = createImage(d.width, d.height);
        g2 = doubleBuffer.getGraphics();
        Rectangle box = g.getClipBounds();

        g2.translate(-box.x, -box.y);
        paint(g2);
        g.drawImage(doubleBuffer, box.x, box.y, this);
    }

    /**
     * Rescales the rectangles to fit screen size and adjusts height if changed
     *
     * @param width   Width of canvas
     * @param height  Height of canvas
     * @param numbers List of numbers for rectangles height
     */
    public void repaintRect(int width, int height, ArrayList<Integer> numbers) {
        int numOfBars = rectangles.size();
        int largestNum = Collections.max(numbers);
        double scaleY = (double) height / largestNum;
        double scaleX = (double) width / numOfBars;
        for (int i = 0; i < numOfBars; i++) {
            rect rectangle = rectangles.get(i);
            rectangle.setMaximumSize((int) scaleX, (int) Math.floor(scaleY * numbers.get(i)));
        }
        panelHeight = height;
        repaint();
    }

    /**
     * Paints the rectangles for the first time
     *
     * @param width   Width of screen
     * @param height  Height of screen
     * @param numbers List of numbers for rectangles height
     */
    public void paintRect(int width, int height, ArrayList<Integer> numbers) {
        int numOfBars = rectangles.size();
        int largestNum = Collections.max(numbers);
        double scaleY = (double) height / largestNum;
        double scaleX = (double) width / numOfBars;
        for (int num : numbers) {
            rect rectangle = new rect();
            rectangle.setMaximumSize((int) scaleX, (int) Math.floor(scaleY * num));
            rectangle.setColor(Color.BLUE);
            rectangles.add(rectangle);
        }
        panelHeight = height;
        repaint();
    }
}
