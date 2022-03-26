package kmp;

import java.util.Arrays;

/**
 * @ClassName KMPAlgorithm
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-24 10:17
 * @Version
 **/
public class KMPAlgorithm {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext("ABCDABD"); //[0, 1, 2, 0]
        System.out.println("next=" + Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index=" + index); // 15了
    }

    /**
     *
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 10:47 2022/3/24
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表
     * @return int 返回第一个匹配的位置，否则返回-1
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }


    public static int[] kmpNext(String dest) {
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串长度是1，部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i)！=dest.charAt(j)，我们需要从next[j-1]获取新的j
            //直到发现 dest.charAt(i)==dest.charAt(j)成立才退出
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if(dest.charAt(i)==dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
