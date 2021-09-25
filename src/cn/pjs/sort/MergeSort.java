package cn.pjs.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[8000000];
        int[] temp = new int[arr.length];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        divide(arr, 0, arr.length - 1, temp);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
//        int[] arr ={8,4,5,7,1,3,6,2,254,45,12};
//        int[] temp =new int[arr.length];
//        divide(arr,0,arr.length-1,temp);
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 划分数组
     *
     * @param arr：需要划分的数组
     * @param left：从哪开始
     * @param right：到那结束
     * @param temp：复制过去的数组，为了向和传递参数，分没用到
     */
    public static void divide(int[] arr, int left, int right, int[] temp) {
        if (left < right) {//当分到剩一个元素是会等于就返回递归
            int mid = (left + right) / 2;
            divide(arr, left, mid, temp);
            divide(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 和
     *
     * @param arr：炒作的数组
     * @param left：从哪开始比较
     * @param min；左边比较的终点，也就是中间的数，+1是右边的起点
     * @param right：右边比较的终点
     * @param temp:保存到哪
     */
    public static void merge(int[] arr, int left, int min, int right, int[] temp) {
        int i = left;
        int j = min + 1;//右边冲那开始
        int t = 0;
        //左边i跟右边j轮流比大小 小的就放过去
        while (i <= min && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                i++;
            } else {
                temp[t] = arr[j];
                j++;
            }
            t++;
        }
        //二 左边或右边有剩余的直接全部复制到temp
        while (i <= min) {
            temp[t] = arr[i];
            i++;
            t++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            j++;
            t++;
        }
        //三 将temp复制回来
        t = 0;
        while (left <= right) {
            arr[left] = temp[t];
            left++;
            t++;
        }
    }
}
