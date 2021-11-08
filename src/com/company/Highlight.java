package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Highlight {
    static final Color normColor = Color.BLUE;

    /**
     * Highlights a rectangle with a provided color
     *
     * @param rectPos    Position to highlight
     * @param rectangles List of rectangles
     * @param markColor  Color to mark rectangle
     */
    public static void markRectangle(int rectPos, ArrayList<rect> rectangles, Color markColor) {
        rectangles.get(rectPos).setColor(markColor);
    }

    /**
     * Clears a highlight at a defined position
     *
     * @param rectPos    Position to clear highlight
     * @param rectangles List of rectangles
     */
    public static void markClear(int rectPos, ArrayList<rect> rectangles) {
        rectangles.get(rectPos).setColor(normColor);
    }

    /**
     * Clears all highlights in a list of rectangles
     *
     * @param rectangles List of rectangles
     */
    public static void clearAll(ArrayList<rect> rectangles) {
        for (int i = 0; i <= rectangles.size() - 1; i++) {
            rectangles.get(i).setColor(normColor);
        }
    }
}
