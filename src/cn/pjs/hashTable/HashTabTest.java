package cn.pjs.hashTable;

import java.util.Scanner;

public class HashTabTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmpLinkedList empLinkedList = new EmpLinkedList(7);
        String key = "";
        boolean look = true;
        int id;
        String name;
        while (look) {
            System.out.println("a==>添加");
            System.out.println("s==>展示");
            System.out.println("f==>查找");
            System.out.println("d==>删除");
            System.out.println("e==>退出");
            key = scanner.next();
            switch (key) {
                case "a":
                    System.out.println("请输入id");
                    id = scanner.nextInt();
                    System.out.println("请输入姓名");
                    name = scanner.next();
                    Emp emp = new Emp(id, name);
                    empLinkedList.add(emp);
                    break;
                case "s":
                    empLinkedList.show();
                    break;
                case "e":
                    scanner.close();
                    look = false;
                    break;
                case "f":
                    System.out.println("请输入查找的id");
                    id = scanner.nextInt();
                    empLinkedList.find(id);
                    break;
                case "d":
                    System.out.println("请输入删除的id");
                    id = scanner.nextInt();
                    empLinkedList.del(id);
                    break;
                default:
                    break;

            }
        }
    }

}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;

    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class EmpLinked {
    private Emp head;

    //添加
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = emp;
    }

    public void show(int no) {
        if (head == null) {
            System.out.printf("这是第%d链表为空\n", no);
            return;
        } else {
            System.out.printf("这是第%d条链表", no);
            Emp temp = head;
            while (temp != null) {
                System.out.printf("==>id=%d,name=%s", temp.id, temp.name);
                temp = temp.next;
            }
            System.out.println();
        }
    }

    public Emp find(int id) {
        if (head != null) {
            Emp temp = head;
            while (temp != null) {
                if (temp.id == id) {
                    return temp;
                }
                temp = temp.next;

            }
        }
        return null;
    }

    public void del(int id) {
        if (head == null) {
            System.out.println("此人不存在!!!");
            return;
        } else {
            Emp temp = head;
            while (temp != null) {
                if (head.id == id) {
                    head = null;
                } else if (temp.next.id == id) {
                    temp.next = temp.next.next;
                }
                temp = temp.next;
            }
        }
    }
}

class EmpLinkedList {
    private EmpLinked[] empLinked;
    private int size;


    public EmpLinkedList(int size) {
        this.size = size;
        this.empLinked = new EmpLinked[size];
        for (int i = 0; i < size; i++) {
            empLinked[i] = new EmpLinked();
        }
    }

    public int hashNo(int id) {
        return id % size;
    }

    public void add(Emp emp) {
        int no = hashNo(emp.id);
        empLinked[no].add(emp);
    }

    public void show() {
        for (int i = 0; i < size; i++) {
            empLinked[i].show(i + 1);
        }
    }

    public void find(int id) {
        int no = hashNo(id);
        System.out.println(empLinked[no].find(no));

    }

    public void del(int id) {
        int no = hashNo(id);
        empLinked[no].del(id);
    }
}
