package com.yzy.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName PolandNotation
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-05 16:12
 * @Version
 **/
public class PolandNotation {

    public static void main(String[] args) {
        //完成将一个中缀表达式转成后缀表达式的功能
        //说明
        //1. 1+((2+3)×4)-5 => 转成  1 2 3 + 4 × + 5 –
        //2. 因为直接对str 进行操作，不方便，因此 先将  "1+((2+3)×4)-5" =》 中缀的表达式对应的List
        //   即 "1+((2+3)×4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //3. 将得到的中缀表达式对应的List => 后缀表达式对应的List
        //   即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  =》 ArrayList [1,2,3,+,4,*,+,5,–]

        String expression ="1+((2+3)*4)-5";
        //将String转化为中缀表达式List
        //即 "1+((2+3)×4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        List<String> infixExpressionList = toInfixExpression(expression);
        System.out.println("中缀表达式对应的List=" + infixExpressionList); // ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  =》 ArrayList [1,2,3,+,4,*,+,5,–]
        //方法：将得到的中缀表达式对应的List => 后缀表达式对应的List
        List<String> suffixExpressionList = parseSuffixExpression(infixExpressionList);
        System.out.println("后缀表达式对应的List" + suffixExpressionList); //ArrayList [1,2,3,+,4,*,+,5,–]

        System.out.printf("expression=%d", calculate(suffixExpressionList)); // ?
    }

    /**
     * 将String转化为中缀表达式List
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 16:38 2022/3/5
     * @param s
     * @return java.util.List<java.lang.String>
     */
    public static List<String> toInfixExpression(String s) {
        int i=0;//索引
        List<String> ls = new ArrayList<String>();//存放中缀表达式
        String str;//多位数拼接
        char c;//遍历字符
        do {
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {//如果c为非数字,直接假如list
                ls.add("" + c);
                i++;
            } else {//是数字，考虑拼接
                str = "";//置空
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    /**
     * 将中缀表达式转化为后缀表达式
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 16:55 2022/3/5
     * @param ls
     * @return java.util.List<java.lang.String>
     */
    public static List<String> parseSuffixExpression(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<String>(); // 符号栈
        //说明：因为s2 这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用 Stack<String> 直接使用 List<String> s2
        //Stack<String> s2 = new Stack<String>(); // 储存中间结果的栈s2
        List<String> s2 = new ArrayList<String>(); // 储存中间结果的Lists2

        //遍历ls
        for (String item : ls) {
            if (item.matches("\\d+")) {//正则表达式 匹配多位数
                s2.add(item);
            } else if ("(".equals(item)) {
                s1.push(item);
            } else if (")".equals(item)) {
                while (!"(".equals(s1.peek())) {
                    //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                    s2.add(s1.pop());
                }
                s1.pop();//!!!将（小括号弹出，消除括号
            } else {//如果是符号，缺少一个符号优先级的比较
                //当item的优先级小于等于s1栈顶运算符, 将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //比较完后还需要将item压入栈或者item优先级更高时，直接压入栈
                s1.push(item);
            }
        }

        //将s1中剩余的运算符依次弹出并加入s2
        while(s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;//注意因为是存放到List, 因此按顺序输出就是对应的后缀表达式对应的List
    }

    //完成对逆波兰表达式的运算
	/*
	 * 1)从左至右扫描，将3和4压入堆栈；
		2)遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
		3)将5入栈；
		4)接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
		5)将6入栈；
		6)最后是-运算符，计算出35-6的值，即29，由此得出最终结果
	 */
    public static int calculate(List<String> ls) {
        Stack<String> stack=new Stack<String>();
        for (String item : ls) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                //先弹出来的，即后加入的
                int num2= Integer.parseInt(stack.pop());
                //后弹出的，即先加入的
                int num1= Integer.parseInt(stack.pop());
                int res=0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res 入栈
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }




}

class Operation{
    private static final int ADD=1;
    private static final int SUB=1;
    private static final int MUL=1;
    private static final int DIV=1;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符" + operation);
                break;
        }
        return result;
    }

}
