package com.company.sorts;

import com.company.*;

import java.awt.*;
import java.util.ArrayList;

public class MergeSort extends Sort {

    /**
     * Merge Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param left       Right-value of sub array
     * @param right      Left-value of sub array
     * @param rectangles List of rectangles for highlighting
     */
    public static void runSort(ArrayList<Integer> array, int left, int right, ArrayList<rect> rectangles) {
        if (left < right) {
            int mid = (left + (right - 1)) / 2;

            if (Main.interruptLoop)
                return;
            runSort(array, left, mid, rectangles);
            runSort(array, mid + 1, right, rectangles);

            merge(array, left, mid, right, rectangles);
        }
    }

    /**
     * Merge Sort Algorithm part
     *
     * @param array      List of numbers to be sorted
     * @param left       Right-value of sub array
     * @param mid        Mid-value of sub array
     * @param right      Left-value of sub array
     * @param rectangles List of rectangles for highlighting
     */
    private static void merge(ArrayList<Integer> array, int left, int mid, int right, ArrayList<rect> rectangles) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        ArrayList<Integer> L = new ArrayList<>();
        ArrayList<Integer> R = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
            L.add(array.get(left + i));
        }
        for (int i = 0; i < n2; i++) {
            R.add(array.get(mid + 1 + i));
        }

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L.get(i) <= R.get(j)) {
                array.set(k, L.get(i));
                i++;
            } else {
                array.set(k, R.get(j));
                j++;
            }
            Main.swaps++;
            Main.comparisons++;
            Highlight.markRectangle(k, rectangles, Color.GREEN);
            Lib.sleep(Main.delay);
            Highlight.markClear(k, rectangles);
            k++;
        }

        while (i < n1) {
            array.set(k, L.get(i));
            i++;
            k++;
        }
        while (j < n2) {
            array.set(k, R.get(j));
            j++;
            k++;
        }
    }
}
