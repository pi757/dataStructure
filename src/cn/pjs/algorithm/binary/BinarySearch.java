package cn.pjs.algorithm.binary;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 13, 15, 78, 99};
        System.out.println(binarySearch(arr, 7));
    }

    public static int binarySearch(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
