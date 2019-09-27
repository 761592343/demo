package com.yq.sort.heapsort;

import com.yq.sort.util.SortUtil;

public class HeapSort {
    /**
     * 判断节点是否有左孩子
     * @param arr 数组
     * @param i 下标
     * @return boolean
     * @throws Exception
     */
    private static boolean hasLeftChild(int[] arr, int i) throws Exception {
        SortUtil.checkIndex(arr, i, 0);
        return 2 * (i + 1) <= arr.length;
    }

    /**
     * 判断节点是否有右孩子
     * @param arr 数组
     * @param i 下标
     * @return boolean
     * @throws Exception
     */
    private static boolean hasRightChild(int[] arr, int i) throws Exception {
        SortUtil.checkIndex(arr, i, 0);
        return 2 * (i + 1) + 1 <= arr.length;
    }
}
