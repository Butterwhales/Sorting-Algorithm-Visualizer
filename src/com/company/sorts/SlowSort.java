package com.company.sorts;

import com.company.*;

import java.awt.*;
import java.util.ArrayList;

//Used https://en.wikipedia.org/wiki/Slowsort as reference

public class SlowSort extends Sort {
    /**
     * Slow Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void runSort(ArrayList<Integer> array, int low, int high, ArrayList<rect> rectangles) {
        if (Main.interruptLoop)
            return;

        if (low >= high)
            return;
        int mid = low + (high - low) / 2;
        runSort(array, low, mid, rectangles);
        runSort(array, mid + 1, high, rectangles);

        if (array.get(high) < array.get(mid)) {
            Highlight.markRectangle(high, rectangles, Color.RED);
            Highlight.markRectangle(mid, rectangles, Color.RED);
            swap(array, high, mid);
            Lib.sleep(Main.delay);
            Highlight.markClear(high, rectangles);
            Highlight.markClear(mid, rectangles);
        }
        Main.comparisons++;
        runSort(array, low, high - 1, rectangles);
    }

    /**
     * Sets the name and statistics of the sort
     */
    public static void setStatistic() {
        name = "Slow Sort";
        best = "O(?)";
        average = "O(?)";
        worst = "O(?)";
    }
}
