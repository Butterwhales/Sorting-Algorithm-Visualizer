package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Highlight {
    static final Color markColor = Color.GREEN;
    static final Color normColor = Color.BLUE;

    public static void markRectangle(int rectPos, ArrayList<rect> rectangles){
        rectangles.get(rectPos).setColor(markColor);
    }

    public static void markClear(int rectPos, ArrayList<rect> rectangles) {
        rectangles.get(rectPos).setColor(normColor);
    }
}
