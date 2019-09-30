package com.yq.sort.heapsort;

import com.sun.istack.internal.NotNull;
import com.yq.sort.util.SortUtil;

public class HeapSort {
    /**
     * 堆排序
     * @param arr 待排序数组
     * @param len 待排序数组长度
     * @throws Exception
     */
    public static void heapSort(int[] arr, int len) throws Exception {
        // 格式化待排序数组，将数组构造成大顶堆
        constructHeap(arr, len);

        // 利用大顶堆的特性循环拿出未排序部分的最大值，直到未排序部分长度为1
        while (-- len > 0) {
            // 将根节点与还未排序的部分的最后一个节点互换
            SortUtil.swap(arr, 0, len);
            // 将新的数组重新构造为堆
            regulateHeap(arr, 0, len);
        }
    }

    /**
     * 构造大顶堆，从最后一个非叶子节点开始将树逐步调整成堆
     * @param arr 数组，代表二叉树
     * @param len 待排序数组长度
     * @throws Exception
     */
    private static void constructHeap(int[] arr, int len) throws Exception {
        if (arr == null || arr.length < len) {
            throw new Exception("参数错误");
        }

        for (int i = len - 1; i >= 0; i --) {
            regulateHeap(arr, i, len);
        }
    }

    /**
     * 重新调整子树为大顶堆
     * @param arr 堆数组
     * @param i 子树根节点
     * @param len 待排序数组长度
     * @throws Exception
     */
    private static void regulateHeap(int[] arr, int i, int len) throws Exception {
        Integer leftChildIndex = leftChild(arr, i, len);

        // 是叶子节点，则跳过
        if(leftChildIndex == null) {
            return;
        }

        Integer rightChildIndex = rightChild(arr, i, len);

        int max = rightChildIndex == null ?
                getIndexOfMaxValue(arr, i, leftChildIndex) :
                getIndexOfMaxValue(arr, i, leftChildIndex, rightChildIndex);

        // 父节点值最大，则不用交换父子节点的值
        if(max == i) {
            return;
        }

        SortUtil.swap(arr, i, max);
        regulateHeap(arr, max, len);
    }

    /**
     * 判断节点是否有左孩子
     * @param arr 数组
     * @param i 下标
     * @param len 待排序数组长度
     * @return Integer 左孩子下标，若不存在则返回null
     * @throws Exception
     */
    private static Integer leftChild(int[] arr, int i, int len) throws Exception {
        SortUtil.checkIndex(arr, i, 0);
        int leftChildIndex = 2 * (i + 1) - 1;
        return leftChildIndex < len ? leftChildIndex : null;
    }

    /**
     * 判断节点是否有右孩子
     * @param arr 数组
     * @param i 下标
     * @param len 待排序数组长度
     * @return Integer 右孩子下标，若不存在则返回null
     * @throws Exception
     */
    private static Integer rightChild(int[] arr, int i, int len) throws Exception {
        Integer leftChildIndex = leftChild(arr, i, len);
        return (leftChildIndex != null && leftChildIndex + 1 < len) ? leftChildIndex + 1 : null;

    }

    /**
     * 获取输入下标中对应值最大的下标
     * @param arr 数组
     * @param indexes 下标数组
     * @return 值最大的下标
     * @throws Exception
     */
    private static int getIndexOfMaxValue(int[] arr, @NotNull int... indexes) throws Exception {
        if (arr == null) {
            throw new Exception("数组为null");
        }

        if(indexes == null || indexes.length == 0) {
            throw new Exception("参数错误: indexes: null");
        }

        int max = indexes[0];
        int toCompare = 0;

        for (int i = 1; i <= indexes.length - 1; i ++) {
            toCompare = indexes[i];

            if(arr[max] > arr[toCompare]) {
                continue;
            }

            max = toCompare;
        }

        return max;
    }
}
