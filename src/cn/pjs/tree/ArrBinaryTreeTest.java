package cn.pjs.tree;

public class ArrBinaryTreeTest {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        System.out.println("前序");
        arrBinaryTree.preOrder();
        System.out.println("中序");
        arrBinaryTree.infixOrder();
        System.out.println("后续");
        arrBinaryTree.sufOrder();

    }
}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        preOrder(0);
    }

    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("空的");
            return;
        }
        System.out.println(arr[index]);
        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }

    }

    public void infixOrder() {
        infixOrder(0);
    }

    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("空的");
            return;
        }
        if (2 * index + 1 < arr.length) {
            infixOrder(2 * index + 1);
        }
        System.out.println(arr[index]);
        if (2 * index + 2 < arr.length) {
            infixOrder(2 * index + 2);
        }

    }

    public void sufOrder() {
        sufOrder(0);
    }

    public void sufOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("空的");
            return;
        }
        if (2 * index + 1 < arr.length) {
            sufOrder(2 * index + 1);
        }
        if (2 * index + 2 < arr.length) {
            sufOrder(2 * index + 2);
        }
        System.out.println(arr[index]);

    }
}
