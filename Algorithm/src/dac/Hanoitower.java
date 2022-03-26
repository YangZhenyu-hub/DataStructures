package dac;

/**
 * @ClassName Hanoitower
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-23 19:48
 * @Version
 **/
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(10, 'A', 'B', 'C');
    }

    /**
     *
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 20:09 2022/3/23
     * @param num 盘子的数量
     * @param a 第一根柱子
     * @param b 第二根柱子
     * @param c 第三根柱子
     * @return void
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第" + num + "盘子从" + a + "->" + c);
        } else {
            //如果我们有 n >= 2 情况，我们总是可以看做是两个盘 1.最下边的一个盘 2. 上面的所有盘
            //1. 先把 最上面的所有盘 A->B， 移动过程会使用到 c
            hanoiTower(num - 1, a, c, b);
            //2. 把最下边的盘 A->C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            //3. 把B塔的所有盘 从 B->C , 移动过程使用到 a塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}
