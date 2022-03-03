package com.yzy.LinkedList;

/**
 * @ClassName Josephu
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-03 11:14
 * @Version
 **/
public class Josephu {
    public static void main(String[] args) {
        // 测试一把看看构建环形链表，和遍历是否ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(125);// 加入5个小孩节点
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(10,20,125);

    }
}

class CircleSingleLinkedList{
    //first节点，当前没有编号
    private Boy first=null;

    public void addBoy(int nums) {
        //nums值校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        //辅助节点，帮助构建环形链表
        Boy curBoy=null;
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy =new Boy(i);
            if (i == 1) {
                first=boy;
                first.setNext(first);
                curBoy=first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;
            }
        }
    }

    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("没有小孩");
            return;
        }
        Boy curBoy=first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNum());
            if (curBoy.getNext() == first) {
                //说明已经遍历完成
                return;
            }
            curBoy=curBoy.getNext();
        }
    }


    /**
     * 根据用户输入，计算小孩出圈顺序
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 11:31 2022/3/3
     * @param startNum 从第几个小孩开始数
     * @param countNum 每次数几下
     * @param nums 表示最初有多少小孩
     * @return void
     */
    public void countBoy(int startNum, int countNum, int nums) {
        //先对数据进行校验
        if (startNum < 1 || startNum > nums || first == null) {
            System.out.println(("参数输入有误，请重新输入"));
            return;
        }
        //辅助节点
        Boy helper=first;
        //helper指向链表的最后节点，即first前一个，帮助出圈
        while (true) {
            if (helper.getNext() == first) {
                //说明helper指向了最后节点
                break;
            }
            helper=helper.getNext();
        }

        //在出圈前，将first与helper前移startNum，即找到开始的位置
        for (int i = 0; i < startNum - 1; i++) {
            first=first.getNext();
            helper=helper.getNext();
        }

        //开始出圈

        while (true) {
            if (helper == first) {
                //圈中只剩一个小孩，出圈结束
                break;
            }

            //一次出圈操作
            for (int i = 0; i < countNum-1; i++) {//找到该次出圈的小孩
                first=first.getNext();
                helper=helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNum());
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.getNum());


    }

}


class Boy{
    private int num;
    private Boy next;

    public Boy(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
