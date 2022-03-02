package com.yzy.LinkedList;

public class SingleLinkedListDemo {
	public static void main(String[] args) {
		//创建节点
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
		
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		//方式一：直接添加到末尾
//		singleLinkedList.addNode(hero1);
//		singleLinkedList.addNode(hero2);
//		singleLinkedList.addNode(hero3);
//		singleLinkedList.addNode(hero4);
		//方式二：按照顺序添加
		singleLinkedList.addNodeByOrder(hero1);
		singleLinkedList.addNodeByOrder(hero2);
		singleLinkedList.addNodeByOrder(hero4);
		singleLinkedList.addNodeByOrder(hero3);
		
		singleLinkedList.list();
		
		//修改节点的测试
		HeroNode newHeroNode = new HeroNode(2,"玉麒麟","卢俊义");
		singleLinkedList.update(newHeroNode);
		
		singleLinkedList.list();
		//删除节点的测试
		HeroNode deletHeroNode=new HeroNode(3, "吴用", "智多星") ;
		singleLinkedList.deletNode(deletHeroNode);
		
		singleLinkedList.list();
		
	}
}

/**
 * 
 * @Description 定义SingleLinkedList类，管理HeroNode
 * @author yzy  729141789@qq.com
 * @version
 * @date 2022年1月19日下午9:05:55
 *
 */
class SingleLinkedList{
	//1.初始化一个头节点，不可动,不存放具体的数据
	public HeroNode head=new HeroNode(0,"","");
	
	/**
	 * 
	 * @Description 添加节点到单项链表的末尾
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月19日下午9:22:06
	 */
	/* 思路：不考虑编号的顺序时
	 * 1.遍历链表找到最后一个节点
	 * 2.将最后这个节点的next指向新节点
	 */
	public void addNode(HeroNode heroNode){
		HeroNode temp=head;//辅助节点/指针 遍历链表
		while (temp.next!=null) {
			temp=temp.next;
		}
		//当while结束时，temp指向链表的最后
		temp.next=heroNode;
	}
	
	/**
	 * 
	 * @Description 按照顺序添加节点到单项链表,如果链表中有这个排名，则添加失败，并且给出提示
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月19日下午11:34:31
	 * @param heroNode
	 */
	public void addNodeByOrder(HeroNode heroNode){
		HeroNode temp=head;
		boolean flag=false;//flag表示该排名是存在，false为不存在，为默认值
		while(true){
			if (temp.next==null) {
				break;
			}
			if (temp.next.num>heroNode.num) {//代表找到位置，即为temp后面
				break;
			}else if (temp.next.num==heroNode.num) {
				flag=true;
				break;
			}
			temp=temp.next;
		}
		if (flag) {
			System.out.println("当前数据已存在，添加失败！");
		}
		else{
			heroNode.next=temp.next;
			temp.next=heroNode;
			System.out.println("添加成功");
		}
	}
	
	/**
	 * 
	 * @Description 显示链表（遍历）
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月19日下午9:50:30
	 */
	public void list(){
		//判断链表是否为空
		if (head.next==null) {
			System.out.println("链表为空");
			return;
		}
		HeroNode temp=head.next;
		while(temp!=null){
			System.out.println(temp);
			temp=temp.next;
		}
	}
	
	/**
	 * 
	 * @Description 修改节点信息，根据newHeroNode的num进行修改
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月20日上午12:05:50
	 * @param newHeroNode
	 */
	public void update(HeroNode newHeroNode){
		//判断链表是否为空
		if (head.next==null) {
			System.out.println("链表为空");
			return;
		}
		
		HeroNode temp=head.next;
		boolean flag=false;//表示是否找到该节点
		while(true){
			if (temp==null) {//已经遍历完链表
				break;
			}
			if (temp.num==newHeroNode.num) {//找到该节点
				flag=true;
				break;
			}
			temp=temp.next;
		}
		if (flag) {
			temp.name=newHeroNode.name;
			temp.nickName=newHeroNode.nickName;
		}else{
			System.out.println("没有找到该编号的节点");
		}
	}
	
	
	/**
	 * 
	 * @Description 删除节点
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月20日上午12:18:09
	 * @param heroNode
	 */
	/*
	 * 思路：
	 * 1.找到需要删除的节点的前一个节点temp
	 * 2.将temp.next=temp.next.next
	 */
	public void deletNode(HeroNode heroNode){
		//判断链表是否为空
		if (head.next==null) {
			System.out.println("链表为空");
			return;
		}
		
		HeroNode temp=head.next;
		boolean flag=false;//表示是否找到该节点
		while(true){
			if (temp.next==null) {//表示找到最后一个节点
				break;
			}
			if (temp.next.num==heroNode.num) {
				flag=true;
				break;
			}
			temp=temp.next;
		}
		if (flag) {
			temp.next=temp.next.next;
			System.out.println("删除成功");
		}else{
			System.out.println("未找到该节点，删除失败！");
		}
	}
	
}


/**
 * 
 * @Description 定义HeroNode,每个HeroNode对象为一个节点
 * @author yzy  729141789@qq.com
 * @version
 * @date 2022年1月19日下午8:59:59
 *
 */
class HeroNode{
	public int num;
	public String name;
	public String nickName;
	public HeroNode next;//指向下一个节点
	
	
	//构造器
	public HeroNode(int hnum,String hname,String hnickname){
		 this.num=hnum;
		 this.name=hname;
		 this.nickName=hnickname;
	}


	//为了显示方便，重写toString
	@Override
	public String toString() {
		return "HeroNode [num=" + num + ", name=" + name + ", nickName=" + nickName + "]";
	}
	
	
}