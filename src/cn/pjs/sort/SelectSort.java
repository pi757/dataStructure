package cn.pjs.sort;


public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        select(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    public static void select(int[] arr) {
        for (int i = 0; i < arr.length - 2; i++) {
            int minIndex = i;
            int min = arr[i];
            //逐个与第i个元素比较
            for (int j = 1 + i; j < arr.length - 1; j++) {
                //if只是单纯得找位置 不是操作
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            //一下操作到for循环外面 找到最终的在操作
            //小小优化 如果上面循环miIndex没发生改变,就不用交换 省了执行这两行的时间
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

        }


    }
}
