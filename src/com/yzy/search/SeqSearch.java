package com.yzy.search;

/**
 * @ClassName seqSearch
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-16 23:48
 * @Version
 **/
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int index = seqSearch(arr, 5);
        System.out.println(index);
    }

    public static int seqSearch(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }

}
