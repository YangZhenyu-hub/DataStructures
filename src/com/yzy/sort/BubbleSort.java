package com.yzy.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName BubbleSort
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-13 11:24
 * @Version
 **/
public class BubbleSort {
    public static void main(String[] args) {
        //		int arr[] = {3, 9, -1, 10, 20};
//
//		System.out.println("排序前");
//		System.out.println(Arrays.toString(arr));

        //为了容量理解，我们把冒泡排序的演变过程，给大家展示

        //测试一下冒泡排序的速度O(n^2), 给80000个数据，测试
        //创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for(int i =0; i < 80000;i++) {
            arr[i] = (int)(Math.random() * 8000000); //生成一个[0, 8000000) 数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        //测试冒泡排序
        bubbleSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是=" + date2Str);

        //System.out.println("排序后");
        //System.out.println(Arrays.toString(arr));

    }

    public static void bubbleSort(int[] arr) {
        boolean flag = true;//排序是否完成的标志，如排序过程中提前完成，可直接退出循环
        int temp ;
        for (int i = 0; i < arr.length - 1; i++) {//最多排序arr.length-1次，每次找出一个最大的排在后面
            for (int j = 0; j < arr.length - 1 - i; j++) {//第i次排序只需对比arr.length-1-i次
                if (arr[j] > arr[j + 1]) {
                    flag=false;
                    temp=arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] =temp;
                }
            }
            if (flag) {
                break;
            } else {
                flag=true;
            }
        }
    }
}
