package cn.pjs.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private ArrayList<String> vertexList;
    private int[][] edges;
    private int numOfEdges;
    private boolean[] isSee;

    public static void main(String[] args) {
        int n = 5;
        String[] vertexes = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        for (String value : vertexes) {
            graph.insertVertex(value);
        }
        //边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.showEdge();
        graph.dfs();
        System.out.println();
        graph.bfs();
    }

    //初始化
    public Graph(int n) {
        this.edges = new int[n][n];
        this.numOfEdges = 0;
        this.vertexList = new ArrayList<>(n);
        this.isSee = new boolean[n];
    }

    //插入顶点
    public void insertVertex(String value) {
        vertexList.add(value);
    }

    //插入边的状态
    public void insertEdge(int index1, int index2, int weight) {
        edges[index1][index2] = weight;
        edges[index2][index1] = weight;
        numOfEdges++;
    }

    //遍历
    public void showEdge() {
        for (int[] i : edges) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    //深度优先 对一个节点
    public void dfs(int index) {
        if (!isSee[index]) {
            System.out.print(vertexList.get(index) + "-->");
            isSee[index] = true;

        }
        int w = getNextIndex(index);
        while (w != -1) {
            if (!isSee[w]) {
                dfs(w);
            }
            w = getNextNeighbor(index, w);
        }
    }

    public void dfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isSee[i]) {
                dfs(i);
            }
        }
    }

    //广度优先 对一个节点
    public void bfs(int index) {

        if (!isSee[index]) {
            System.out.print(vertexList.get(index) + "-->");
            isSee[index] = true;
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.addLast(index);
        while (!queue.isEmpty()) {
            Integer u = queue.removeFirst();
            int w = getNextIndex(u);
            if (w != -1) {
                if (!isSee[w]) {
                    System.out.print(vertexList.get(w) + "-->");
                    isSee[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }

    }

    public void bfs() {
        Arrays.fill(isSee, false);
        for (int i = 0; i < vertexList.size(); i++) {
            bfs(i);
        }
    }

    public int getNextIndex(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public int getNextNeighbor(int index, int next) {
        for (int i = next + 1; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }
}
