package cn.pjs.recursion;

public class Queen8 {
    static int MAX = 8;
    static int[] arr = new int[MAX];
    static int count;

    public static void main(String[] args) {
        check(0);
        System.out.printf("共%d种摆法\n", count);
    }

    /**
     * 递归判断方案
     *
     * @param n：0
     */
    public static void check(int n) {
        if (n == MAX) {
            pri();
            count++;
            return;
        }
        for (int i = 0; i < MAX; i++) {//每一行的列循环 看看哪个列合适
            arr[n] = i;//把第n个皇后放入第几列;
            if (judge(n)) {
                check(n + 1);
            }
            //如果上面judge是假 那就i++ 放入下一列试试
        }
    }

    /**
     * 与之前比较是否同一斜线或者行
     *
     * @param n：第几个皇后
     * @return 是否符合
     */
    public static boolean judge(int n) {
        for (int i = 0; i < n; i++) {//传入的第几个皇后逐个与之前的比较
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {//行不用判断先判断列 后面是斜线（等腰直角三角形）
                return false;
            }
        }
        return true;
    }

    /**
     * 输出遍历
     */
    public static void pri() {
        for (int i = 0; i < MAX; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }
}
