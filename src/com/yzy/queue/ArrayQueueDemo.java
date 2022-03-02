package com.yzy.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
	public static void main(String[] args) {
		ArrayQueue arrayQueue=new ArrayQueue(3);
		char key=' ';//接收用户输入
		Scanner scanner = new Scanner(System.in);
		boolean loop=true;
		while(loop){
			System.out.println("s(show):显示队列");
			System.out.println("e(exit):退出程序");
			System.out.println("a(add):添加数据到队列");
			System.out.println("g(get):从队列取出数据");
			System.out.println("h(head):查看队列头的数据");
			key=scanner.next().charAt(0);
			switch (key) {
			case 's':
				arrayQueue.showQueue();
				break;
			case 'e':
				scanner.close();
				loop=false;
				break;
			case 'a':
				System.out.println("输入一个数");
				int value = scanner.nextInt();
				arrayQueue.addQueue(value);
				break;
			case 'g':
				try {
					int result=arrayQueue.getQueue();
					System.out.println("result:"+result);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			case 'h':
				try {
					int result=arrayQueue.headQueue();
					System.out.println("result:"+result);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			}
		}
		
		System.out.println("程序退出");
	}
	
}

class ArrayQueue{
	private int maxSize;//表示队列的最大容量
	private int front;//表示队列的头部，指向队列头的前一个位置
	private int rear;//表示队列的尾部，指向队列尾的数据（即队列的最后一个数据）
	private int[] arr;//模拟队列的数组
	
	//创建队列的构造器
	public ArrayQueue(int maxSize){
		this.maxSize=maxSize;
		//初始化数组
		arr=new int[maxSize];
		front=-1;
		rear=-1;
	}
	
	/**
	 * 
	 * @Description 判断队列是否满
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月18日下午9:26:27
	 * @return true为满；false为空
	 */
	public boolean isFull(){
		return rear==maxSize-1;
	}
	
	/**
	 * 
	 * @Description 判断队列是否为空
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月18日下午9:26:49
	 * @return true为空；false为非空
	 */
	public boolean isEmpy(){
		return rear==-1;
	}
	
	/**
	 * 
	 * @Description 添加数据到队列中
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月18日下午9:29:20
	 * @param n
	 */
	public void addQueue(int n){
		//判断队列是否满
		if(isFull()){
			System.out.println("队列满，不能加入数据");
			return;
		}
		//填充数据
		arr[++rear]=n;
	}
	
	/**
	 * 
	 * @Description 获取队列数据，出队列
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月18日下午9:30:03
	 * @return
	 */
	public int getQueue(){
		if (isEmpy()) {
			//通过抛出异常
			throw new RuntimeException("队里空，不能取数据");
		}
		return arr[++front];
	}
	
	/**
	 * 
	 * @Description 显示队列的所有数据
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月18日下午9:37:07
	 */
	public void showQueue(){
		if (isEmpy()) {
			System.out.println("队列是空的，没有数据");
			return;
		}
		for (int i = front; i < rear; i++) {
			System.out.println(arr[i+1]);
		}
	}
	
	/**
	 * 
	 * @Description 显示队列的头数据，并非取出数据
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月18日下午11:45:54
	 * @return
	 */
	public int headQueue(){
		if (isEmpy()) {
			System.out.println("队列是空的，没有数据");
			throw new RuntimeException("队列为空");
		}
		return arr[front+1];
	}
	
	
	
	
}