package com.yzy.sort;

import java.util.Arrays;

/**
 * @ClassName InsertSort
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-13 20:50
 * @Version
 **/
public class InsertSort {

    public static void main(String[] args) {
        int[] arr={7,6,1,56,15,3,7};
        insertSort(arr);
    }

    public static void insertSort(int[] arr) {
        int insertVal;//插入值
        int insertIndex;//插入值下标
        //从第二个开始插入
        for (int i = 1; i < arr.length ; i++) {
            insertIndex=i-1;
            insertVal = arr[i];
            while (insertIndex >= 0 && arr[insertIndex] > insertVal) {
                arr[insertIndex+1]=arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex+1]=insertVal;
            System.out.println(Arrays.toString(arr));
        }
    }

}
