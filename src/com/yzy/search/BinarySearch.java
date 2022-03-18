package com.yzy.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找必须为有序数组
 * @ClassName BinarySearch
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-16 23:53
 * @Version
 **/
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 5, 88, 90, 165, 233, 245, 678};
        int index = binarySearch(arr, 0, arr.length - 1, 233);
        System.out.println(index);
    }

    public static int binarySearch(int[] arr,int left,int right,int searchVal) {
        int mid=(left+right)/2;
        int midVal = arr[mid];
        int index;

        if (left > right) {
            return index=-1;
        }

        if (searchVal > midVal) {//向右递归
            index=binarySearch(arr, mid + 1, right, searchVal);
        } else if (searchVal < midVal) {
            index = binarySearch(arr, left, mid - 1, searchVal);
        } else {
            index=mid;
        }
        return index;

    }


    //完成一个课后思考题:
    /*
     * 课后思考题： {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
     * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
     *
     * 思路分析
     * 1. 在找到mid 索引值，不要马上返回
     * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 4. 将Arraylist返回
     */

    public static List<Integer> binarySearch2(int[] arr, int left, int right, int searchVal) {
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (left > right) {
            return new ArrayList<Integer>();
        }

        if (searchVal > mid) {
            return binarySearch2(arr, mid + 1, right, searchVal);
        } else if (searchVal < mid) {
            return binarySearch2(arr, left, mid - 1, searchVal);
        } else {
            //			 * 思路分析
//			 * 1. 在找到mid 索引值，不要马上返回
//			 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 4. 将Arraylist返回
            List<Integer> res = new ArrayList<Integer>();
            res.add(mid);
            int temp=mid-1;
            //向左扫描
            while (true) {
                if (temp < 0 || arr[temp] != searchVal) {
                    break;
                }
                res.add(temp);
                temp--;
            }
            //向右扫描
            while (true){
                if (temp>arr.length-1||arr[temp] != searchVal) {
                    break;
                }
                res.add(temp);
                temp++;
            }
            return res;
        }
    }


}
