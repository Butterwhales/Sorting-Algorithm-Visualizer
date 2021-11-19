package com.company.sorts;

import com.company.*;

import java.awt.*;
import java.util.ArrayList;

//Used https://en.wikipedia.org/wiki/Quicksort and https://www.geeksforgeeks.org/quick-sort/ as reference

public class QuickSort extends Sort {

    /**
     * Quicksort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param low        Lower index to start sorting from
     * @param high       Higher index to end sorting at
     * @param rectangles List of rectangles for highlighting
     */
    public static void runSort(ArrayList<Integer> array, int low, int high, ArrayList<rect> rectangles) {
        if (low < high) {
            if (Main.interruptLoop)
                return;
            int partitionIndex = partition(array, low, high, rectangles);

            runSort(array, low, partitionIndex - 1, rectangles);
            runSort(array, partitionIndex + 1, high, rectangles);
        }
    }

    /**
     * Sorts Partitions of an array
     *
     * @param array      List of numbers to be sorted
     * @param low        Lower index to start sorting from
     * @param high       Higher index to end sorting at
     * @param rectangles List of rectangles for highlighting
     * @return Index sorted to
     */
    private static int partition(ArrayList<Integer> array, int low, int high, ArrayList<rect> rectangles) {
        int pivot = array.get(high);
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (array.get(j) < pivot) {
                i++;
                Highlight.markRectangle(i, rectangles, Color.RED);
                Highlight.markRectangle(j, rectangles, Color.RED);
                Lib.sleep(Main.delay);
                swap(array, i, j);
                Highlight.markClear(i, rectangles);
                Highlight.markClear(j, rectangles);
            }
            Main.comparisons++;
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    /**
     * Sets the name and statistics of the sort
     */
    public static void setStatistic() {
        name = "Quicksort";
        best = "O(n log n)";
        average = "O(n log n)";
        worst = "O(n^2)";
    }
}
