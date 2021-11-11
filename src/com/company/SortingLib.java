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
        if (array.get(i) >= array.get(i - 1)) {
            Main.comparisons++;
            if (isSorted(array, i - 1, false))
                bool = true;
        }
        return bool;
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
        Main.swaps++;
    }

    /**
     * Bogosort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void bogoSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        while (!Main.isSorted) {
            if (SortingLib.isSorted(array, array.size() - 1, false)) {
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
     * Bogobogosort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void bogobogoSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        while (!Main.isSorted) {
            if (isSorted(array, bogoLength, false)) {
                if (bogoLength == array.size() - 1) {
                    Main.isSorted = true;
                    return;
                } else
                    bogoLength++;
            } else
                bogoLength = 1;
            Main.sortIterator += bogoLength;
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
     * Insertion Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void insertionSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        for (int i = 0; i < array.size(); i++) {
            Highlight.markRectangle(i, rectangles, Color.RED);
            int pos = i;
            while (pos > 0 && array.get(pos - 1) > array.get(pos)) {
                Highlight.markRectangle(pos - 1, rectangles, Color.GREEN);
                swap(array, pos - 1, pos);
                Lib.sleep(Main.delay);
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
     * Selection Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void selectionSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        for (int i = 0; i < array.size() - 1; i++) {
            int lowIndex = i;
            for (int j = i + 1; j < array.size(); j++) {
                Highlight.markRectangle(j, rectangles, Color.RED);
                Lib.sleep(Main.delay);
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
     * Bubble Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void bubbleSort(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        while (!Main.isSorted) {
            for (int i = 0; i < Main.sortIterator - 1; i++) {
                if (array.get(i) > array.get(i + 1)) {
                    swap(array, i, i + 1);
                }
                Main.comparisons++;
                Highlight.markRectangle(i, rectangles, Color.RED);
                Highlight.markRectangle(i + 1, rectangles, Color.RED);
                Lib.sleep(Main.delay);
                Highlight.markClear(i, rectangles);
                Highlight.markClear(i + 1, rectangles);
            }
            if (Main.sortIterator == 0)
                Main.isSorted = true;
            Main.sortIterator--;

            if (Main.interruptLoop)
                return;
        }
    }

    /**
     * Bubble Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param rectangles List of rectangles for highlighting
     */
    public static void bubbleSortFlag(ArrayList<Integer> array, ArrayList<rect> rectangles) {
        while (!Main.isSorted) {
            Main.sortIterator++;
            boolean bool = false;
            for (int i = 0; i < array.size() - Main.sortIterator; i++) {
                if (array.get(i) > array.get(i + 1)) {
                    swap(array, i, i + 1);
                    bool = true;
                }
                Main.comparisons++;
                Highlight.markRectangle(i, rectangles, Color.RED);
                Highlight.markRectangle(i + 1, rectangles, Color.RED);
                Lib.sleep(Main.delay);
                Highlight.markClear(i, rectangles);
                Highlight.markClear(i + 1, rectangles);
            }
            if (!bool)
                Main.isSorted = true;

            if (Main.interruptLoop)
                return;
        }
    }

    /**
     * Merge Sort Algorithm
     *
     * @param array      List of numbers to be sorted
     * @param left       Right-value of sub array
     * @param right      Left-value of sub array
     * @param rectangles List of rectangles for highlighting
     */
    public static void mergeSort(ArrayList<Integer> array, int left, int right, ArrayList<rect> rectangles) {
        if (left < right) {
            int mid = (left + (right - 1)) / 2;

            if (Main.interruptLoop)
                return;
            mergeSort(array, left, mid, rectangles);
            mergeSort(array, mid + 1, right, rectangles);

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
