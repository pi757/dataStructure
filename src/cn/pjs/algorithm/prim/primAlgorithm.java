package cn.pjs.algorithm.prim;

import java.util.Arrays;

public class primAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int sum = data.length;
        int[][] side = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 8, 10000, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},
        };
        Graph graph = new Graph(sum);
        //以下是直接用一个内存空间
        graph.data = data;
        graph.side = side;
        creatMinTree(graph, 0);
    }

    public static void creatMinTree(Graph graph, int first) {
        int len = graph.data.length;
        int[] temp = new int[len];
        temp[first] = 1;//表明该点访问了
        int h1 = -1;
        int h2 = -1;
        int minSide = 10000;
        for (int i = 0; i < len; i++) {//这里是所有循环
            for (int j = 0; j < len; j++) {//z这是是子图循环
                for (int k = 0; k < len; k++) {//这里是每个节点对应的边循环
                    if (temp[j] == 1 && temp[k] == 0 && graph.side[j][k] < minSide) {
                        minSide = graph.side[j][k];
                        h1 = j;
                        h2 = k;
                    }
                }
            }
            if (minSide != 10000) {
                System.out.printf("边《%c，%c》权：%d\n", graph.data[h1], graph.data[h2], minSide);
                temp[h2] = 1;
                minSide = 10000;
            }
        }
    }

}

class Graph {
    public int sum;
    public char[] data;
    public int[][] side;

    public Graph(int sum) {
        this.sum = sum;
        this.data = new char[sum];
        this.side = new int[sum][sum];
    }

    public void showSide() {
        for (int[] s : side) {
            System.out.println(Arrays.toString(s));
        }
    }
}
