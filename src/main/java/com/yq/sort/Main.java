package com.yq.sort;

import com.yq.sort.heapsort.HeapSort;
import com.yq.sort.mergesort.MergeSort;
import com.yq.sort.quicksort.QuickSort;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int len = 20;
        int[] arr = new int[len];
        Random random = new Random();

        // 生成随机数组
        for (int i = 0; i < len; i ++) {
            arr[i] = random.nextInt(1000);
        }

        int[] arr1 = new int[len];
        int[] arr2 = new int[len];
        int[] arr3 = new int[len];
        System.arraycopy(arr, 0, arr1, 0, len);
        System.arraycopy(arr, 0, arr2, 0, len);
        System.arraycopy(arr, 0, arr3, 0, len);

        // 测试排序类型
        QuickSort.quickSortTest(arr1);
        HeapSort.heapSortTest(arr2);
        MergeSort.mergeSortTest(arr3);
    }
}
