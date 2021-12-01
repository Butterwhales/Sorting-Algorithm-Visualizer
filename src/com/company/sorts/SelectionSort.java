package com.company.sorts;

import com.company.*;

import java.awt.*;
import java.util.ArrayList;

//Used https://en.wikipedia.org/wiki/Selection_sort and https://www.geeksforgeeks.org/selection-sort/ as reference

public class SelectionSort extends Sort {

    /**
     * Selection Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void runSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        for (int i = 0; i < array.size() - 1; i++) {
            int lowIndex = i;
            for (int j = i + 1; j < array.size(); j++) {
                Highlight.markRectangle(j, rectangles, Color.RED);
                Sort.sleep(Main.delay);
                if (array.get(j) < array.get(lowIndex))
                    lowIndex = j;
                Main.comparisons++;
                Highlight.markClear(j, rectangles);
            }
            if (lowIndex != i)
                swap(array, i, lowIndex);


            if (Main.interruptLoop)
                return;
        }
    }

    /**
     * Sets the name and statistics of the sort
     */
    public static void setStatistic(){
        name = "Selection Sort";
        best = "O(n^2)";
        average = "O(n^2)";
        worst = "O(n^2)";
    }
}
