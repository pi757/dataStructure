package cn.pjs.sparseArray;

import java.io.*;

/*
稀疏数组与普通二维数组相互转换 并写入磁盘
 */
public class ArrayToSparse {
    public static void main(String[] args) {
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[3][4] = 2;
        System.out.println("这里是原始数组");
        int count = 0;//计数有几个有效值
        for (int[] rows : chessArray) {
            System.out.println("");
            for (int data : rows) {
                System.out.printf("%d\t", data);
                if (data != 0) {
                    count++;
                }
            }
        }
        int row = chessArray.length;//棋盘行数
        int col = chessArray[0].length;//棋盘列数数
        int not0 = 0;//第一个有效数值
        int[][] sparseArray = new int[count + 1][3];
        sparseArray[0][0] = row;
        sparseArray[0][1] = col;
        sparseArray[0][2] = count;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (chessArray[i][j] != 0) {
                    not0++;
                    sparseArray[not0][0] = i;
                    sparseArray[not0][1] = j;
                    sparseArray[not0][2] = chessArray[i][j];
                }

            }
        }
        //遍历输出稀疏数组
        System.out.println("\n这里是稀疏数组");
        for (int[] rows : sparseArray) {
            System.out.println("");
            for (int data : rows) {
                System.out.printf("%d\t", data);
            }
        }
        boolean b = saveSparse(sparseArray);
        if (b) {
            System.out.println("\n保存成功");
        }
        //将稀疏数组恢复成二维数组
        int sRow = sparseArray[0][0];
        int sCol = sparseArray[0][1];
        int[][] sparseToArray = new int[sRow][sCol];
        for (int i = 1; i < sparseArray.length; i++) {
            sparseToArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        System.out.println("\n输出转换后的二维数组");
        for (int[] rows : sparseToArray) {
            System.out.println("");
            for (int data : rows) {
                System.out.printf("%d\t", data);

            }

        }
        System.out.println("\n输出冲磁盘中取出的稀疏数组");
        int[][] querySparse = querySparse(not0);//从磁盘中取
        for (int[] rows : querySparse) {
            System.out.println("");
            for (int data : rows) {
                System.out.printf("%d\t", data);
            }
        }
    }

    public static boolean saveSparse(int[][] sparse) {
        boolean isSuccess = false;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File("D:\\JAVA\\Idea 2019\\ProgramFiles\\IdeaProjects\\dataStructure\\sparseArray.data"));
            for (int[] array : sparse) {
                fileWriter.write(array[0] + "\t" + array[1] + "\t" + array[2] + "\t\n");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return isSuccess;
    }

    public static int[][] querySparse(int isRow) {
        int[][] QSparse = null;
        boolean oneRead = false;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File("D:\\JAVA\\Idea 2019\\ProgramFiles\\IdeaProjects\\dataStructure\\sparseArray.data")));
            String linStr = null;
            int count = 0;
            while ((linStr = bufferedReader.readLine()) != null) {
                String[] tempStr = linStr.split("\t");
                if (!oneRead) {
                    QSparse = new int[isRow + 1][3];
                    QSparse[count][0] = Integer.parseInt(tempStr[0]);
                    QSparse[count][1] = Integer.parseInt(tempStr[1]);
                    QSparse[count][2] = Integer.parseInt(tempStr[2]);
                    count++;
                    oneRead = true;
                } else {
                    QSparse[count][0] = Integer.parseInt(tempStr[0]);
                    QSparse[count][1] = Integer.parseInt(tempStr[1]);
                    QSparse[count][2] = Integer.parseInt(tempStr[2]);
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return QSparse;
    }
}
