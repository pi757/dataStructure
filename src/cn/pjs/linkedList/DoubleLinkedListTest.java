package cn.pjs.linkedList;

import java.util.Scanner;

public class DoubleLinkedListTest {
    public static void main(String[] args) {
        /*
        双链表测试
         */
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "呼保义");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "公孙胜", "入云龙");
        HeroNode2 hero5 = new HeroNode2(5, "大刀关胜", "天勇星");
        /*
        添加英雄
         */
//        doubleLinkedList.addHero(hero1);
//        doubleLinkedList.addHero(hero2);
//        doubleLinkedList.addHero(hero3);
//        doubleLinkedList.addHero(hero4);
        /*
        顺序添加英雄
         */
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero5);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero1);
        /*
        /*
        删除
         */
//        doubleLinkedList.del(1);
        /*
        修改
         */
        HeroNode2 hero = new HeroNode2(2, "卢俊义", "玉麒麟");
//        HeroNode newHero=new HeroNode(6,"林冲","豹子头");
        doubleLinkedList.updateHero(hero2);
        /*
        输出双链表
         */
        System.out.println("遍历双链表");
        doubleLinkedList.show();

    }
}

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    /*
    获取头
     */
    public HeroNode2 getHead() {
        return head;
    }

    /*
    添加节点
     */
    public void addHero(HeroNode2 heroNode) {
        HeroNode2 temp = head;//head不能直接炒作 所以搞一个临时变量
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;//如果next不为空就继续指向下一个，有点类似递归；
        }
        temp.next = heroNode;//最后一个为空时指向我们加进来的;
        heroNode.pre = temp;
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
        HeroNode2 temp = head.next;//head不能直接炒作 所以搞一个临时变量
        while (temp != null) {
            System.out.println(temp);
            count++;
            temp = temp.next;
        }
        System.out.println("有" + count + "个有效数据");
        return count;
    }

    /*
   根据no修改信息
    */
    public void updateHero(HeroNode2 newHero) {
        HeroNode2 temp = head;
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
    双向链表可以直接找到本身进行删除 不需要借助前一个节点
     */
    public void del(int no) {
        HeroNode2 temp = head.next;
        boolean flag = false;
        if (temp == null) {
            System.out.println("空链表");
            return;
        }
        /*
        找到位置
         */
        while (true) {
            if (temp == null) {
                break;//遍历结束
            } else if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;

        }
        if (flag) {
            temp.pre.next = temp.next;
            temp.next.pre = temp.pre;
        } else {
            System.out.printf("删除%d节点不存在", no);
        }
    }

    /*
    根据no自动排序链表 重复不加
     */
    public void addByOrder(HeroNode2 hero) {
        boolean flag = false;
        HeroNode2 temp = head;
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
            hero.pre = temp;
            if (hero.next != null) {
                hero.next.pre = hero;
            }
        }


    }

}

class HeroNode2 {
    public int no;
    public String name;
    public String smallName;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String smallName) {
        this.no = no;
        this.name = name;
        this.smallName = smallName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", smallName='" + smallName + '\'' +
                /*
                以下两个方法不能同时toString
                因为他们互相调用 会循环 然后栈溢出
                 */
//                ", next=" + next +
//                ", pre=" + pre +
                '}';
    }
}