package cn.pjs.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
//        int[] arr ={1,1,3,6,9,12,46,99};
        Scanner scanner = new Scanner(System.in);
        Double[] arr = new Double[12];
        for (int i = 0; i < 12; i++) {
            System.out.println("请输入有序数组第" + (i + 1) + "个数值");
            arr[i] = scanner.nextDouble();
        }
        boolean flag = true;
        while (flag) {
            System.out.println("输入1===========> 查找数值下标");
            System.out.println("输入2===========> 退出查找");
            String in = scanner.next();
            switch (in) {
                case "1":
                    System.out.println("请输入要查找的数值:");
                    double v = scanner.nextDouble();
                    List<Integer> result = binary(arr, v);
                    if (result.isEmpty()) {
                        System.out.println("没有这个数");
                    } else {
                        System.out.print("在哪几个下标:" + result);
                        System.out.println();
                    }
                    break;
                case "2":
                    flag = false;
                    break;
                default:
                    System.out.println("输入有误");
                    break;
            }

        }

    }

    public static List<Integer> binary(Double[] arr, Double value) {
        return binary(arr, 0, arr.length - 1, value);
    }

    public static List<Integer> binary(Double[] arr, int left, int right, Double value) {
        int mid = (left + right) / 2;
        List<Integer> list = new ArrayList<>();
        if (left > right) {
            return list;
        }
        if (value.equals(arr[mid])) {//找多个等于的
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || !arr[temp].equals(value)) {
                    break;
                }
                list.add(temp);
                temp--;
            }
            list.add(mid);
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || !arr[temp].equals(value)) {
                    break;
                }
                list.add(temp);
                temp++;
            }
            return list;
        } else if (value < arr[mid]) {
            return binary(arr, left, mid - 1, value);
        } else {
            return binary(arr, mid + 1, right, value);
        }
    }
}
