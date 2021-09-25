package cn.pjs.stack;

import java.util.Scanner;

public class LinkedStackTest {
    public static void main(String[] args) {
        LinkedStack linkedStack = new LinkedStack();
        String key = "";
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("push:入栈");
            System.out.println("pop:出栈");
            System.out.println("show:遍历");
            System.out.println("allPop:弹出所有");
            System.out.println("e:退出");
            String s = scanner.next();
            switch (s) {
                case "push":
                    System.out.println("请输入数字");
                    int i = scanner.nextInt();
                    linkedStack.push(i);
                    break;
                case "pop":
                    try {
                        System.out.println(linkedStack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "allPop":
                    try {
                        linkedStack.allPop();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "show":
                    linkedStack.show();
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

class LinkedStack {
    MyStack head = new MyStack();

    public boolean isEmpty() {
        if (head.getNex() == null) {
            return true;
        }
        return false;
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("空");
            return;
        }
        MyStack temp = head;
        while (true) {
            if (temp.getNex() == null) {
                break;
            }
            temp = temp.getNex();
            System.out.println(temp);
        }
    }

    public void push(int n) {
        MyStack temp = head;
        while (true) {
            if (temp.getNex() == null) {
                break;
            }
            temp = temp.getNex();
        }
        MyStack newNote = new MyStack();
        newNote.setData(n);
        newNote.setNex(head.getNex());
        head.setNex(newNote);
    }

    public int pop() {
        if (head.getNex() == null) {
            throw new RuntimeException("为空");
        }
        MyStack temp = head;
        int i = temp.getNex().getData();
        head.setNex(temp.getNex().getNex());
        return i;
    }

    public void allPop() {
        if (isEmpty()) {
            throw new RuntimeException("为空");
        }
        MyStack temp = head;
        while (true) {
            if (temp.getNex() == null) {
                break;
            }
            temp = temp.getNex();
            System.out.println(temp);
        }
        head.setNex(null);
    }


}

class MyStack {
    private int data;
    private MyStack nex;

    @Override
    public String toString() {
        return "MyStack{" +
                ", data=" + data +
//                ", nex=" + nex +
                '}';
    }


    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public MyStack getNex() {
        return nex;
    }

    public void setNex(MyStack nex) {
        this.nex = nex;
    }
}
