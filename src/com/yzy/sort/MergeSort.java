package com.yzy.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName MergeSort
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-15 15:11
 * @Version
 **/
public class MergeSort {
    public static void main(String[] args) {
        //int arr[] = { 8, 4, 5, 7, 1, 3, 6, 2 }; //

        //测试快排的执行速度
        // 创建要给80000个的随机的数组
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }
        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        int temp[] = new int[arr.length]; //归并排序需要一个额外空间
        mergeSort(arr, 0, arr.length - 1, temp);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
        //int temp[] = new int[arr.length];
        //mergeSort(arr,0,arr.length-1,temp);
        //System.out.println("归并排序后=" + Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid=(left + right) / 2;
            //向左进行递归分解
            mergeSort(arr, left, mid, temp);
            //向右进行递归分解
            mergeSort(arr, mid + 1, right, temp);
            //分解一次，合并一次
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     *
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 15:16 2022/3/15
     * @param arr 原始数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边有序序列的初始索引
     * @param temp 中转数组
     * @return void
     */
    public static void merge(int[] arr,int left, int mid, int right,int[] temp) {
        int i=left;//左边索引
        int j=mid+1;//右边
        int t=0;//temp数组的当前索引

        //(一)
        //先把左右两边(有序)的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                i++;
                t++;
            } else {
                temp[t] = arr[j];
                j++;
                t++;
            }
        }
        //(二)
        //把有剩余数据的一边的数据依次全部填充到temp
        while (i <= mid) {
            temp[t] = arr[i];
            i++;
            t++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            j++;
            t++;
        }

        //(三)
        //将temp数组的元素拷贝到arr
        //注意，并不是每次都拷贝所有
        t=0;
        int tempLeft=left;
        while (tempLeft <= right) {//每次不是从0开始添加
            arr[tempLeft]=temp[t];
            tempLeft++;
            t++;
        }

    }

}
