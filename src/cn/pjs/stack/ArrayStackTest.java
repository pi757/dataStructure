package cn.pjs.stack;

import java.util.Scanner;

public class ArrayStackTest {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("push:入栈");
            System.out.println("pop:出栈");
            System.out.println("show:遍历");
            System.out.println("e:退出");
            String s = scanner.next();
            switch (s) {
                case "push":
                    System.out.println("请输入数字");
                    int i = scanner.nextInt();
                    stack.push(i);
                    break;
                case "pop":
                    try {
                        System.out.println(stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "show":
                    stack.show();
                    break;
                case "e":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}

class ArrayStack {
    public int maxSize;
    public int[] stack;
    public int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
        return;
    }

    /**
     * 判断是否为空
     *
     * @return ture为空
     */
    public boolean isEmpty() {
        if (top == -1) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否为满
     *
     * @return ture为满
     */
    public boolean isFull() {
        if (top == maxSize - 1) {
            return true;
        }
        return false;
    }

    /**
     * 入栈
     *
     * @param i；放入的数据
     */
    public void push(int i) {
        if (isFull()) {
            System.out.println("满了");
            return;
        }
        top++;
        stack[top] = i;
    }

    /**
     * 出栈
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("空");
        }
        int i = stack[top];
        top--;
        return i;
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d", i, stack[i]);
        }
    }
}
