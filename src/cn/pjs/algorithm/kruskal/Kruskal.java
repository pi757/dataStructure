package cn.pjs.algorithm.kruskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Kruskal {

    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int sum = data.length;
        int INF = Integer.MAX_VALUE;
        int[][] side = new int[][]{
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0},
        };
        Graph graph = new Graph(sum, data, side);
        graph.showSide();
        graph.kruskal();
    }
}

class Graph {
    public int sum;
    public char[] data;
    public int[][] side;
    public int sideNum = 0;
    List<Sides> sides;
    boolean[][] isOut;

    //初始化
    public Graph(int sum, char[] data, int[][] side) {
        this.sum = sum;
        this.data = new char[sum];
        this.side = new int[sum][sum];
        sides = new ArrayList<>();
        isOut = new boolean[sum][sum];
        System.arraycopy(data, 0, this.data, 0, data.length);
        for (int i = 0; i < side.length; i++) {
            for (int j = 0; j < side[0].length; j++) {
                this.side[i][j] = side[i][j];
                if (side[i][j] != Integer.MAX_VALUE) {
                    sideNum++;
                }
            }
        }
        for (int i = 0; i < side.length; i++) {
            for (int j = 0; j < side[0].length; j++) {
                if (side[i][j] != Integer.MAX_VALUE && i != j && !isOut[i][j]) {
                    sides.add(new Sides(data[i], data[j], side[i][j]));
                    isOut[i][j] = isOut[j][i] = true;
                }
            }
        }
    }

    //遍历边
    public void showSide() {
        System.out.println("    A   B   C   D   E   F   G");
        for (int i = 0; i < sum; i++) {
            switch (i) {
                case 0:
                    System.out.print("A:");
                    break;
                case 1:
                    System.out.print("B:");
                    break;
                case 2:
                    System.out.print("C:");
                    break;
                case 3:
                    System.out.print("D:");
                    break;
                case 4:
                    System.out.print("E:");
                    break;
                case 5:
                    System.out.print("F:");
                    break;
                case 6:
                    System.out.print("G:");
                    break;
            }
            for (int j = 0; j < sum; j++) {
                if (side[i][j] == Integer.MAX_VALUE) {
                    System.out.printf("%4d", -1);
                } else {
                    System.out.printf("%4d", side[i][j]);
                }
            }
            System.out.println();
        }
    }

    //联通的边排序
    public void sideSort() {
        Collections.sort(sides);
    }

    //获取这个顶点的下标
    public int getIndex(char c) {
        for (int i = 0; i < sum; i++) {
            if (data[i] == c) {
                return i;
            }
        }
        return -1;
    }
    //获取该点的终点


    public int getEnd(int[] ends, int i) {//精华
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    //重点代码
    public void kruskal() {
        int index = 0;
        int[] ends = new int[sides.size()];
        Sides[] result = new Sides[sides.size()];
        sideSort();
        for (int i = 0; i < sides.size(); i++) {
            int p1 = getIndex(sides.get(i).vertex1);
            int p2 = getIndex(sides.get(i).vertex2);
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            if (m != n) {
                ends[m] = n;
                result[index++] = sides.get(i);
            }

        }
        for (Sides s : result) {
            if (s != null) {
                System.out.println(s);
            }
        }

    }

}

class Sides implements Comparable<Sides> {
    public char vertex1;
    public char vertex2;
    public int distance;

    public Sides(char vertex1, char vertex2, int distance) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "AllSide{" +
                "start:" + vertex1 +
                ", end:" + vertex2 +
                ", distance:" + distance +
                '}';
    }

    @Override
    public int compareTo(Sides o) {
        return this.distance - o.distance;
    }
}