package com.company.sorts;

import com.company.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class BogobogoSort extends Sort {
    static int bogoLength = 2;
    /**
     * Bogobogosort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void runSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        while (!Main.isSorted) {
            if (isSorted(array, bogoLength, false)) {
                if (bogoLength == array.size() - 1) {
                    Main.isSorted = true;
                    return;
                } else
                    bogoLength++;
            } else
                bogoLength = 1;
            ArrayList<Integer> copy = new ArrayList<>();
            for (int i = 0; i <= bogoLength; i++) copy.add(array.get(i));
            for (int i = 0; i <= copy.size() - 1; i++) Highlight.markRectangle(i, rectangles, Color.RED);
            Collections.shuffle(copy);
            Lib.sleep(Main.delay);
            for (int i = 0; i <= bogoLength; i++) array.set(i, copy.get(i));
            copy.clear();
            Highlight.clearAll(rectangles);

            if (Main.interruptLoop)
                return;
        }
    }

    /**
     * Sets the name and statistics of the sort
     */
    public static void setStatistic(){
        name = "Bogobogosort";
        best = "O(n - 1)";
        average = "O(n*(n!)^n)";
        worst = "O(I)";
    }
}
