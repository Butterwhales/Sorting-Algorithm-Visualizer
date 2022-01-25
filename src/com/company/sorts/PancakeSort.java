package com.company.sorts;

import com.company.*;

import java.awt.*;
import java.util.ArrayList;

//Used https://en.wikipedia.org/wiki/Pancake_sorting and https://www.geeksforgeeks.org/pancake-sorting/ as reference

public class PancakeSort extends Sort {
    /**
     * Pancake Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void runSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        for (int i = array.size(); i > 1; --i) {
            int maxIndex = findMax(array, i); //array.indexOf(Collections.max(array));
            if (maxIndex != i - 1) {
                flip(array, maxIndex, rectangles);
                flip(array, i - 1, rectangles);
            }
            if (Main.interruptLoop)
                return;
        }
    }

    static int findMax(ArrayList<Integer> array, int high) {
        int maxIndex = 0;
        for (int i = 0; i < high; ++i) {
            if (array.get(i) > array.get(maxIndex))
                maxIndex = i;
            Main.comparisons++;
        }
        return maxIndex;
    }

    /**
     * Flips the array from a[0...i]
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    private static void flip(ArrayList<Integer> array, int i, ArrayList<rect> rectangles) {
        for (int j = 0; j <= i; j++) {
            Highlight.markRectangle(i, rectangles, Color.RED);
            Highlight.markRectangle(j, rectangles, Color.RED);
            swap(array, j, i);
            Sort.sleep(Main.delay);
            Highlight.markClear(i, rectangles);
            Highlight.markClear(j, rectangles);
            i--;
        }
    }

    /**
     * Sets the name and statistics of the sort
     */
    public static void setStatistic() {
        name = "Pancake Sort";
        best = "O(?)";
        average = "O(?)";
        worst = "O(?)";
    }
}
