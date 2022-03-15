package com.yzy.sort;

import java.util.Arrays;

/**
 * @ClassName ShellSort
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-14 23:48
 * @Version
 **/
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));

    }
    public static void shellSort1(int[] arr) {
        int temp;
        for (int gap = arr.length/2; gap >0 ; gap/=2) {
            //交换法
            for (int i = gap; i < arr.length; i++) {
                for (int j = i-gap; j >=0 ; j-=gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    public static void shellSort2(int[] arr) {
        for (int gap = arr.length/2; gap >0 ; gap/=2) {
            for (int i = gap; i <arr.length ; i++) {
                int insertIndex=i-gap;
                int insertVal = arr[i];
                while (insertIndex >= 0 && arr[insertIndex] > insertVal) {
                    arr[insertIndex+gap] = arr[insertIndex];//大值后移
                    insertIndex-=gap;
                }
                arr[insertIndex+gap]=insertVal;
            }
        }

    }
}




