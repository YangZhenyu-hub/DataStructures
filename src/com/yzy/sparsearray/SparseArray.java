package com.yzy.sparsearray;
/**
 * 
 * @Description 二维数组与稀疏数组的转换
 * @author yzy  729141789@qq.com
 * @version
 * @date 2022年1月16日上午12:52:21
 *
 */
public class SparseArray {
	public static void main(String[] args) {
		SparseArray sparseArray=new SparseArray();
		//创建一个原始的二维数组
		int chessArr1[][]=new int[11][11];
		chessArr1[1][2]=1;
		chessArr1[2][3]=2;
		chessArr1[4][5]=2;
		chessArr1[7][8]=1;
		//输出原始的二维数组
		System.out.println("原始的二维数组");
		for(int[] row:chessArr1){
			for(int data :row){
				System.out.print(data+"\t");
			}
			System.out.println();
		}
		
		//二维数组转稀疏数组
		int[][] sparseArr = sparseArray.toSparseArray(chessArr1);
		System.out.println("稀疏数组为");
		for(int[] row:sparseArr){
			for(int data:row){
				System.out.print(data+"\t");
			}
			System.out.println();
		}
		
		
		//稀疏数组转二维数组
		System.out.println("二维数组为");
		int[][] array = sparseArray.sparseArrayToArray(sparseArr);
		for(int[] row:array){
			for(int data:row){
				System.out.print(data+"\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * 
	 * @Description 将二维数组转换为sparseArray（稀疏数组）
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月16日上午12:12:19
	 * @param chessArr1 二维数组
	 * @return 
	 * @return 
	 */
	
	/*
	 * 思路：
	 * 1.遍历数组，获取非0数据的个数
	 * 2.创建一个sparseArray
	 * 3.给稀疏数组赋值
	 */
	public int[][] toSparseArray(int[][] chessArr1){
		int totalNum=0;
		//1.遍历数组，获取非0个数
		for(int[] row:chessArr1){
			for(int data:row){
				if (data!=0) {
					totalNum++;
				}
			}
			
		}
		System.out.println(totalNum);
		
		//2.创建对应的sparseArray
		int[][] sparseArray=new int[totalNum+1][3];
		
		//3.给稀疏数组赋值
		//3.1第一行为原二维数组的行数和列数
		int rows=chessArr1.length;
		int column=chessArr1[0].length;
		sparseArray[0][0]=rows;
		sparseArray[0][1]=column;
		sparseArray[0][2]=totalNum;
		
		int count=1;//用于记录是第几个非0数据
		//3.2填充有效值
		for(int i=0;i<rows;i++){
			for(int j=0;j<column;j++)
			{
				if (chessArr1[i][j]!=0) {
					sparseArray[count][0]=i;
					sparseArray[count][1]=j;
					sparseArray[count][2]=chessArr1[i][j];
					count++;
				}
			}
		}
		

		//4.输出稀疏数组
		return sparseArray;		
	}
	
	
	/**
	 * 
	 * @Description 将稀疏数组转换回二维数组
	 * @author yzy  729141789@qq.com
	 * @date 2022年1月16日上午12:41:23
	 * @param sparseArray 稀疏数组
	 * @return 二维数组
	 */
	
	/*
	 * 思路：
	 * 1.根据稀疏数组创建一个二维数组
	 * 2.填充二维数组
	 */
	public int[][] sparseArrayToArray(int[][] sparseArray){
		//1.根据稀疏数组创建一个二维数组
		int rows=sparseArray[0][0];
		int columns=sparseArray[0][1];
		int[][] array=new int[rows][columns];
		for(int i=0;i<sparseArray[0][2];i++)
		{
			//获取有效值的行数与列数
			int row=sparseArray[i+1][0];
			int column=sparseArray[i+1][1];
			array[row][column]=sparseArray[i+1][2];
		}
		return array;
	}
	
	
}
