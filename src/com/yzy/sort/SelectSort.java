package com.yzy.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName SelectSort
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-13 17:11
 * @Version
 **/
public class SelectSort {
    public static void main(String[] args) {

        //int [] arr = {101, 34, 119, 1, -1, 90, 123};

        //创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        System.out.println("排序前");
        //System.out.println(Arrays.toString(arr));

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        selectSort(arr);


        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);

        //System.out.println("排序后");
        //System.out.println(Arrays.toString(arr));

    }
    public static void selectSort(int[] arr) {
        int minIndex;//最小值的下标
        int min;//最小值
        for (int i = 0; i < arr.length - 1; i++) {//每次寻找一个最小值，一共找arr.length-1次
            minIndex=i;//假定当前的i即为最小值
            min = arr[minIndex];
            for (int j = i+1; j <arr.length ; j++) {//当前i与i+1开始比
                if (min > arr[j]) {//说明当前的i不是最小值
                    minIndex=j;//记录最小值下标
                    min = arr[minIndex];//记录当前找到的最小值
                }
            }
            if (minIndex != i) {//说明在j之后找到了最小值
                arr[minIndex] = arr[i];
                arr[i] =min;
            }

        }
    }
}

