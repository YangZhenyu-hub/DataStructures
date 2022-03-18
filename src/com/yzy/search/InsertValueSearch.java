package com.yzy.search;

/**
 * 二分查找升级版 也要求数组有序
 * @ClassName InsertValueSearch
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-17 21:32
 * @Version
 **/
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i;
        }
    }


    public static int insertValueSearch(int[] arr,int left,int right,int searchValue) {
        //注意：findVal < arr[0]  和  findVal > arr[arr.length - 1] 必须需要
        //否则我们得到的 mid 可能越界
        if (left > right || searchValue < arr[0] || searchValue > arr[arr.length - 1]) {
            return -1;
        }

        int mid=left+(right-left)*(searchValue-arr[left])/(arr[right]-arr[left]);
        int midVal = arr[mid];
        if (searchValue > midVal) {
            return insertValueSearch(arr, mid + 1, right, searchValue);
        } else if (searchValue < midVal) {
            return insertValueSearch(arr, left, mid - 1, searchValue);
        } else {
            return mid;
        }

    }

}
