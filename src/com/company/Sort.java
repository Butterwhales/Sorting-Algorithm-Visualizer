package com.company;

import com.company.sorts.BogoSort;
import com.company.sorts.BubbleSortFlag;

import javax.swing.*;
import java.util.ArrayList;

public class Sort {
//    String name;
//    String best;
//    String average;
//    String worst;

    /**
     * Checks to see if a list of numbers is sorted
     *
     * @param array List of numbers to check if they are sorted
     * @param i     Amount og the list to check if sorted
     * @param bool  bool for recursive operation
     * @return True if list is sorted
     */
    protected static boolean isSorted(ArrayList<Integer> array, int i, boolean bool) {
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
    protected static void swap(ArrayList<Integer> array, int index1, int index2) {
        if (array == null) return;
        int n = array.get(index1);
        int m = array.get(index2);
        array.set(index2, n);
        array.set(index1, m);
        Main.swaps++;
    }

    /**
     * Sets the statistics and name in the info panel
     *
     * @param sort              Name of sort
     * @param algorithmLabel    Label for algorithm name
     * @param bestLabel         Label for the best performance
     * @param averageLabel      Label for the average performance
     * @param worstLabel        Label for the worst performance
     */
    protected static void setStatistics(String sort, JLabel algorithmLabel, JLabel bestLabel, JLabel averageLabel, JLabel worstLabel){
        //Sort sortThing = getSort(sort);
        //sortThing.name;

        switch (sort) {
            case "bogosort" -> {
                algorithmLabel.setText("Bogosort");
                bestLabel.setText("O(1)");
                averageLabel.setText("O((n+1)!)");
                worstLabel.setText("O(I)");
            }
            case "bogobogosort" -> {
                algorithmLabel.setText("Bogobogosort");
                bestLabel.setText("O(n-1)");
                averageLabel.setText("O(n*(n!)^n)");
                worstLabel.setText("O(I)");
            }
            case "insertion sort" -> {
                algorithmLabel.setText("Insertion Sort");
                bestLabel.setText("O(n)");
                averageLabel.setText("O(n^2)");
                worstLabel.setText("O(n^2)");
            }
            case "selection sort" -> {
                algorithmLabel.setText("Selection Sort");
                bestLabel.setText("O(n^2)");
                averageLabel.setText("O(n^2)");
                worstLabel.setText("O(n^2)");
            }
            case "bubble sort" -> {
                algorithmLabel.setText("Bubble Sort");
                bestLabel.setText("O(n^2)");
                averageLabel.setText("O(n^2)");
                worstLabel.setText("O(n^2)");
            }
            case "bubble sort flag" -> {
                algorithmLabel.setText("Bubble Sort Flag");
                bestLabel.setText("O(n^2)");
                averageLabel.setText("O(n^2)");
                worstLabel.setText("O(n^2)");
            }
            case "merge sort" -> {
                algorithmLabel.setText("Merge Sort");
                bestLabel.setText("O(n log n)");
                averageLabel.setText("O(n log n)");
                worstLabel.setText("O(n log n)");
            }
            case "shell sort" -> {
                algorithmLabel.setText("Shell Sort");
                bestLabel.setText("O(n log n)");
                averageLabel.setText("O(n log n)");
                worstLabel.setText("O(n log n)");
            }
            case "cocktail shaker sort" -> {
                algorithmLabel.setText("Cocktail Shaker Sort");
                bestLabel.setText("O(n)");
                averageLabel.setText("O(n^2)");
                worstLabel.setText("O(n^2)");
            }
            default -> {
                algorithmLabel.setText("NULL");
                bestLabel.setText("NULL");
                averageLabel.setText("NULL");
                worstLabel.setText("NULL)");
                System.out.println("No Statistics yet for this");
            }
        }
    }

    private static Sort getSort(String sort) {
        switch (sort){
            case "bogosort" -> {
                return new BogoSort();
            }
            case "bubble sort flag" -> {
                return new BubbleSortFlag();
            }
            default -> throw new IllegalStateException("Unexpected value: " + sort);
        }
    }
}
