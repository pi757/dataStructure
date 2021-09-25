package cn.pjs.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        Shell2(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    public static void Shell(int[] arr) {
        int temp = 0;
        for (int gra = arr.length / 2; gra > 0; gra /= 2) {//gra相当于步数 也就是跟他同一组的相隔多少
            for (int i = gra; i < arr.length; i++) {//组外循环 单位是组
                for (int j = i - gra; j >= 0; j -= gra) {//组内循环 单位是相隔gra
                    if (arr[j] > arr[j + gra]) {
                        temp = arr[j];
                        arr[j] = arr[j + gra];
                        arr[j + gra] = temp;
                    }
                }

            }
        }
    }

    public static void Shell2(int[] arr) {
        int temp;
        for (int gra = arr.length / 2; gra > 0; gra /= 2) {//gra相当于步数 也就是跟他同一组的相隔多少
            for (int i = gra; i < arr.length; i++) {
                int j = i;
                temp = arr[j];
                while (j - gra >= 0 && temp < arr[j - gra]) {
                    arr[j] = arr[j - gra];
                    j -= gra;
                }
                arr[j] = temp;
            }
        }

    }
}
