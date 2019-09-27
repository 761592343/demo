package com.yq.sort.util;

public class SortUtil {
    /**
     * 交换数组中两个值
     * @param arr
     * @param i
     * @param j
     * @throws Exception
     */
    public static void swap(int[] arr, int i, int j) throws Exception {
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
    public static void checkIndex(int[] arr, int i, int j) throws Exception {
        int len = arr.length;

        if(i < 0 || j < 0 || i > len - 1 || j > len - 1) {
            throw new Exception("数组下标溢出, i: " + i + ", j: " + j);
        }
    }

    /**
     * 判断数组是否有序
     * @param arr
     */
    public static boolean checkArr(int[] arr) {
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
    public static void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder(arr.length * 4);

        for (int i = 0; i < arr.length; i ++) {
            sb.append(arr[i]).append(" ");
        }

        System.out.println(sb);
    }
}
