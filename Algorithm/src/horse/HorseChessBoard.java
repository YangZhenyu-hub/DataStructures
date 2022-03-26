package horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @ClassName HorseChessBoard
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-27 0:00
 * @Version
 **/
public class HorseChessBoard {
    private static int X;//棋盘的行数
    private static int Y;//棋盘的列数
    //创建一个数组，标记棋盘的各个位置是否被访问
    private static boolean[] visisted;
    //使用一个属性，标记是否棋盘的所有位置都被访问过
    private static boolean finished;

    public static void main(String[] args) {
        System.out.println("骑士周游算法，开始运行~~");
        //测试骑士周游算法是否正确
        X = 8;
        Y = 8;
        int row = 1; //马儿初始位置的行，从1开始编号
        int column = 1; //马儿初始位置的列，从1开始编号
        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visisted = new boolean[X * Y];//初始值都是false
        //测试一下耗时
        long start = System.currentTimeMillis();
        travelsalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时: " + (end - start) + " 毫秒");

        //输出棋盘的最后情况
        for(int[] rows : chessboard) {
            for(int step: rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }

    }

    /**
     * 完成骑士周游问题
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 0:05 2022/3/27
     * @param chessboard 棋盘
     * @param row 马儿当前的位置的行
     * @param column 马儿当前的位置的列
     * @param step 这是第几步，初始记为1
     * @return void
     */
    public static void travelsalChessboard(int[][] chessboard,int row,int column,int step) {
        chessboard[row][column] = step;
        //标记该位置
        visisted[row * X + column] = true;
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(row, column));
        //对ps进行排序
        sort(ps);
        //遍历ps
        while (!ps.isEmpty()) {
            //取出下一个可以走的位置
            Point p = ps.remove(0);
            if (!visisted[p.x * X + p.y]) {//说明还未被访问
                travelsalChessboard(chessboard, p.x, p.y, step + 1);
            }
        }
        //判断马儿是否完成了任务，使用   step 和应该走的步数比较 ，
        //如果没有达到数量，则表示没有完成任务，将整个棋盘置0
        //说明: step < X * Y  成立的情况有两种
        //1. 棋盘到目前位置,仍然没有走完
        //2. 棋盘处于一个回溯过程
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visisted[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 根据当前位置(Point对象)，计算马儿还能走哪些位置(Point)，并放入到一个集合中(ArrayList), 最多有8个位置
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 0:09 2022/3/27
     * @param curPoint
     * @return java.util.ArrayList<java.awt.Point>
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        //表示马儿可以走5这个位置
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y -1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走6这个位置
        if((p1.x = curPoint.x - 1) >=0 && (p1.y=curPoint.y-2)>=0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走7这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走0这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走1这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走2这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走3这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走4这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    //根据当前这个一步的所有的下一步的选择位置，进行非递减排序, 减少回溯的次数
    public static void sort(ArrayList<Point> ps) {

        ps.sort(new Comparator<Point>(){

            @Override
            public int compare(Point o1, Point o2) {
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }

}
