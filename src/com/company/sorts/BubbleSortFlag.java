package com.company.sorts;

import com.company.*;

import java.awt.*;
import java.util.ArrayList;

public class BubbleSortFlag extends Sort {
//    public String name = "Bubble Sort Flag";
//    public String best = "O(n^2)";
//    public String average = "O(n^2)";
//    public String worst = "O(n^2)";

    static int sortIterator;
    /**
     * Bubble Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void runSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        sortIterator = 0;
        while (!Main.isSorted) {
            sortIterator++;
            boolean flag = false;
            for (int i = 0; i < array.size() - sortIterator; i++) {
                if (array.get(i) > array.get(i + 1)) {
                    swap(array, i, i + 1);
                    flag = true;
                }
                Main.comparisons++;
                Highlight.markRectangle(i, rectangles, Color.RED);
                Highlight.markRectangle(i + 1, rectangles, Color.RED);
                Lib.sleep(Main.delay);
                Highlight.markClear(i, rectangles);
                Highlight.markClear(i + 1, rectangles);
            }
            if (!flag)
                Main.isSorted = true;

            if (Main.interruptLoop)
                return;
        }
    }
}
