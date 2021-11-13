package com.company.sorts;

import com.company.*;

import java.awt.*;
import java.util.ArrayList;

public class ShellSort extends Sort {
    /**
     * Shell Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void runSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        //Add standard gaps?
        for (int gap = (array.size() - 1) / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.size() - 1; i++) {
                int temp = array.get(i);
                int j;
                for (j = i; j >= gap && temp < array.get(j - gap); j -= gap) {
                    array.set(j, array.get(j - gap));
                    Main.comparisons++;
                    Highlight.markRectangle(j, rectangles, Color.GREEN);
                    Lib.sleep(Main.delay);
                    Highlight.markClear(j, rectangles);
                }
                Main.swaps++;
                array.set(j, temp);
            }
        }
    }
}
