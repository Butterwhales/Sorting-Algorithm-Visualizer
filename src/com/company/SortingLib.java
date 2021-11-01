package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class SortingLib {
    static int bogoLength = 2;

    public static boolean isSorted(ArrayList<Integer> array, int i, boolean bool) {
        if (i == 0)
            return true;
        if (array.get(i) >= array.get(i - 1))
            if (isSorted(array, i - 1, false))
                bool = true;
        return bool;
    }

    public static void bogoSort(ArrayList<Integer> array) {
        if (SortingLib.isSorted(array, array.size() - 1, false)) {
            Main.isSorted = true;
            return;
        }
        Collections.shuffle(array);
    }

    public static void bogobogoSort(ArrayList<Integer> array) {
        if (isSorted(array, bogoLength, false)) {
            if (bogoLength == array.size()-1) {
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
        Collections.shuffle(copy);
        for (int i = 0; i <= bogoLength; i++) array.set(i, copy.get(i));
        copy.clear();
    }

    //TODO do multi threading
    public static void insertionSort(ArrayList<Integer> array, int i, ArrayList<rect> rectangles, int delay, JPanel sortPanel) {
        Lib.highlightRectangle(sortPanel, rectangles, i, array, Color.GREEN);
        if (SortingLib.isSorted(array, array.size() - 1, false)) {
            Main.isSorted = true;
            return;
        }
        int j = i;
        while (j > 0 && array.get(j - 1) > array.get(j)) {
            Lib.highlightRectangle(sortPanel, rectangles, j, array, Color.RED);
            int n = array.get(j - 1);
            int m = array.get(j);
            array.set(j, n);
            array.set(j - 1, m);
            Lib.highlightRectangle(sortPanel, rectangles, j, array, Color.BLUE);
            Main.operations += 2;
            j--;
        }
        Lib.highlightRectangle(sortPanel, rectangles, i, array, Color.BLUE);
        Main.sortIterator++;
        //return i;
//        if (n > 0){
//            insertionSort(array, n-1, rectangles);
//            int x = array.get(n);
//            int j = n-1;
//            while (j >= 0 && array.get(j) > x) {
//                array.set(j+1, array.get(j));
//                j--;
//            }
//            array.set(j+1, x);
//        }
    }

    static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
