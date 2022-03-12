package com.yzy.recursion;

/**
 * @ClassName Queue8
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-12 9:24
 * @Version
 **/
public class Queue8 {

    //定义一个max表示最多有多少个皇后
    final private int MAX=8;
    //定义数组array, 保存皇后放置位置的结果,比如 arr = {0 , 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[MAX];
    static int count;//计数
    static int judgeCount;//判断计数
    public static void main(String[] args) {
        //测试一把 ， 8皇后是否正确
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d解法", count);
        System.out.printf("一共判断冲突的次数%d次", judgeCount); // 1.5w
    }

    //编写一个方法，放置第n个皇后
    //特别注意： check 是 每一次递归时，进入到check中都有  for(int i = 0; i < max; i++)，因此会有回溯
    public void check(int n) {
        if (n == MAX) {
            print();
            return;
        }

        for (int i = 0; i < MAX; i++) {
            array[n]=i;
            if (judge(n)) {
                check(n+1);
            }
        }

    }

    //判断第n个皇后与前面放置的是否有冲突
    public boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            // 说明
            //1. array[i] == array[n]  表示判断 第n个皇后是否和前面的n-1个皇后在同一列
            //2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i皇后是否在同一斜线
            // n = 1  放置第 2列 1 n = 1 array[1] = 1
            // Math.abs(1-0) == 1  Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
            //3. 判断是否在同一行, 没有必要，n 每次都在递增
            if (array[i] == array[n] || Math.abs(i - n) == Math.abs(array[i] - array[n])) {
                return false;
            }
        }
        return true;
    }


    //写一个方法，可以将皇后摆放的位置输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
