package com.company.sorts;

import com.company.*;

import java.awt.*;
import java.util.ArrayList;

//Used https://en.wikipedia.org/wiki/Gnome_sort as reference

public class GnomeSort extends Sort {
    /**
     * Gnome Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void runSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        int pos = 0;
        while (pos < array.size()) {
            if (pos == 0 || array.get(pos) >= array.get(pos - 1)) {
                pos++;
            } else {
                Highlight.markRectangle(pos, rectangles, Color.RED);
                Highlight.markRectangle(pos - 1, rectangles, Color.RED);
                Lib.sleep(Main.delay);
                swap(array, pos, pos - 1);
                Highlight.markClear(pos, rectangles);
                Highlight.markClear(pos - 1, rectangles);
                pos--;
            }
            Main.comparisons++;
            if (Main.interruptLoop)
                return;
        }
    }

    /**
     * Sets the name and statistics of the sort
     */
    public static void setStatistic() {
        name = "Gnome Sort";
        best = "O(n)";
        average = "O(n^2)";
        worst = "O(n^2)";
    }
}
