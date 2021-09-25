package cn.pjs.sort;

import java.time.Instant;

public class BubbleSort {
    public static void main(String[] args) {
        int[] bubble = new int[80000];
        for (int i = 0; i < 80000; i++) {
            bubble[i] = (int) (Math.random() * 80000);
        }
        Instant instant = Instant.now();
        long l1 = instant.toEpochMilli();
        System.out.println("开始");

        Bubble(bubble);
        Instant n2 = Instant.now();
        long l2 = n2.toEpochMilli();
        System.out.println(l2 - l1);

    }

    public static void Bubble(int[] bubble) {
        boolean flag = false;
        int temp;
        for (int i = 0; i < bubble.length - 1; i++) {//循环了大小减一次
            //依次找最大的泡
            for (int j = 0; j < bubble.length - 1; j++) {//在每次循环比较了i-1次
                if (bubble[j] > bubble[j + 1]) {
                    flag = true;
                    temp = bubble[j];
                    bubble[j] = bubble[j + 1];
                    bubble[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            }
        }
    }
}
