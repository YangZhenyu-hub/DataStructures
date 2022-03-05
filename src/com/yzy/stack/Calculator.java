package com.yzy.stack;

/**
 * @ClassName Caculator
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-05 10:26
 * @Version
 **/
/*
使用栈完成表达式的计算思路
1.设置一个index值（索引）遍历表达式
2.如果发现是一个数字，就直接入数栈
3.如果扫描到一个符号，则分如下情况
 3.1.如果当前符号栈为空，则直接入栈
 3.2.如果符号栈有符号，则进行比较，
     如果当前操作符优先级小于或者等于栈中的操作符，则需要从数栈pop出两个数，再从符号栈中pop出一个操作符进行运算，得到的结果入数栈，操作符入符号栈
     如果当前操作符优先级大于栈中的操作符，就直接入符号栈
4.表达式扫描完毕后，就从数栈和符号栈pop出相应的符号和数，并运算
5.最后数栈中只有一个数字，就是表达式结果。
 */
public class Calculator {
    public static void main(String[] args) {
        String expression ="3+2*6-2";
        //数栈
        ArrayStack2 numStack = new ArrayStack2(10);
        //符号栈
        ArrayStack2 operStack = new ArrayStack2(10);
        int index=0;//扫描索引
        int num1=0;
        int num2=0;
        int res=0;
        int oper=0;
        char ch=' ';//保存扫描到的char字符
        String keepNum = "";//用于拼接多位数

        //1.while循环扫描
        while(true){
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOpera(ch)) {//如果是运算符
                if (operStack.isEmpty()) {//如果符号栈为空
                    operStack.push(ch);
                } else {
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {//当前操作符优先级小于栈中的操作符
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = operStack.cal(num1, num2, oper);
                        //将结果压入数栈
                        numStack.push(res);
                        //将当前操作符压入符号栈
                        operStack.push(ch);
                    } else {
                        //当前操作优先级大于栈中操作符，直接入栈
                        operStack.push(ch);
                    }
                }
            } else {//如果是数
                //numStack.push(ch - 48); //? "1+3" '1' => 1
                //分析思路
                //1. 当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //2. 在处理数，需要向expression的表达式的index 后再看一位,如果是数就进行扫描，如果是符号才入栈
                //3. 因此我们需要定义一个变量 字符串，用于拼接
                keepNum+=ch;

                if (index == expression.length() - 1) {//如果index已经是最后一个字符了，直接入栈
                    numStack.push(Integer.parseInt(keepNum));
                }else {

                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    //注意是看后一位，不是index++
                    if (operStack.isOpera(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符，则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //keepNum置空！！
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //扫描完毕,进行最后的计算
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        int res2 = numStack.pop();
        System.out.printf("表达式 %s = %d", expression, res2);



    }
}

//先创建好一个栈，直接直接使用前面创建好的
class ArrayStack2{
    private int maxSize;//栈的大小
    private int top=-1;//表示栈顶，初始化为-1
    private int[] stack;//数组模拟栈

    ArrayStack2(int maxSize) {
        this.maxSize=maxSize;
        //初始化栈
        stack=new int[maxSize];
    }

    public int peek() {
        return stack[top];
    }

    public boolean isFull() {
        return top==maxSize-1;
    }

    public boolean isEmpty() {
        return top==-1;
    }

    public void push(int value) {
        if (isFull()){
            System.out.println("栈已满");
            return;
        }
        top++;
        stack[top]=value;
    }

    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈为空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void list() {
        if (isEmpty()) {
            System.out.println(("栈空"));
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d]=%d",i,stack[i]);
        }
    }

    //判断是否是一个运算符
    public boolean isOpera(char value) {
        return value=='+'||value=='-'||value=='*'||value=='/';
    }

    //返回运算符的优先级，优先级是程序员来确定, 优先级使用数字表示
    //数字越大，则优先级就越高.
    public int priority(int oper){
        if (oper == '*' || oper == '/') {
            return 1;
        }else if (oper=='+'||oper=='-'){
            return 0;
        }else{
            return -1; //假设当前只有+ - * /
        }
    }

    public int cal(int num1,int num2,int oper){
        int res=0;
        switch (oper) {
            case '*':
                res=num1*num2;
                break;
            case '/':
                res=num2/num1;
                break;
            case '+':
                res=num2+num1;
                break;
            case '-':
                res=num2-num1;
                break;
            default:
                break;
        }
        return res;
    }

}
