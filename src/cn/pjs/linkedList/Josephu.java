package cn.pjs.linkedList;

public class Josephu {
    public static void main(String[] args) {
        RingLinkedList ringLinkedList = new RingLinkedList();
        ringLinkedList.addBoy(25);
//        ringLinkedList.show();
        ringLinkedList.outBoy(2, 3, 25);

    }
}

class RingLinkedList {
    Boy first = null;//相当于head

    /**
     * 添加小朋友
     */
    public void addBoy(int n) {
        if (n < 1) {
            System.out.println("请输入大于0的数");
            return;
        }
        Boy curBoy = null;//辅助节点，first不能动
        for (int i = 1; i <= n; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = boy;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 遍历
     */
    public void show() {
        if (first == null) {
            System.out.println("空");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩编号%d\n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 出圈
     *
     * @param start:从第几个小朋友开始游戏
     * @param count：数字几出圈
     * @param all：一共有几个小朋友
     */
    public void outBoy(int start, int count, int all) {
        if (first == null) {
            System.out.println("空");
            return;
        }
        int n = 1;
        Boy last = first;
        Boy curBoy = first;
        while (true) {
            if (last.getNext() == first) {
                break;
            }
            n++;
            //已经在赋值了
            last = last.getNext();
        }
        if (start < 1 || count < 1 || all != n) {
            System.out.println("参数有误");
            return;
        }
        /**
         循环start-1次到开始的位置
         */
        for (int i = 0; i < start - 1; i++) {
            curBoy = curBoy.getNext();
            last = last.getNext();
        }
        while (true) {
            if (last == curBoy) {
                break;
            }
            for (int j = 0; j < count - 1; j++) {
                curBoy = curBoy.getNext();
                last = last.getNext();
            }
            System.out.printf("%d号小朋友出圈\n", curBoy.getNo());
            curBoy = curBoy.getNext();
            last.setNext(curBoy);
        }
        System.out.printf("这是最有一个小朋友%d\n", last.getNo());

    }
}

class Boy {
    private int no;
    private Boy next;

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
//                ", next=" + next +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public Boy(int no) {
        this.no = no;
    }
}
