package com.yq.sort;

import com.yq.sort.quicksort.QuickSort;
import com.yq.sort.util.SortUtil;

import java.util.Random;

public class Main {
    private static void quickSortTest(int[] arr) throws Exception {
        int len = arr.length;
        QuickSort.quickSort(arr, 0, len - 1);
        SortUtil.printArr(arr);
        System.out.println(SortUtil.checkArr(arr));
    }


    public static void main(String[] args) {
        int len = 20;
        int[] arr = new int[len];
        Random random = new Random();

        // 生成随机数组
        for (int i = 0; i < len - 1; i ++) {
            arr[i] = random.nextInt(1000);
        }

        SortUtil.printArr(arr);

        try {
            quickSortTest(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
