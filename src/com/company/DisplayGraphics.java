package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static com.company.Main.rectangles;

public class DisplayGraphics extends Canvas {
    int panelHeight = 0;

    public void paint(Graphics g) {
        super.paint(g);
        int i = 0;
        for (rect rectangle : rectangles) {
            g.setColor(Color.BLUE);
            g.fillRect(rectangle.getOffset() + (rectangle.getWidth() * i), panelHeight - rectangle.getHeight(), rectangle.getWidth(), rectangle.getHeight());
            i++;
        }
    }

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

    public void repaintRect(int width, int height, ArrayList<Integer> numbers) {
        int numOfBars = rectangles.size();
        int largestNum = Collections.max(numbers);
        double scaleY = (double) height / largestNum;
        double scaleX = (double) width / numOfBars;
        for (int i = 0; i < numOfBars; i++) {
            rect rectangle = rectangles.get(i);
            rectangle.setMaximumSize((int) scaleX, (int) Math.floor(scaleY * numbers.get(i)));
            if (i == 191) rectangle.setColor(Color.RED);
        }
        panelHeight = height;
        repaint();
    }

    public void paintRect(int width, int height, ArrayList<Integer> numbers) {
        int numOfBars = rectangles.size();
        int largestNum = Collections.max(numbers);
        double scaleY = (double) height / largestNum;
        double scaleX = (double) width / numOfBars;
        int i = 0;
        for (int num : numbers) {
            rect rectangle = new rect();
            rectangle.setMaximumSize((int) scaleX, (int) Math.floor(scaleY * num));
            if (i == 191) rectangle.setColor(Color.RED);
            rectangles.add(rectangle);
            i++;
        }
        panelHeight = height;
        repaint();
    }
}
