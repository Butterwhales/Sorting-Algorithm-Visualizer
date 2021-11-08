package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Highlight {
    static final Color normColor = Color.BLUE;

    public static void markRectangle(int rectPos, ArrayList<rect> rectangles, Color markColor) {
        rectangles.get(rectPos).setColor(markColor);
    }

    public static void markClear(int rectPos, ArrayList<rect> rectangles) {
        rectangles.get(rectPos).setColor(normColor);
    }

    public static void clearAll(ArrayList<rect> rectangles) {
        for (int i = 0; i <= rectangles.size() - 1; i++) {
            rectangles.get(i).setColor(normColor);
        }
    }
}
