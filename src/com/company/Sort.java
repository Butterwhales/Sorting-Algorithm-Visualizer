package com.company;

import com.company.sorts.*;

import javax.swing.*;
import java.util.ArrayList;

public class Sort {
    protected static String name;
    protected static String best;
    protected static String average;
    protected static String worst;

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
        //TODO Figure out how to make this not a switch

        switch (sort) {
            case "bogosort" -> BogoSort.setStatistic();
            case "bogobogosort" -> BogobogoSort.setStatistic();
            case "insertion sort" -> InsertionSort.setStatistic();
            case "selection sort" -> SelectionSort.setStatistic();
            case "bubble sort" -> BubbleSort.setStatistic();
            case "bubble sort flag" -> BubbleSortFlag.setStatistic();
            case "merge sort" -> MergeSort.setStatistic();
            case "shell sort" -> ShellSort.setStatistic();
            case "cocktail shaker sort" -> CocktailShakerSort.setStatistic();
            default -> {
                name = sort;
                best = null;
                average = null;
                worst = null;
                System.out.println("No Statistics yet for this");
            }
        }
        algorithmLabel.setText(name);
        bestLabel.setText(best);
        averageLabel.setText(average);
        worstLabel.setText(worst);
    }

//    private static Sort getSort(String sort) {
//        switch (sort){
//            case "bogosort" -> {
//                return new BogoSort();
//            }
//            case "bubble sort flag" -> {
//                return new BubbleSortFlag();
//            }
//            default -> throw new IllegalStateException("Unexpected value: " + sort);
//        }
//    }
}
