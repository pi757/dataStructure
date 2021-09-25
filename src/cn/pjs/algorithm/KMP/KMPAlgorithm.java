package cn.pjs.algorithm.KMP;


import java.util.Arrays;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String s1 = "BBC ABCDAB ABCDABCDABDE";
        String s2 = "ABCDABD";
//        System.out.println(kmp(s1,s2));
        s2 = "abababacb";
        System.out.println(Arrays.toString(toKey(s2)));
        System.out.println(Arrays.toString(toKey2(s2)));
    }

    public static int[] toKey(String s) {
        if (s.length() == 0) {
            return null;
        }
        int[] key = new int[s.length()];
        char[] chars = s.toCharArray();

        key[0] = 0;
        //i是key的指针 也是字符串的指针，j是字符串头匹配的指针
        for (int i = 1, j = 0; i < s.length(); i++) {
            //精华
            while (j > 0 && chars[i] != chars[j]) {
                j = key[j - 1];
            }
            //逐个匹配
            if (chars[i] == chars[j]) {
                j++;
            }
            key[i] = j;
        }
        return key;
    }

    public static int kmp(String s1, String s2) {
        if (s2 == null || s1 == null) {
            return -1;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int[] key = toKey(s2);
        for (int i = 0, j = 0; i < s1.length(); i++) {
            //i是key的指针 也是s1字符串的指针，j是s2的指针
            while (j > 0 && c1[i] != c2[j]) {
                assert key != null;
                j = key[j - 1];
            }
            if (c1[i] == c2[j]) {
                j++;
            }
            if (j == c2.length) {
                return i - j + 1;
            }
        }
        return -1;
    }

    public static int[] toKey2(String s) {
        int[] result = new int[s.length()];
        result[0] = 0;
        char[] chars = s.toCharArray();
        int e = 1;
        int f = 0;
        while (e < s.length()) {
            if (chars[e] == chars[f]) {
                f++;
                result[e] = f;
                e++;
            } else {
                if (f > 0) {
                    f = result[f - 1];
                } else {
                    result[e] = f;
                    e++;
                }
            }
        }
        return result;
    }
}
