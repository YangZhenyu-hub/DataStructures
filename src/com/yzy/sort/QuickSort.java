package com.yzy.sort;

import java.util.Arrays;

/**
 * @ClassName QuickSort
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-15 10:06
 * @Version
 **/
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9,78,0,23,-567,70, -1,900, 4561};
        quickSort(arr,0,arr.length-1);
        System.out.println("arr=" + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l=left;//左下标
        int r=right;//右下标
        int pivot = arr[(l + r) / 2];//中轴值
        int temp ;
        while (l < r) {
            while (arr[l] < pivot) {//找到左边比pivot大的值
                l++;
            }
            while (arr[r] > pivot) {//找到右边比pivot小的值
                r--;
            }
            if (l >= r) {//说明排序完成，左边无比pivot大的值，右边无比pivot小的值，左右小标重合
                break;
            }
            //交换
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;

            //出现l,r有一个到达中轴
            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }
        }

        if (l == r) {//防止两个排序好的数字无限递归   如{2，3} r=l=0 ,right=1 右递归-->又是{2，3}重复
            l+=1;
            r-=1;
        }
        //向左递归
        if (left < r) { //left=r 即为排序完成，递归至只有一个值
            quickSort(arr,left,r);
        }
        if (right > l) {
            quickSort(arr, l, right);
        }

    }
}
