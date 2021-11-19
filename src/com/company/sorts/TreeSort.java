package com.company.sorts;

import com.company.*;

import java.awt.*;
import java.util.ArrayList;

//Used https://en.wikipedia.org/wiki/Tree_sort as reference

public class TreeSort extends Sort {
    //TODO Make work
    /**
     * Tree~ Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void runSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        for (int i = 0; i <= array.size()-1; i++) {

        }
    }

    /**
     * Sets the name and statistics of the sort
     */
    public static void setStatistic() {
        name = "Tree Sort";
        best = "O(n log n)";
        average = "O(n log n)";
        worst = "O(n^2)";
    }
}
