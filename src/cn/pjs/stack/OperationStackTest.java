package cn.pjs.stack;

public class OperationStackTest {
    public static void main(String[] args) {
        OperationStack number = new OperationStack(10);
        OperationStack operator = new OperationStack(10);
        String s = "6-(2+3)";//-3 7-2*6+2  2+2*3-2*1-1
        char c = ' ';
        int num1 = 0;
        int num2 = 0;
        int index = 0;
        char opera = ' ';
        int res = 0;
        while (true) {
            c = s.substring(index, index + 1).charAt(0);
            if (isInt(c)) {
                number.push(c - '0');
            } else {
                //判断符号栈是否为空，为空就直接入，不为空就判断
                if (operator.isEmpty()) {
                    operator.push(c);
                } else {
                    //判断符号的优先级
                    if (priority(c) > priority((char) operator.peek())) {
                        operator.push(c);
                    } else {
                        num1 = number.pop();
                        num2 = number.pop();
                        opera = (char) operator.pop();
                        res = cal(num1, num2, opera);
                        number.push(res);
                        operator.push(c);

                    }
                }
            }
            index++;
            if (index >= s.length()) {
                break;
            }
        }

        while (true) {
            if (operator.isEmpty()) {
                break;
            }
            num1 = number.pop();
            num2 = number.pop();
            opera = (char) operator.pop();
            res = cal(num1, num2, opera);
            number.push(res);
        }
        System.out.println(number.peek());


    }

    public static boolean isInt(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/') {
            return false;
        }
        return true;
    }

    public static int priority(char c) {
        if (c == '*' || c == '/') {
            return 1;
        } else if (c == '+' || c == '-') {
            return 0;
        }
        throw new RuntimeException("错误运算符");
    }

    /**
     * 运算操作
     *
     * @param num1：栈顶数值
     * @param num2：倒数第二个
     * @param operator：运算符
     * @return：运算结果
     */
    public static int cal(int num1, int num2, char operator) {
        int value;
        switch (operator) {
            case '+':
                value = num2 + num1;
                break;
            case '-':
                value = num2 - num1;
                break;
            case '*':
                value = num2 * num1;
                break;
            case '/':
                value = num2 / num1;
                break;
            default:
                value = 0;
                break;
        }
        return value;

    }
}

class OperationStack {
    public int maxSize;
    public int[] stack;
    public int top = -1;
    int count = 0;

    public OperationStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public int[] getStack() {
        return stack;
    }

    public void setStack(int[] stack) {
        this.stack = stack;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int peek() {
        return stack[top];
    }

    /**
     * 判断是否为空
     *
     * @return:真为空
     */
    public boolean isEmpty() {
        if (top == -1) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否满
     *
     * @return
     */
    public boolean isFull() {
        if (top == maxSize - 1) {
            return true;
        }
        return false;
    }

    public void push(int i) {
        if (isFull()) {
            System.out.println("满了");
            return;
        }
        top++;
        stack[top] = i;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("空的");
        }
        int temp = stack[top];
        stack[top] = 0;
        top--;
        return temp;
    }

    public void show() {
        while (true) {
            if (top == -1) {
                break;
            }
            int temp = stack[top];
            System.out.printf("stack[%d]=%d\n", top, temp);
            top--;
        }
    }

    public int count() {
        while (true) {
            if (top == -1) {
                break;
            }
            count++;
            top--;
        }
        return count;
    }


}
