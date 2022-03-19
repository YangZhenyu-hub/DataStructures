package com.yzy.tree;

/**
 * @ClassName HeapSort
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-19 21:16
 * @Version
 **/
public class HeapSort {
    public static void main(String[] args) {

    }

    public static void heapSort(int[] arr) {
        int temp = 0;//临时变量

        //第一次调整为有序
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        		/*
		 * 2).将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
　　			3).重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
		 */
        for(int j = arr.length-1;j >0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;

            //最大的堆顶元素被调整至arr[j]，不参与下次排序，arr[0]此时非最大值，而arr[1]、arr[2]为次大值，
            adjustHeap(arr, 0, j);
        }

        //System.out.println("数组=" + Arrays.toString(arr));

    }
    //将一个数组(二叉树), 调整成一个大顶堆
    /**
     * 功能： 完成 将 以 i 对应的非叶子结点的树调整成大顶堆
     * 举例  int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到 {4, 9, 8, 5, 6}
     * 如果我们再次调用  adjustHeap 传入的是 i = 0 => 得到 {4, 9, 8, 5, 6} => {9,6,8,5, 4}
     * @param arr 待调整的数组
     * @param i 表示非叶子结点在数组中索引
     * @param length 表示对多少个元素继续调整， length 是在逐渐的减少
     */
    public static void adjustHeap(int[] arr,int i,int length) {
        int temp = arr[i];//先取出当前元素的值，保存在临时变量
        //开始调整
        //说明
        //1. k = i * 2 + 1 k 是 i结点的左子结点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//找出左右子节点中较大的值
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;//!!!i指向k,循环比较
            } else {
               //arr[k]<temp，则其子节点必小于temp
                break;
            }
        }
        //当for 循环结束后，我们已经将以i 为父结点的树的最大值，放在了 最顶(局部)
        arr[i] = temp;//将temp值放到调整后的位置
    }

}
