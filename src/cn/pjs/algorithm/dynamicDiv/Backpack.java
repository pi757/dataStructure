package cn.pjs.algorithm.dynamicDiv;


import java.util.LinkedList;

public class Backpack {

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 1, 7, 8, 3};
        int i = maxTotal(arr);
        System.out.println(i);

    }

    /**
     * 背包规划问题
     */
    public static void backpack() {
        int[] w = {1, 4, 3};
        int[] val = {1500, 3000, 2000};
        int weight = 4;
        int[][] table = new int[w.length + 1][weight + 1];
        LinkedList<Integer> path = new LinkedList<Integer>();
        for (int i = 1; i < table.length; i++) {
            for (int j = 1; j < table[1].length; j++) {
                if (w[i - 1] > j) {
                    table[i][j] = table[i - 1][j];
                } else {
                    int a = table[i - 1][j];
                    int b = val[i - 1] + table[i - 1][j - w[i - 1]];
                    if (a > b) {
                        table[i][j] = a;

                    } else {
                        table[i][j] = b;
                        path.addLast(i);

                    }
                }
            }
        }
        for (int[] row : table) {
            for (int clo : row) {
                if (clo + "".length() < 4) {
                    System.out.print("    " + clo + " ");
                } else {
                    System.out.print(" " + clo + " ");
                }
            }
            System.out.println();
        }
    }

    public static int maxTotal(int[] arr) {
//        int[] arr,int 参数
//            if (i==0){
//                return arr[0];
//            }
//            else if (i==1){
//                return Math.max(arr[0],arr[1]);
//            }
//            else {
//                int a = maxTotal(arr,i-2) +arr[i];//i-2的最尤加上自己
//                int b = maxTotal(arr,i-1);//不选自己则前一个的最优解
//                return Math.max(a,b);
//            }

        //非递归

        int[] temp = new int[arr.length];
        temp[0] = arr[0];
        temp[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < arr.length; i++) {
            int a = arr[i] + temp[i - 2];
            int b = temp[i - 1];
            temp[i] = Math.max(a, b);
        }
        return temp[temp.length - 1];
    }
}
