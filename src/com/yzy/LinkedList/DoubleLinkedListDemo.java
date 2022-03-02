package com.yzy.LinkedList;

/**
 * @ClassName DoubleLinkedListDemo
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-02 23:28
 * @Version
 **/
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        // 测试
        System.out.println("双向链表的测试");
        // 先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        // 修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        // 删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.list();



    }
}


/**
 * 创建一个单向列表的类
 * @Description TODO
 * @author yzy 729141789@qq.com
 * @Date 0:21 2022/3/3
 * @return
 */
class DoubleLinkedList{
    // 先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode2 head=new HeroNode2(0,"","");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //遍历打印双向链表
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp=temp.next;

        }
    }

    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode) {
        HeroNode2 temp=head;
        while (temp.next != null) {
            temp=temp.next;
        }
        //此时temp为最后一个节点
        temp.next=heroNode;
        heroNode.pre=temp;
    }

    public void update(HeroNode2 heroNode) {
        HeroNode2 temp=head;
        //1.判空
        if (temp.next == null) {
            System.out.println("链表为空");
        }
        while (true) {
            if (temp == null) {
                //遍历完列表
                System.out.printf("没有找到 编号 %d 的节点，不能修改\n", heroNode.num);
                break;
            }
            if (temp.num == heroNode.num) {
                temp.name = heroNode.name;
                temp.nickName= heroNode.nickName;
                break;
            }
            temp=temp.next;
        }
    }


    // 从双向链表中删除一个节点,
    // 说明
    // 1 对于双向链表，我们可以直接找到要删除的这个节点
    // 2 找到后，自我删除即可
    public void del(int num) {
        HeroNode2 temp=head;
        boolean flag=false;//标志是否找到删除节点

        if (temp.next == null){
            System.out.println("链表为空，无法删除");
        }

        while (true) {
            if (temp == null) {
                break;
            }
            if (num == temp.num) {
                flag = true;
                break;
            }
            temp=temp.next;
        }
        if (flag) {//找到
            temp.pre.next=temp.next;
            //这里存在一个问题，如果temp是最后一个节点，则temp.next为空
            if (temp.next != null) {
                temp.next.pre=temp.pre;
            }
        }else {
            System.out.printf("要删除的 %d 节点不存在\n", num);
        }
    }



}

/**
 * 链表节点
 * @Description TODO
 * @author yzy 729141789@qq.com
 * @Date 23:56 2022/3/2
 * @return
 */
class HeroNode2 {
    public int num;
    public String name;
    public String nickName;
    public HeroNode2 pre; //前一个节点
    public HeroNode2 next;//后一个节点

    public HeroNode2(int num,String name,String nickName){
        this.num=num;
        this.name=name;
        this.nickName=nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
