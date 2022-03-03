package com.yzy.stack;

/**
 * @ClassName SingleLinkedListStackDemo
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-03 15:57
 * @Version
 **/
public class SingleLinkedListStackDemo {
}

class SingleLinkedListStack{
    StackNode head=new StackNode();//头节点，不存放数据
    StackNode top=head;//栈顶节点

    public boolean isEmpty() {
        return top==head;
    }

    public void push(int value) {
        StackNode newStackNode=new StackNode(value);
        top.next=newStackNode;
        top=top.next;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        StackNode temp=head;
        while (true) {
            if (temp.next == top) {
                break;
            }
            temp=temp.next;
        }
        int value= top.value;
        top=temp;
        return value;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空");
        }
        StackNode temp=head;
        while (true) {

            while (true) {
                if (temp.next == top) {
                    break;
                }
                temp = temp.next;
            }
            System.out.println(top.value);
            top=temp;
            if (temp==head){
                break;
            }
        }
    }


}

class StackNode{
    public int value;
    public StackNode next;

    public StackNode(){}

    public StackNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}


