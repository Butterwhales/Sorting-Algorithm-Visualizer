package com.company.sorts;

import com.company.*;

import java.awt.*;
import java.util.ArrayList;

//Used https://en.wikipedia.org/wiki/Insertion_sort and https://www.geeksforgeeks.org/insertion-sort/ as reference

public class InsertionSort extends Sort {
    /**
     * Insertion Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void runSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        for (int i = 0; i < array.size(); i++) {
            Highlight.markRectangle(i, rectangles, Color.RED);
            int pos = i;
            while (pos > 0 && array.get(pos - 1) > array.get(pos)) {
                Highlight.markRectangle(pos - 1, rectangles, Color.GREEN);
                swap(array, pos - 1, pos);
                Sort.sleep(Main.delay);
                Highlight.markClear(pos - 1, rectangles);
                pos--;
                Main.comparisons++;
            }
            Highlight.markClear(i, rectangles);

            if (Main.interruptLoop)
                return;
        }
    }


    /**
     * Sets the name and statistics of the sort
     */
    public static void setStatistic(){
        name = "Insertion Sort";
        best = "O(n)";
        average = "O(n^2)";
        worst = "O(n^2)";
    }
}
