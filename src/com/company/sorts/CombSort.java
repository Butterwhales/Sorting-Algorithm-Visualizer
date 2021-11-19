package com.company.sorts;

import com.company.*;

import java.awt.*;
import java.util.ArrayList;

//Used https://en.wikipedia.org/wiki/Comb_sort as reference

public class CombSort extends Sort {
    /**
     * Combsort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void runSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        int gap = array.size();
        double shrink = 1.3;
        boolean sorted = false;

        while (!sorted) {
            gap = (int) Math.floor(gap / shrink);
            if (gap <= 1) {
                gap = 1;
                sorted = true;
            }

            for (int i = 0; i + gap < array.size(); i++) {
                if (array.get(i) > array.get(i + gap)) {
                    Highlight.markRectangle(i, rectangles, Color.RED);
                    Highlight.markRectangle(i + gap, rectangles, Color.RED);
                    swap(array, i, i + gap);
                    Lib.sleep(Main.delay);
                    sorted = false;
                    Highlight.markClear(i, rectangles);
                    Highlight.markClear(i + gap, rectangles);
                }
                Main.comparisons++;
            }
            if (Main.interruptLoop)
                return;
        }
    }

    /**
     * Sets the name and statistics of the sort
     */
    public static void setStatistic() {
        name = "Comb Sort";
        best = "O(n log n)";
        average = "O(n^2)";
        worst = "O(n^2)";
    }
}
