package cn.pjs.search;


import java.util.Arrays;

public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = fibSearch(arr, 190);
        System.out.println(index);

    }

    public static int[] newFib(int max) {
        int[] arr = new int[max];
        arr[0] = 1;
        arr[1] = 1;
        if (max < 2) {
            return arr;
        } else {
            for (int i = 2; i < max; i++) {
                arr[i] = arr[i - 1] + arr[i - 2];
            }
        }
        return arr;
    }

    public static int fibSearch(int[] arr, int value) {
        int[] fib = newFib(20);
        //{1,1,2,3,5,8,13}
        int left = 0;
        int right = arr.length - 1;
        int k = 0;
        int mid = 0;
        //找到斐波那契数组里面适合这个数组的数字；他决定复制过去 新的数组多大；
        while (right > fib[k] - 1) {
            k++;
        }
        //拷贝
        int[] temp = Arrays.copyOf(arr, fib[k]);//拷贝的数组，新长度,不足时补0；
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }
//        System.out.println(Arrays.toString(temp));
        //开始循环找value
        while (left <= right) {
            mid = left + fib[k - 1] - 1;//长度减一等于下标
            if (value > temp[mid]) {
                left = mid + 1;
                k -= 2;
            } else if (value < temp[mid]) {
                right = mid - 1;
                k--;
            } else {
                if (mid < arr.length) {
                    return mid;
                } else {
                    return arr.length - 1;
                }
            }
        }
        return -1;
    }
}
