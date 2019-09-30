package com.yq.sort;

import com.yq.sort.heapsort.HeapSort;
import com.yq.sort.quicksort.QuickSort;
import com.yq.sort.util.SortUtil;

import java.util.Random;

public class Main {
    /**
     * 快排测试
     * @param arr 数组
     * @throws Exception
     */
    private static void quickSortTest(int[] arr) {
        try {
            SortUtil.printArr(arr);
            int len = arr.length;
            QuickSort.quickSort(arr, 0, len - 1);
            SortUtil.printArr(arr);
            System.out.println(SortUtil.checkArrOrderly(arr));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 堆排序测试
     * @param arr 数组
     * @throws Exception
     */
    private static void heapSortTest(int[] arr) {
        try {
            SortUtil.printArr(arr);
            HeapSort.heapSort(arr, arr.length);
            SortUtil.printArr(arr);
            System.out.println(SortUtil.checkArrOrderly(arr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        int len = 50;
        int[] arr = new int[len];
        Random random = new Random();

        // 生成随机数组
        for (int i = 0; i < len - 1; i ++) {
            arr[i] = random.nextInt(1000);
        }

        // 测试排序类型
        heapSortTest(arr);
    }
}
