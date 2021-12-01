package com.company.sorts;

import com.company.*;

import java.awt.*;
import java.util.ArrayList;

//Used https://en.wikipedia.org/wiki/Shellsort and https://www.geeksforgeeks.org/shellsort/ as reference

public class ShellSort extends Sort {
    /**
     * Shell Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void runSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        //Add standard gaps?
        for (int gap = (array.size()-1) / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.size(); i++) {
                int temp = array.get(i);
                int j;
                for (j = i; j >= gap && temp < array.get(j - gap); j -= gap) {
                    array.set(j, array.get(j - gap));
                    Main.comparisons++;
                    Highlight.markRectangle(j, rectangles, Color.GREEN);
                    Sort.sleep(Main.delay);
                    Highlight.markClear(j, rectangles);
                }
                Main.swaps++;
                array.set(j, temp);
            }
        }
    }

    /**
     * Sets the name and statistics of the sort
     */
    public static void setStatistic(){
        name = "Shell Sort";
        best = "O(n log n)";
        average = "O(n log n)";
        worst = "O(n log n)";
    }
}
