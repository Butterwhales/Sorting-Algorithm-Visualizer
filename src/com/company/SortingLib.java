package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class SortingLib {
    static int bogoLength = 2;

    /**
     * Checks to see if a list of numbers is sorted
     *
     * @param array List of numbers to check if they are sorted
     * @param i     Amount og the list to check if sorted
     * @param bool  bool for recursive operation
     * @return True if list is sorted
     */
    public static boolean isSorted(ArrayList<Integer> array, int i, boolean bool) {
        if (i == 0)
            return true;
        if (array.get(i) >= array.get(i - 1))
            if (isSorted(array, i - 1, false))
                bool = true;
        return bool;
    }

    /**
     * Bogosort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void bogoSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        if (SortingLib.isSorted(array, array.size() - 1, false)) {
            Main.isSorted = true;
            return;
        }
        Collections.shuffle(array);
        Lib.sleep(Main.delay);
    }

    /**
     * Bogobogosort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void bogobogoSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        if (isSorted(array, bogoLength, false)) {
            if (bogoLength == array.size() - 1) {
                Main.isSorted = true;
                return;
            } else {
                bogoLength++;
            }
        } else {
            bogoLength = 2;
        }
        Main.sortIterator += bogoLength;
        ArrayList<Integer> copy = new ArrayList<>();
        for (int i = 0; i <= bogoLength; i++) copy.add(array.get(i));
        for (int i = 0; i <= copy.size() - 1; i++)
            Highlight.markRectangle(i, rectangles, Color.RED);
        Collections.shuffle(copy);
        Lib.sleep(Main.delay);
        for (int i = 0; i <= bogoLength; i++) array.set(i, copy.get(i));
        copy.clear();
        Highlight.clearAll(rectangles);
    }

    /**
     * Insertion Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param i          Iterator
     * @param rectangles List of rectangles for highlighting
     */
    public static void insertionSort(ArrayList<Integer> array, int i, ArrayList<rect> rectangles) {
        if (i < array.size())
            Highlight.markRectangle(i, rectangles, Color.RED);
        if (SortingLib.isSorted(array, array.size() - 1, false)) {
            Main.isSorted = true;
            return;
        }
        int j = i;
        while (j > 0 && array.get(j - 1) > array.get(j)) {
            Highlight.markRectangle(j - 1, rectangles, Color.GREEN);
            swap(array, j - 1, j);
            Lib.sleep(Main.delay);
            Highlight.markClear(j - 1, rectangles);
            Main.operations += 2;
            j--;
        }
        Highlight.markClear(i, rectangles);
        Main.sortIterator++;
    }

    /**
     * Selection Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param i          Iterator
     * @param rectangles List of rectangles for highlighting
     */
    public static void selectionSort(ArrayList<Integer> array, int i, ArrayList<rect> rectangles) {
        if (SortingLib.isSorted(array, array.size() - 1, false)) {
            Main.isSorted = true;
            return;
        }
        int lowIndex = i;
        for (int j = i + 1; j < array.size(); j++) {
            Highlight.markRectangle(j, rectangles, Color.RED);
            Lib.sleep(Main.delay);
            if (array.get(j) < array.get(lowIndex)) {
                lowIndex = j;
            }
            Highlight.markClear(j, rectangles);
            Main.operations++;
        }
        if (lowIndex != i)
            swap(array, i, lowIndex);
        Main.sortIterator++;
    }

    /**
     * Bubble Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void bubbleSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        if (SortingLib.isSorted(array, array.size() - 1, false)) {
            Main.isSorted = true;
            return;
        }
        for (int i = 0; i < array.size() - 1; i++) {
            if (array.get(i) > array.get(i + 1)) {
                swap(array, i, i + 1);
                Main.operations++;
            }

            Highlight.markRectangle(i, rectangles, Color.RED);
            Highlight.markRectangle(i + 1, rectangles, Color.RED);
            Lib.sleep(Main.delay);
            Highlight.markClear(i, rectangles);
            Highlight.markClear(i + 1, rectangles);
        }
    }

    /**
     * Swaps two numbers in a list
     *
     * @param array  List of numbers
     * @param index1 index of item 1
     * @param index2 index of item 2
     */
    private static void swap(ArrayList<Integer> array, int index1, int index2) {
        if (array == null) return;
        int n = array.get(index1);
        int m = array.get(index2);
        array.set(index2, n);
        array.set(index1, m);
    }


}
