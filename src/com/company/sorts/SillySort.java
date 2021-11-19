package com.company.sorts;

import com.company.*;

import java.awt.*;
import java.util.ArrayList;

public class SillySort extends Sort {
    /**
     * Silly Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void runSort(ArrayList<Integer> array, int low, int high, ArrayList<rect> rectangles) {
        if (low >= high)
            return;
        if (Main.interruptLoop)
            return;
        int mid = low + (high - low) / 2;
        runSort(array, low, mid, rectangles);
        runSort(array, mid + 1, high, rectangles);

        if (array.get(low) > array.get(mid + 1)) {
            Highlight.markRectangle(low, rectangles, Color.RED);
            Highlight.markRectangle(mid + 1, rectangles, Color.RED);
            swap(array, low, mid + 1);
            Lib.sleep(Main.delay);
            Highlight.markClear(low, rectangles);
            Highlight.markClear(mid + 1, rectangles);
        }
        Main.comparisons++;
        runSort(array, low + 1, high, rectangles);
    }

    /**
     * Sets the name and statistics of the sort
     */
    public static void setStatistic() {
        name = "Silly Sort";
        best = "O(?)";
        average = "O(?)";
        worst = "O(?)";
    }
}
