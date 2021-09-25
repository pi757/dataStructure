package cn.pjs.search;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int index = insertValue(arr, 0, arr.length - 1, 99);
        System.out.println(index);
    }

    public static int insertValue(int[] arr, int left, int right, int value) {
        if (value < arr[left] || value > arr[right]) {
            return -1;
        }
        if (left > right) {
            return -1;
        }
        //这里注意 按照正常写法是后面/加（）但是等于小数会等于0 所以就先× 反正不影响结果；
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        if (arr[mid] == value) {
            return mid;
        } else if (arr[mid] > value) {
            return insertValue(arr, left, mid - 1, value);
        } else if (arr[mid] < value) {
            return insertValue(arr, mid + 1, right, value);
        } else {
            return -1;
        }
    }
}
