package cn.pjs.sort;


public class RadixSort {
    public static void main(String[] args) {
        int[] arr = new int[8000000];
        int[] temp = new int[arr.length];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        radix(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    public static void radix(int[] arr) {
        int[][] barrel = new int[10][arr.length];
        int[] temp = new int[10];
        int max = arr[0];
        //取最大长度
        for (int m = 0; m < arr.length; m++) {
            if (arr[m] > max) {
                max = arr[m];
            }
        }

        int maxLength = (max + "").length();
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            int index = 0;
            //取出了放桶
            for (int j = 0; j < arr.length; j++) {//遍历原数组 放进桶里
                int value = arr[j] / n % 10;//取个/十位数
                barrel[value][temp[value]] = arr[j];//把arr第几个数放进桶【尾数对应桶号】【对应桶里第几个数，从一维数组里取】
                temp[value]++;//一维数组对应第几个桶的有效数字 加一
            }
            //放回去
            for (int k = 0; k < barrel.length; k++) {//遍历10个桶
                if (temp[k] != 0) {//该桶不为空
                    for (int l = 0; l < temp[k]; l++) {//l是小于第k个桶内数字有效个数。也就是遍历每个桶
                        arr[index++] = barrel[k][l];//index是arr数组的下标
                    }
                }
                temp[k] = 0;//取完记得一维数组对应桶归0
            }
        }

    }
}
