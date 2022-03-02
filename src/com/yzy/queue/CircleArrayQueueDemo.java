package com.yzy.queue;

public class CircleArrayQueueDemo {

}

class CircleQueue{
	private int maxSize;//表示队列的最大容量
	private int front;//表示队列的头部，指向队列头第一个元素
	private int rear;//表示队列的尾部，指向队列尾的数据（即队列的最后一个数据）的下一个
	private int[] arr;//模拟队列的数组
	
	//创建队列的构造器
	public CircleQueue(int maxSize){
		this.maxSize=maxSize;
		//初始化数组
		arr=new int[maxSize];
		front=0;
		rear=0;
	}
	
	/**
	 * 
	 * @Description 判断队列是否满
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月18日下午9:26:27
	 * @return true为满；false为空
	 */
	public boolean isFull(){
		return (rear+1)%maxSize==front;
	}
	
	/**
	 * 
	 * @Description 判断队列是否为空
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月18日下午9:26:49
	 * @return true为空；false为非空
	 */
	public boolean isEmpy(){
		return rear==front;
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
		//填充数据,因为rear为尾数据的下一个位置
		arr[rear]=n;
		//rear后移,并取模
		rear=(rear+1)%maxSize;
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
		//1.将front对应的值保存到一个临时变量，front后移并考虑取模，再返回值
		int num=arr[front];
		front=(front+1)%maxSize;
		return num;
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
		
		//从front开始遍历
		for (int i = 0; i < (rear+maxSize-front)%maxSize; i++) {
			System.out.println(arr[(i+front)%maxSize]);
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
		return arr[front];
	}
}	