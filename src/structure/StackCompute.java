package structure;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @ClassName StackCompute
 * @Description TODO
 * 1.实现自定义栈
 * 2.中缀表达式转后缀表达式(输入字符串，以空格分割)
 * 3.后缀表达式计算结果
 * @Author liang
 * @Date 2018/7/9 17:01
 * @Version 1.0
 **/
public class StackCompute {

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }


    /**
     * 中缀表达式转后缀表达式
     *
     * @param str
     * @return
     */
    public static String transition(String str) {
        //按空格分割
        String[] elements = str.split(" ");
        StringBuffer sb = new StringBuffer();
        myStack mystack = new myStack();
        for (String element : elements) {
            if (isInteger(element)) {
                sb.append(element + " ");
            } else {
                switch (element) {
                    case "(":
                    case "*":
                    case "/":
                        mystack.addElement(element);
                        continue;
                    case "+":
                    case "-":
                        //判断栈顶元素是否为* /
                        if (mystack.searchElement("*") != -1 || mystack.searchElement("/") != -1) {
                            //将括号内的元素给sb
                            while (mystack.peekElement() != null) {
                                if ("(".equals(element)) {
                                    break;
                                }
                                sb.append(mystack.popElement() + " ");
                            }
                            //最后自己进栈
                            mystack.addElement(element);
                        } else {
                            mystack.addElement(element);
                        }
                        continue;
                    case ")":
                        //取出括号内的东西
                        int result = mystack.searchElement("(");
                        if (result != -1) {
                            while (mystack.peekElement() != null) {
                                if (mystack.peekElement().equals("(")) {
                                    mystack.popElement();
                                    break;
                                }
                                sb.append(mystack.popElement() + " ");
                            }
                        }
                        continue;
                }
            }
        }
        //出栈
        while (mystack.peekElement() != null) {
            sb.append(mystack.popElement() + " ");
        }
        return sb.toString();
    }

    /**
     * 计算结果
     * 碰到运行符，取出栈顶元素作为x数，栈顶下面的元素作为被x数
     *
     * @param str
     * @return
     */
    public static int getResult(String str) {
        String[] elements = str.split(" ");
        myStack mystack = new myStack();
        for (String elment : elements) {
            if (isInteger(elment)) {
                mystack.addElement(Integer.valueOf(elment));
            } else {
                int b = (int) mystack.popElement();//x数
                int a = (int) mystack.popElement();//被x数
                switch (elment) {
                    case "+":
                        mystack.addElement(a + b);
                        continue;
                    case "-":
                        mystack.addElement(a - b);
                        continue;
                    case "*":
                        mystack.addElement(a * b);
                        continue;
                    case "/":
                        mystack.addElement(a / b);
                        continue;
                }
            }
        }
        return (int) mystack.popElement();
    }

    public static void main(String[] args) {
        String s = "1 + ( 2 - 5 ) * 2 + 10 / 2";
        String h = transition(s);
        System.out.println("后缀表达式：" + h);
        System.out.println("计算结果：" + getResult(h));
    }


    /**
     * 自定义栈的实现
     */
    static class myStack {
        Object elementData[];
        int elementCount;

        myStack() {
            this.elementData = new Object[10];
            elementCount = 0;
        }

        public void addElement(Object o) {
            //先判断栈容量
            if (elementData.length <= elementCount)
                elementData = Arrays.copyOf(elementData, elementData.length + 10);//扩容
            elementData[elementCount] = o;
            elementCount++;
        }

        public Object popElement() {
            //弹出栈顶元素
            if (elementCount == 0) {
                return null;
            } else {
                Object l = elementData[elementCount - 1];
                elementData[elementCount - 1] = null;
                elementCount--;
                return l;
            }
        }

        //查看栈顶元素
        public Object peekElement() {
            if (elementCount == 0) {
                return null;
            } else {
                return elementData[elementCount - 1];
            }
        }

        //查找元素
        public int searchElement(Object o) {
            if (elementCount > 0)
                for (int i = 0; i < elementCount; i++) {
                    if (o == null) {
                        return -1;
                    } else {
                        if (o.equals(elementData[i]))
                            return i;
                    }
                }
            return -1;
        }

        //取出元素
        public Object getElement(int i) {
            if (i > elementCount)
                return null;
            Object o = elementData[i];
            //后面的元素下标全部减一
            if (elementCount - 1 > i) {
                for (int j = i; j < elementCount - 1; i++) {
                    elementData[j] = elementData[j + 1];
                }
            }
            elementData[elementCount - 1] = null;
            elementCount--;
            return o;
        }

        @Override
        public String toString() {
            return "myStack{" +
                    "elementData=" + Arrays.toString(elementData) +
                    ", elementCount=" + elementCount +
                    '}';
        }
    }
}