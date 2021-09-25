package cn.pjs.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        if (arr != null) {
            preOrder(huffman(arr));
        } else {
            System.out.println("空");
        }


    }

    public static Node huffman(int[] arr) {
        Node left;
        Node right;
        Node parent;
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {

            Collections.sort(nodes);
            left = nodes.get(0);
            right = nodes.get(1);
            parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    public static void preOrder(Node node) {
        System.out.println(node);
        if (node.left != null) {
            preOrder(node.left);
        }
        if (node.right != null) {
            preOrder(node.right);
        }
    }
}

class Node implements Comparable<Node> {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;//从小到大
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
