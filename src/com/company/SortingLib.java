package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class SortingLib {


    public static boolean isSorted(ArrayList<Integer> array, int i, boolean bool) {
        if (!bool) {
            if (i == 0)
                return true;
            if (array.get(i) >= array.get(i - 1))
                if (isSorted(array, i - 1, false))
                    bool = true;
        }
        return bool;
    }

    public static void bogoSort(ArrayList<Integer> array) {
        Collections.shuffle(array);
    }

    public static int insertionSort(ArrayList<Integer> array, int i, ArrayList<JPanel> rectangles, int delay, JPanel sortPanel) {
        Lib.highlightRectangle(sortPanel, rectangles, i, array, Color.GREEN);
        int j = i;
        while (j > 0 && array.get(j-1) > array.get(j)) {
            Lib.highlightRectangle(sortPanel, rectangles, j, array, Color.RED);
            int n = array.get(j - 1);
            int m = array.get(j);
            array.set(j, n);
            array.set(j-1, m);
            //wait(delay);
            //System.out.println("RED");
            Lib.highlightRectangle(sortPanel, rectangles, j, array, Color.BLUE);
            Main.operations+=2;
            j--;
        }
        //System.out.println(i);
        Lib.highlightRectangle(sortPanel, rectangles, i, array, Color.BLUE);
        i++;
        return i;
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

    static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
