package cn.pjs.recursion;

public class MiGong {
    public static void main(String[] args) {
        int[][] maze = new int[8][7];
        for (int i = 0; i < maze[0].length; i++) {
            maze[0][i] = 1;
            maze[maze.length - 1][i] = 1;
        }
        for (int j = 0; j < maze.length; j++) {
            maze[j][0] = 1;
            maze[j][maze[0].length - 1] = 1;
        }
        maze[3][1] = maze[3][2] = 1;
        System.out.println("原本：");
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.printf(" %d ", maze[i][j]);
            }
            System.out.println("");
        }
        findOut(maze, 1, 1);
        System.out.println("正确路径：");
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.printf(" %d ", maze[i][j]);
            }
            System.out.println("");
        }

    }

    public static boolean findOut(int[][] maze, int x, int y) {
        if (maze[6][5] == 2) {
            return true;
        } else if (maze[x][y] == 0) {
            maze[x][y] = 2;
            if (findOut(maze, x + 1, y)) {//下
                return true;
            } else if (findOut(maze, x, y + 1)) {//右
                return true;
            } else if (findOut(maze, x - 1, y)) {//上
                return true;
            } else if (findOut(maze, x, y - 1)) {//下
                return true;
            } else {
                maze[x][y] = 3;
                return false;
            }
        } else {
            return false;
        }

    }
}
