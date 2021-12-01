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
     * Sleep for a given amount of time in milliseconds
     *
     * @param millis Amount of milliseconds
     */
    public static void sleep(double millis) {
        if (millis != 0)
            if (millis >= 1) {
                try {
                    for (int i = 0; i <= millis; i++)
                        Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                long start = System.nanoTime();
                //noinspection StatementWithEmptyBody
                while (System.nanoTime() - start < (1000000 * (millis))) ;
            }
    }

    /**
     * Sets the statistics and name in the info panel
     *
     * @param sort           Name of sort
     * @param algorithmLabel Label for algorithm name
     * @param bestLabel      Label for the best performance
     * @param averageLabel   Label for the average performance
     * @param worstLabel     Label for the worst performance
     */
    protected static void setSortingStatistics(String sort, JLabel algorithmLabel, JLabel bestLabel, JLabel averageLabel, JLabel worstLabel) {
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
            case "quicksort" -> QuickSort.setStatistic();
            case "comb sort" -> CombSort.setStatistic();
            case "gnome sort" -> GnomeSort.setStatistic();
            case "slow sort" -> SlowSort.setStatistic();
            case "silly sort" -> SillySort.setStatistic();
            case "pancake sort" -> PancakeSort.setStatistic();
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

    /**
     * Runs the corresponding sorting algorithm
     *
     * @param sortName   Name of sorting algorithm
     * @param array      List of numbers
     * @param rectangles List of rectangles
     */
    public static void runSort(String sortName, ArrayList<Integer> array, ArrayList<rect> rectangles) {
        switch (sortName) {
            case "bogosort" -> BogoSort.runSort(array, rectangles);
            case "bogobogosort" -> BogobogoSort.runSort(array, rectangles);
            case "insertion sort" -> InsertionSort.runSort(array, rectangles);
            case "selection sort" -> SelectionSort.runSort(array, rectangles);
            case "bubble sort" -> BubbleSort.runSort(array, rectangles);
            case "bubble sort flag" -> BubbleSortFlag.runSort(array, rectangles);
            case "merge sort" -> MergeSort.runSort(array, 0, array.size() - 1, rectangles);
            case "shell sort" -> ShellSort.runSort(array, rectangles);
            case "cocktail shaker sort" -> CocktailShakerSort.runSort(array, rectangles);
            case "quicksort" -> QuickSort.runSort(array, 0, array.size() - 1, rectangles);
            case "comb sort" -> CombSort.runSort(array, rectangles);
            case "gnome sort" -> GnomeSort.runSort(array, rectangles);
            case "pancake sort" -> PancakeSort.runSort(array, rectangles);
            case "slow sort" -> SlowSort.runSort(array, 0, array.size() - 1, rectangles);
            case "silly sort" -> SillySort.runSort(array, 0, array.size() - 1, rectangles);

            default -> System.out.println("Not a valid sorting algorithm");
        }
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
