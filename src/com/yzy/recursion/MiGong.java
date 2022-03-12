package com.yzy.recursion;

/**
 * @ClassName MiGong
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-11 21:46
 * @Version
 **/
public class MiGong {
    public static void main(String[] args) {
        // 先创建一个二维数组，模拟迷宫
        // 地图
        int[][] map = new int[8][7];

        // 使用1 表示墙
        // 上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i]=1;
            map[7][i]=1;
        }

        // 左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0]=1;
            map[i][6]=1;
        }

        //设置挡板, 1 表示
        map[3][1]=1;
        map[3][2]=1;

        // 输出地图
        System.out.println("地图情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+"");
            }
            System.out.println();
        }

        //使用递归回溯给小球找路
        setWay(map, 1, 1);

        //输出新的地图, 小球走过，并标识过的递归
        System.out.println("小球走过后 地图情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+"");
            }
            System.out.println();
        }
    }

    //使用递归回溯来给小球找路
    //说明
    //1. map 表示地图
    //2. i,j 表示从地图的哪个位置开始出发 (1,1)
    //3. 如果小球能到 map[6][5] 位置，则说明通路找到.
    //4. 约定： 当map[i][j] 为 0 表示该点没有走过 当为 1 表示墙  ； 2 表示通路可以走 ； 3 表示该点已经走过，但是走不通
    //5. 在走迷宫时，需要确定一个策略(方法) 下->右->上->左 , 如果该点走不通，再回溯
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//路已经走通
            return true;
        } else {
            if (map[i][j] == 0) {//当前点未走过
                //按照策略 下->右->上->左  走
                map[i][j]=2;//假设该点能走通
                if (setWay(map, i + 1, j)) {
                    return true;
                }else if (setWay(map, i , j+1)) {
                    return true;
                }else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    //该点走不通
                    map[i][j]=3;
                    return false;
                }
            } else {//如果该点不为0，即为1，2，3
                return false;
            }
        }
    }
}
