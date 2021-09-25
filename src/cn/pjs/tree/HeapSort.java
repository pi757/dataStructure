package cn.pjs.tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
//        int[] arr =new int[8000000];
//        for (int i =0;i<8000000;i++){
//            arr[i]= (int)(Math.random() * 80000);
//        }
//        long start = System.currentTimeMillis();
//
//       heap(arr);
//
//        long end = System.currentTimeMillis();
//        System.out.println(end-start);
        int[] arr = {4, 6, 8, 5, 9};
        heap(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void heap(int[] arr) {

        int temp = 0;
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            toHeapSort(arr, i, arr.length);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            toHeapSort(arr, 0, j);
        }
    }

    public static void toHeapSort(int[] arr, int i, int length) {
        int temp = arr[i];
        //这个for循环有回溯 当你换完了就要跟换了的节点当作根节点 重新比较一下他的左右孩子
        //2*i+1是左孩子 +2是右孩子 这里只是找到k=下标
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }

    //以下不行，因为他每次都是冲最后一个非叶子结点开始,而真正的是第一次从非叶子开始 以后每次传入0
    public static void heap2(int[] arr) {
        toHeapSort2(arr, arr.length / 2 - 1, arr.length - 1);
//        for(int i = arr.length;i>0;i--){
//            toHeapSort2(arr,0);
//        swap(arr,0,i-1);
//        }
    }

    //只比较了一次 换完之后没有在比较
    public static void toHeapSort2(int[] arr, int n, int length) {
        int max;
        for (int i = n; i >= 0; i--) {
            max = arr[i];
            if (2 * i + 1 <= length && arr[i] < arr[2 * i + 1]) {
                swap(arr, i, 2 * i + 1);
            }
            if (2 * i + 2 <= length && arr[i] < arr[2 * i + 2]) {
                swap(arr, i, 2 * i + 2);
            }

        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
