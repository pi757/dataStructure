package cn.pjs.tree;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
//        String str="i like like like java do you like a java";
////        String str="my is pjs hello i love you";
//        byte[] strByte=str.getBytes();//字符串转byte数组
//        byte[] result = huffmanZip(strByte);
//        byte[] deCodeResult= deCode(map, result);
//        String s= new String(deCodeResult);
//        System.out.println(s);
        String srcFile = "E://zzf.jpg";
        String srcFile2 = "E://zzf.zip";
//        zipFile(srcFile);
//        System.out.println("压缩成功");
        unZipFile(srcFile2);
        System.out.println("解压成功");

    }

    //封装
    public static byte[] huffmanZip(byte[] strByte) {

        List<Node2> nodes = toList(strByte);
        Node2 result = huffman(nodes);
        Map<Byte, String> allCode = code(result);
        byte[] zip = zip(strByte, allCode);
        return zip;
    }

    //传递编码 字符串拼接
    static StringBuilder stringBuilder = new StringBuilder();
    //存储每个叶子结点的霍夫曼编码
    static Map<Byte, String> map = new HashMap<>();
    static int last = 0;

    //方法重载 调用方便，可不返回
    public static Map<Byte, String> code(Node2 node) {
        if (node == null) {
            System.out.println("空");
            return null;
        } else {
            toCode(node, "", stringBuilder);

        }
        return map;
    }

    //递归处理编码 不需要返回
    public static void toCode(Node2 node, String path, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(path);
        if (node != null) {

            if (node.value == null) {
                //向左
                toCode(node.left, "0", stringBuilder2);
                //向右
                toCode(node.right, "1", stringBuilder2);

            } else {
                map.put(node.value, stringBuilder2.toString());
            }
        }
    }

    /**
     * 转换成list数组，计算每个字母出现的次数，并将字母当作value key当作weight放入类型为node2的集合
     */
    public static List<Node2> toList(byte[] strByte) {
        Map<Byte, Integer> map = new HashMap();
        List<Node2> nodes = new ArrayList<Node2>();
        //迭代遍历传进来的byte数组，并计算出现次数
        for (Byte b : strByte) {
            if (map.get(b) == null) {
                map.put(b, 1);
            } else {
                map.put(b, map.get(b) + 1);
            }
        }
        //遍历map数组，并把key当作value 值当作weight传入list集合；
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node2(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //弄出树
    public static Node2 huffman(List<Node2> nodes) {
        Node2 left;
        Node2 right;
        Node2 parent;
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            left = nodes.get(0);
            right = nodes.get(1);
            parent = new Node2(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    //前序遍历
    public static void preOrder(Node2 node) {
        System.out.println(node);
        if (node.left != null) {
            preOrder(node.left);
        }
        if (node.right != null) {
            preOrder(node.right);
        }
    }

    /**
     * 压缩
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> codes) {
        StringBuilder stringBuilder = new StringBuilder();
        //从string转换的byte数组中遍历当作k 然后按照code取出对应的编码并追加到stringBuilder；
        for (Byte b : bytes) {
            stringBuilder.append(codes.get(b));
        }
//        System.out.println(stringBuilder);
        //获取压缩后存放数组的大小
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        byte[] result = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String str;
            if (i + 8 > stringBuilder.length()) {
                str = stringBuilder.substring(i);
                last = str.length();
            } else {
                last = 8;
                str = stringBuilder.substring(i, i + 8);
            }
            result[index] = (byte) Integer.parseInt(str, 2);//后面的2 是二进制
            index++;
        }
        return result;
    }

    /**
     * 将传入的byte转换成一个0101路径的字符串
     *
     * @param flag:如果不是最后一个就为真
     * @param b：传入需要转换的byte
     * @return 转换完成的字符串
     */
    public static String byteToStr(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);//这里返回二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            StringBuilder pre = new StringBuilder(str);
            if (pre.length() < last) {
                for (int i = 0; i < last - str.length(); i++) {
                    pre.insert(0, "0");
                }
                str = pre.toString();
            } else {
                str = str.substring(str.length() - last);
            }
            return str;
        }
    }

    /**
     * 解码
     *
     * @param huffmanCode：对应的密码表
     * @param huffmanByte：转换的密码
     * @return 返回破解的结果
     */
    public static byte[] deCode(Map<Byte, String> huffmanCode, byte[] huffmanByte) {
        Map<String, Byte> newHuffmanCode = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCode.entrySet()) {
            newHuffmanCode.put(entry.getValue(), entry.getKey());
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanByte.length; i++) {
            String str;
            if (i != huffmanByte.length - 1) {
                str = byteToStr(true, huffmanByte[i]);
            } else {
                str = byteToStr(false, huffmanByte[i]);
            }
            stringBuilder.append(str);
        }
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String s = stringBuilder.substring(i, i + count);
                b = newHuffmanCode.get(s);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        //注意这里list是ascall码 还不是字母；需要转换成byte模式才是字母
        byte[] b = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            b[i] = list.get(i);
        }
//        String s = new String(b);
        return b;
    }

    /**
     * 文件压缩
     */
    public static void zipFile(String srcFile) {
        String[] split = srcFile.split("\\.");
        String detFile = split[0] + ".zip";
        //输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //输入流
        InputStream is = null;

        try {
            //打开该文件
            is = new FileInputStream(srcFile);
            //创建跟原内容一样大的字节数组
            byte[] b = new byte[is.available()];
            //读取文件存入b
            is.read(b);
            //进行压缩
            byte[] huffmanBytes = huffmanZip(b);
            //已完成压缩 以下是把压缩放入文件中
            //创建一个压缩得文件
            os = new FileOutputStream(detFile);
            //这个是对象输出流
            oos = new ObjectOutputStream(os);
            //写入密码表
            oos.writeObject(map);
            //写入压缩后的数据
            oos.writeObject(huffmanBytes);
            oos.writeObject(split[1]);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                os.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 文件解压
     */
    public static void unZipFile(String zipSrc) {
        String[] split = zipSrc.split("\\.");

        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            //打开该文件
            is = new FileInputStream(zipSrc);
            //以对象的方式打开该文件
            ois = new ObjectInputStream(is);
            //以对象的方式读取内容，注意顺序
            Map<Byte, String> pwdTable = (Map<Byte, String>) ois.readObject();
            byte[] huffmanByte = (byte[]) ois.readObject();
            //从编码表中获取后缀
            String str = (String) ois.readObject();
            String detSrc = split[0] + "2." + str;
            //调用解压方法
            byte[] b = deCode(pwdTable, huffmanByte);
            //new一个新文件
            os = new FileOutputStream(detSrc);
            //将解压的放入新文件中
            os.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Node2 implements Comparable<Node2> {
    public Byte value;
    public int weight;
    public Node2 left;
    public Node2 right;

    public Node2(Byte value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node2{" +
                "value=" + value +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node2 o) {
        return this.weight - o.weight;
    }
}
