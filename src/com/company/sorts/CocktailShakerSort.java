package com.company.sorts;

import com.company.*;

import java.awt.*;
import java.util.ArrayList;

//Used https://en.wikipedia.org/wiki/Cocktail_shaker_sort as reference

public class CocktailShakerSort extends Sort {
    /**
     * Cocktail Shaker Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void runSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        boolean swapped = true;
        int start = 0;
        int end = array.size();
        while (swapped) {
            swapped = false;
            for (int i = start; i < end - 1; i++) {
                swapped = compareValues(array, rectangles, swapped, i);
            }
            if (!swapped) {
                break;
            }
            swapped = false;
            end--;
            for (int i = end - 1; i >= start; i--) {
                swapped = compareValues(array, rectangles, swapped, i);
            }
            start++;
        }
    }

    private static boolean compareValues(ArrayList<Integer> array, ArrayList<rect> rectangles, boolean swapped, int i) {
        if (array.get(i) > array.get(i + 1)) {
            Highlight.markRectangle(i, rectangles, Color.RED);
            Highlight.markRectangle(i + 1, rectangles, Color.RED);
            swap(array, i, i + 1);
            swapped = true;
            Lib.sleep(Main.delay);
            Highlight.markClear(i, rectangles);
            Highlight.markClear(i + 1, rectangles);
        }
        Main.comparisons++;
        return swapped;
    }

    /**
     * Sets the name and statistics of the sort
     */
    public static void setStatistic(){
        name = "Cocktail Shaker Sort";
        best = "O(n)";
        average = "O(n^2)";
        worst = "O(n^2)";
    }
}
