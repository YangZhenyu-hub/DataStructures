package com.yzy.LinkedList;


public class SingleLinkedListExer {
	
	public static void main(String[] args) {
		
		SingleLinkedListExer singleLinkedListExer = new SingleLinkedListExer();
		
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
		
		singleLinkedList.addNodeByOrder(hero1);
		singleLinkedList.addNodeByOrder(hero2);
		singleLinkedList.addNodeByOrder(hero3);
		singleLinkedList.addNodeByOrder(hero4);
		singleLinkedList.list();
		
		//1.求单链节点的有效个数
		int nodeCount=singleLinkedListExer.singleLinkedListNodeCount(singleLinkedList);
		System.out.println(nodeCount);
		
		//2.查找单链表中的倒数第k个结点
		HeroNode downCountNode=singleLinkedListExer.findDownCountNode(singleLinkedList, 3);
		System.out.println(downCountNode);
		
		
		//3.单链表的反转
		System.out.println("3.**************");
		SingleLinkedList reverseList = singleLinkedListExer.reverseList2(singleLinkedList);
		reverseList.list();
		
		//4.从尾到头打印单链表 
		System.out.println("4.****************");
		singleLinkedListExer.reversePrintList(singleLinkedList);
		
	}
	

	/**
	 * 
	 * @Description 求单链表中有效节点的个数
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月20日上午11:08:06
	 * @return 有效节点的个数
	 */
	public int singleLinkedListNodeCount(SingleLinkedList singleLinkedList){
		int count=0;
		HeroNode temp=singleLinkedList.head.next;
		if (temp==null) {
			return count;
		}
		while(temp!=null){
			count++;
			temp=temp.next;
		}
		return count;
		
	}
	
	/**
	 * 
	 * @Description 查找单链表中的倒数第k个结点
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月20日下午10:02:26
	 * @param singleLinkedList
	 * @param k 倒数个数
	 * @return 倒数第K个节点
	 */
	public HeroNode findDownCountNode(SingleLinkedList singleLinkedList,int k){
		//得到有效节点的总个数
		int total=singleLinkedListNodeCount(singleLinkedList);
		if (total==0) {
			System.out.println("该链表为空");
			return null;
		}
		if (k>total) {
			System.out.println("输入的数不合符，超过链表长度");
		}
		HeroNode temp=singleLinkedList.head;
		for(int i=0;i<total-k+1;i++)
		{
			temp=temp.next;
		}
		return temp;
		
	}
	
	/**
	 * 
	 * @Description 输出一个反转链表
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月20日下午10:43:12
	 * @param singleLinkedList
	 * @return 反转链表
	 */
	/*
	 * 方法一：
	 * 1.从后往前找遍历，取出该节点
	 * 2.添加到新链表中
	 */
	public SingleLinkedList reverseList(SingleLinkedList singleLinkedList){
		SingleLinkedList reverseLinkedList = new SingleLinkedList();
		
		HeroNode temp=singleLinkedList.head;
		
		int total=singleLinkedListNodeCount(singleLinkedList);
		for(int i=0;i<total;i++){
			for(int j=0;j<total-i;j++)
			{
				temp=temp.next;
			}
			HeroNode reNode=new HeroNode(temp.num, temp.name, temp.nickName);
			reverseLinkedList.addNode(reNode);
			temp=singleLinkedList.head;
		}
		
		return reverseLinkedList;
	}
	
	/**
	 * 
	 * @Description 输出一个反转链表
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月21日上午12:34:25
	 * @param singleLinkedList
	 * @return 反转链表
	 */
	/*
	 * 方式二：
	 * 1.新建一个reverseHead
	 * 2.遍历原来的链表，取出每一个节点
	 * 3.将节点添加到新链表的最前端
	 * 4.将singleLinkedList.head.next=reverseHead.next
	 */
	public SingleLinkedList reverseList2(SingleLinkedList singleLinkedList){
		SingleLinkedList reverseLinkedList=new SingleLinkedList();
		
		HeroNode cur=singleLinkedList.head; //当前节点
		if (cur.next==null||cur.next.next==null) {
			return singleLinkedList;
		}
		
		HeroNode next=null;//下一个节点
		cur=cur.next;
		while(cur!=null)
		{
			//获取cur的下一个节点，防止链表丢失
			next=cur.next;
			//非常关键的一步，将cur的下一个节点指向reverseLinkedList的最前端
			//抹除cur的next链表信息
			//非常的妙！！
			cur.next=reverseLinkedList.head.next;
			//将cur加到reversLinkedList的最前端
			reverseLinkedList.head.next=cur;
			//cur后移
			cur=next;
		}
		singleLinkedList.head=reverseLinkedList.head;
		return reverseLinkedList;
	}
	
	
	/**
	 * 
	 * @Description 从尾到头打印单链表 
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月20日下午10:45:59
	 * @param singleLinkedList
	 */
	public void reversePrintList(SingleLinkedList singleLinkedList){
		HeroNode temp=singleLinkedList.head;
		int total=singleLinkedListNodeCount(singleLinkedList);
		
		for(int i=0;i<total;i++)
		{
			for(int j=0;j<total-i;j++){
				temp=temp.next;
			}
			System.out.println(temp);
			temp=singleLinkedList.head;
		}
		
	}

}
