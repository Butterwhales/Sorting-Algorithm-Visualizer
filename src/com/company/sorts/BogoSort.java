package com.company.sorts;

import com.company.Lib;
import com.company.Main;
import com.company.Sort;
import com.company.rect;

import java.util.ArrayList;
import java.util.Collections;

//Didn't use anything for reference

public class BogoSort extends Sort {
    /**
     * Bogosort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void runSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        while (!Main.isSorted) {
            if (Sort.isSorted(array, array.size() - 1, false)) {
                Main.isSorted = true;
                return;
            }
            Collections.shuffle(array);
            Lib.sleep(Main.delay);

            if (Main.interruptLoop)
                return;
        }
    }

    /**
     * Sets the name and statistics of the sort
     */
    public static void setStatistic(){
        name = "Bogosort";
        best = "O(1)";
        average = "O((n+1)!)";
        worst = "O(I)";
    }
}
