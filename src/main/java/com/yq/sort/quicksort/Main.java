package com.yq.sort.quicksort;

import java.util.Random;

public class Main {
    /**
     * 快速排序递归方法
     * @param arr
     * @param i
     * @param j
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

    /**
     * 交换数组中两个值
     * @param arr
     * @param i
     * @param j
     * @throws Exception
     */
    private static void swap(int[] arr, int i, int j) throws Exception {
        if(i == j) {
            return;
        }

        checkIndex(arr, i, j);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 判断数组下标是否正确
     * @param arr
     * @param i
     * @param j
     * @throws Exception
     */
    private static void checkIndex(int[] arr, int i, int j) throws Exception {
        int len = arr.length;

        if(i < 0 || j < 0 || i > len - 1 || j > len - 1) {
            throw new Exception("数组下标溢出, i: " + i + ", j: " + j);
        }
    }

    /**
     * 判断数组是否有序
     * @param arr
     */
    private static boolean checkArr(int[] arr) {
        for (int i = 0; i < arr.length - 1; i ++) {
            if(arr[i] > arr[i + 1]) {
                return false;
            }
        }

        return true;
    }

    /**
     * 打印数组
     * @param arr
     */
    private static void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder(arr.length * 4);

        for (int i = 0; i < arr.length; i ++) {
            sb.append(arr[i]).append(" ");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) {
        int len = 20;
        int[] arr = new int[len];
        Random random = new Random();

        // 生成随机数组
        for (int i = 0; i < len - 1; i ++) {
            arr[i] = random.nextInt(1000);
        }

        printArr(arr);

        try {
            quickSort(arr, 0, len - 1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        printArr(arr);
        System.out.println(checkArr(arr));
    }
}
