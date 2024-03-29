package com.yq.sort.quicksort;

import com.yq.sort.Sort;

public class QuickSort extends Sort {
    /**
     * 快排测试
     * @param arr 数组
     * @throws Exception
     */
    public static void quickSortTest(int[] arr) {
        try {
            printArr(arr);
            quickSort(arr, 0, arr.length - 1);
            printArr(arr);
            System.out.println(checkArrOrderly(arr));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 快速排序递归方法
     * @param arr 待排序数组
     * @param i 左下标
     * @param j 右下标
     * @throws Exception
     */
    private static void quickSort(int[] arr, int i, int j) throws Exception {
        checkIndex(arr, i, j);
        // p: 标志位
        // l: 左指针
        // r: 右指针
        int l = i, r = j, p = i;

        while (l < r) {
            // 判断右下标所在值与标志位之间的大小，如果不小于标志位则右下标左移
            while (l < r && arr[r] >= arr[p]) {
                r--;
            }

            // 判断左下标所在值与标志位之间的大小，如果不大于标志位则左下标右移
            while (l < r && arr[l] <= arr[p]) {
                l++;
            }

            // 交换数组中左右下标所对应的值
            if(l < r) {
                swap(arr, l, r);
            }
            // 当 l == r 时，将标志位与指针l所在值互换
            else {
                swap(arr, l, p);
            }
        }

        //递归
        if(i < l - 1) {
            quickSort(arr, i, l - 1);
        }

        if(j > l + 1) {
            quickSort(arr, l + 1, j);
        }
    }
}
