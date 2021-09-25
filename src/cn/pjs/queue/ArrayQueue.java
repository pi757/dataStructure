package cn.pjs.queue;

import java.util.Scanner;

public class ArrayQueue {
    public static void main(String[] args) {
        Queue queue = new Queue(3);
        Scanner scanner = new Scanner(System.in);
        char key;
        boolean loop = true;
        while (loop) {
            System.out.println("s:显示队列");
            System.out.println("a:添加");
            System.out.println("g:获取");
            System.out.println("h:头");
            System.out.println("e:退出");
            key = scanner.next().charAt(0);//接受一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int i = scanner.nextInt();
                    try {
                        queue.addQueue(i);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int n = queue.getQueue();
                        System.out.println(n);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    int headQueue = queue.headQueue();
                    System.out.println(headQueue);
                    break;
                case 'e':
                    loop = false;
                    break;
                default:
                    break;
            }

        }
        System.out.println("\n程序退出");
    }

}

class Queue {
    private final int maxSize;
    private int front;
    private int rear;
    private final Integer[] arr;

    //无参构造
    public Queue(int maxSize) {
        this.maxSize = maxSize;
        front = -1;
        rear = -1;
        arr = new Integer[maxSize];
    }

    //判断该队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //判断该队列是否为满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //往队列加元素
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满了");
            return;

        }
        rear++;
        arr[rear] = n;
    }

    //往队列取元素
    public Integer getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        front++;
        int value = arr[front];
        arr[front] = null;
        return value;
    }

    //输出队列
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列没有数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                System.out.printf("arr[%d]=%d\n", i, arr[i]);
            }

        }
    }

    public Integer headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("没有数据");

        }
        return arr[front];
    }
}

