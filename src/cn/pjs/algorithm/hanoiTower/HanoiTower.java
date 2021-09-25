package cn.pjs.algorithm.hanoiTower;

public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(64, 'a', 'b', 'c');

    }

    public static int count = 1;

    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.print("第" + count + "步: ");
            System.out.println("第1个盘子从 " + a + "==>" + c);
            count++;
        } else {
            //第一部
            hanoiTower(num - 1, a, c, b);
            System.out.print("第" + count + "步: ");
            System.out.println("第" + num + "个盘子从 " + a + "==>" + c);
            count++;
            //第三步
            hanoiTower(num - 1, b, a, c);
        }
    }
}
