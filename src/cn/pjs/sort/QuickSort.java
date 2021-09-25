package cn.pjs.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = new int[8000000];
//        for (int i = 0; i < 8000000; i++) {
//            arr[i] = (int) (Math.random() * 80000);
//        }
//        long start = System.currentTimeMillis();
//        quick(arr, 0, arr.length - 1);
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        int[] arr ={2,6,1,9,5,3,8,7,10};
        quick(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quick(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int base = arr[left];
        int i = left;
        int j = right;
        while (i != j) {
            //找比base小的下标
            while (arr[j] >= base && i < j) {//这里不写等于然后i也不写就会死循环 因为有两个一样的数就会无限交换
                j--;
            }
            //找比base大的下标
            while (arr[i] <= base && i < j) {//这里不写= 就会直接返回最左边的数
                i++;
            }
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        arr[left] = arr[i];
        arr[i] = base;
        quick(arr, left, i - 1);
        quick(arr, j + 1, right);

    }
}
