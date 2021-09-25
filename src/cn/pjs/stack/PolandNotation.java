package cn.pjs.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        String s = "38*6+5/7-8";
        System.out.println("中缀:" + toInfixList(s));
        System.out.println("后缀：" + toSuffixList(toInfixList(s)));
        System.out.println("结果：" + suffixToCal(toSuffixList(toInfixList(s))));
    }

    /**
     * 转成后缀集合
     *
     * @param list：传入中缀集合
     */
    public static List<String> toSuffixList(List<String> list) {
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        for (String item : list) {
            if (item.matches("\\d+")) {//判断是否数字
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                while (s1.size() != 0 && priority(s1.peek()) >= priority(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 比较优先级
     *
     * @param c:传入符号
     * @return 返回优先级
     */
    public static int priority(String c) {
        if (c.equals("*") || c.equals("/")) {
            return 1;
        } else if (c.equals("+") || c.equals("-")) {
            return 0;
        }
        throw new RuntimeException("错误运算符");
    }

    /**
     * 转成中缀集合
     *
     * @param s:传入解析的字符串
     */
    public static List<String> toInfixList(String s) {
        List<String> list = new ArrayList<String>();
        int i = 0;
        char c;
        StringBuilder str;
        while (i < s.length()) {
            //判断是不是符号
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                list.add("" + c);
                i++;
                //不是符号则是数字
            } else {
                str = new StringBuilder();
                //循环判断下一个是不是数字，记得判断是否越界
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str.append(c);//拼接
                    i++;
                }
                list.add(str.toString());//返回数字
            }

        }
        return list;//返回集合
    }

    /**
     * 后缀表达式计算
     *
     * @param list:传入的后缀表达式
     */
    public static int suffixToCal(List<String> list) {
        int res = 0;
        Stack<String> stack = new Stack<>();
        for (String li : list) {
            if (li.matches("\\d+")) {
                stack.push(li);
            } else {
                int pop2 = Integer.parseInt(stack.pop());
                int pop1 = Integer.parseInt(stack.pop());
                switch (li) {
                    case "+":
                        res = pop1 + pop2;
                        break;
                    case "-":
                        res = pop1 - pop2;
                        break;
                    case "*":
                        res = pop1 * pop2;
                        break;
                    case "/":
                        res = pop1 / pop2;
                        break;
                    default:
                        new RuntimeException("符号出错");
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.peek());
    }
}
