package com.yq.sort.mergesort;

import com.yq.sort.Sort;

public class MergeSort extends Sort {

    /**
     * 堆排序测试
     * @param arr 数组
     * @throws Exception
     */
    public static void mergeSortTest(int[] arr) {
        try {
            printArr(arr);
            mergeSort(arr, 0, arr.length - 1);
            printArr(arr);
            System.out.println(checkArrOrderly(arr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 归并排序，分而治之的思想
     * @param arr 待排序数组
     * @param i 待排序数组左下标
     * @param j 待排序数组右下标
     * @throws Exception
     */
    private static void mergeSort(int[] arr, int i, int j) throws Exception {
        checkIndex(arr, i, j);

        if(i == j) {
            return;
        }

        int center = (i + j) / 2;
        // 递归调用，分而治之
        mergeSort(arr, i, center);
        mergeSort(arr, center + 1, j);
        int[] arr1 = new int[center - i + 1];
        int[] arr2 = new int[j - center];
        System.arraycopy(arr, i, arr1, 0, arr1.length);
        System.arraycopy(arr, center + 1, arr2, 0, arr2.length);
        // p1: 数组arr1的指针，p2: 数组arr2的指针
        int p1 = 0, p2 = 0;

        while (p1 < arr1.length || p2 < arr2.length) {
            // 当只有arr1中有剩余时直接将剩余值拷贝到arr中并返回
            if(p2 >= arr2.length) {
                System.arraycopy(arr1, p1, arr, i, j - i + 1);
                break;
            }

            // 当只有arr2中有剩余时直接将剩余值拷贝到arr中并返回
            if(p1 >= arr1.length) {
                System.arraycopy(arr2, p2, arr, i, j - i + 1);
                break;
            }

            // arr1和arr2中都有值，从p1和p2所对应值中选择较小的一个放到arr中
            arr[i ++] = arr1[p1] <= arr2[p2] ? arr1[p1 ++] : arr2[p2 ++];
        }
    }
}
