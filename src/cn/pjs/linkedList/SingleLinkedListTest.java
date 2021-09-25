package cn.pjs.linkedList;

import java.util.Scanner;
import java.util.Stack;

public class SingleLinkedListTest {
    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1, "宋江", "呼保义");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "公孙胜", "入云龙");
        HeroNode hero5 = new HeroNode(5, "大刀关胜", "天勇星");
        /*
        合并链表
         */
        System.out.println("合并链表");
        SingleLinkedList s1 = new SingleLinkedList();
        s1.addByOrder(hero2);
        s1.addByOrder(hero4);
        s1.show();
        SingleLinkedList s2 = new SingleLinkedList();
        s2.addByOrder(hero3);
        s2.addByOrder(hero1);
        s2.addByOrder(hero5);
        s2.show();
        SingleLinkedList tow = tow(s1, s2);
        System.out.println("=================");
        assert tow != null;
        tow.show();


        /*
        新建链表并加入
         */
//        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.addHero(hero3);
//        singleLinkedList.addHero(hero2);
//        singleLinkedList.addHero(hero4);
//        singleLinkedList.addHero(hero1);
        /*
        排序插入
         */
//        singleLinkedList.addByOrder(hero3);
//        singleLinkedList.addByOrder(hero2);
//        singleLinkedList.addByOrder(hero4);
//        singleLinkedList.addByOrder(hero1);
        /*
        遍历链表
         */
//        singleLinkedList.show();
//        /*
//        修改2号
//         */
//        HeroNode newHero=new HeroNode(2,"卢俊义~~","玉麒麟~~");
//        HeroNode newHero=new HeroNode(6,"林冲","豹子头");
//        singleLinkedList.updateHero(newHero);
//        /*
//        遍历链表
//         */
//        System.out.println("\n更新后遍历");
//        singleLinkedList.show();
//        /*
//        删除一个节点
//         */
//        singleLinkedList.del(1);
//        /*
//        遍历链表
//         */
//        System.out.println("\n删除后遍历");
//        singleLinkedList.show();
//        /*
//        找倒数第几个
//         */
//        HeroNode last = singleLinkedList.findLast(1);
//        if(last!=null){
//            System.out.println(last);
//        }
//        /*
//        反转
//         */
//        singleLinkedList.reverse();
//        /*
//        遍历链表
//         */
//        System.out.println("\n反转后遍历");
//        singleLinkedList.show();
//        /*
//        反向输出
//         */
//        System.out.println("反向输出");
//        singleLinkedList.reverseOut();

    }

    public static SingleLinkedList tow(SingleLinkedList s1, SingleLinkedList s2) {
        HeroNode head1 = s1.getHead();
        HeroNode temp1 = head1.next;

        if (temp1 == null) {
            return null;
        }
        while (temp1 != null) {
            HeroNode temp2 = new HeroNode(0, "", "");
            temp2.no = temp1.no;
            temp2.name = temp1.name;
            temp2.smallName = temp1.smallName;
            temp2.next = null;
            System.out.println(temp2 + "------------------");
            s2.addByOrder(temp2);
            temp1 = temp1.next;


        }

        return s2;
    }
}

class SingleLinkedList {
    private final HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /*
        添加节点
         */
    public void addHero(HeroNode heroNode) {
        HeroNode temp = head;//head不能直接炒作 所以搞一个临时变量
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;//如果next不为空就继续指向下一个，有点类似递归；
        }
        temp.next = heroNode;//最后一个为空时指向我们加进来的;
    }

    /*
    遍历链表并算出有效数据个数
     */
    public int show() {
        int count = 0;
        if (head.next == null) {
            System.out.println("\n该链表为空");
            return count;
        }
        HeroNode temp = head.next;//head不能直接炒作 所以搞一个临时变量
        while (temp != null) {
            System.out.println(temp);
            count++;
            temp = temp.next;
        }
        System.out.println("有" + count + "个有效数据");
        return count;
    }

    /*
    根据no自动排序链表 重复不加
     */
    public void addByOrder(HeroNode hero) {
        boolean flag = false;
        HeroNode temp = head;
        /*
        找插入的位置
         */
        while (true) {
            if (temp.next == null) {
                break;
            } else if (temp.next.no > hero.no) {
                break;
            } else if (temp.next == hero) {
                flag = true;
            }
            temp = temp.next;
        }
        /*
        执行插入操作
         */
        if (flag) {
            System.out.printf("\n%d变量已存在", hero.no);
        } else {
            hero.next = temp.next;
            temp.next = hero;
        }


    }

    /*
    根据no修改信息
     */
    public void updateHero(HeroNode newHero) {
        HeroNode temp = head;
        boolean flag = false;
        if (head.next == null) {
            System.out.println("该链表空");
            return;
        }
        while (true) {
            if (temp.next.next == null) {
                break;//遍历完没找到
            }
            if (temp.next.no == newHero.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next.name = newHero.name;
            temp.next.smallName = newHero.smallName;
        } else {
            System.out.printf("没有%d此人,是否添加此人?\ny:是\nn:否\n", newHero.no);
            Scanner scanner = new Scanner(System.in);
            char isAdd = scanner.next().charAt(0);
            switch (isAdd) {
                case 'y':
                    addByOrder(newHero);
                    break;
                default:
                    break;
            }
        }

    }

    /*
    根据no删除节点
     */
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;
        if (temp.next == null) {
            System.out.println("空链表");
            return;
        }
        /*
        找到位置
         */
        while (true) {
            if (temp.next == null) {
                break;//遍历结束
            } else if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;

        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("删除%d节点不存在", no);
        }
    }

    /*
    查找第倒数第几个数据
     */
    public HeroNode findLast(int n) {
        HeroNode temp = head;
        int all = show();
        if (temp.next == null) {
            System.out.println("为空");
            return null;
        }
        if (all - n < 0) {
            System.out.printf("本链表只有%d个有效数据\n", all);
            return null;
        }
        for (int i = 0; i <= all - n; i++) {
            temp = temp.next;
        }
        return temp;

    }

    /*
    反转节点
     */
    public void reverse() {
        HeroNode temp = head.next;
        HeroNode nex = null;
        HeroNode reverseHead = new HeroNode(0, "", "");
        if (head.next == null || temp.next == null) {
            return;
        }
        while (temp != null) {
            nex = temp.next;
            temp.next = reverseHead.next;
            reverseHead.next = temp;
            temp = nex;
        }
        head.next = reverseHead.next;
    }

    /*
    反向输出链表 单不破坏结构
     */
    public void reverseOut() {
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode temp = head;
        if (temp.next == null) {
            System.out.println("空");
            return;
        }
        while (temp.next != null) {

            stack.push(temp.next);
            temp = temp.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}


class HeroNode {
    public int no;
    public String name;
    public String smallName;
    public HeroNode next;

    public HeroNode(int no, String name, String smallName) {
        this.no = no;
        this.name = name;
        this.smallName = smallName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", smallName='" + smallName + '\'' +
//                ", next=" + next +
                '}';
    }
}
