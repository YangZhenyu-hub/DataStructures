package kruskal;

import java.util.Arrays;

/**
 * @ClassName KruskalCase
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-25 15:02
 * @Version
 **/
public class KruskalCase {
    private int edgeNum;//边的个数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    //使用INF表示两个顶点不能联通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};
        //大家可以在去测试其它的邻接矩阵，结果都可以得到最小生成树.
    }

    //构造器
    public KruskalCase(char[] vertexs, int[][] matrix) {
        //初始化顶点和边
        //因传入的是数组，即地址值，在不破坏源数据的基础上，使用复制拷贝的方式
        //顶点个数
        int vlen = vertexs.length;
        //初始化顶点
        this.vertexs = new char[vlen];
        for (int i = 0; i < vlen; i++) {
            this.vertexs[i] =vertexs[i];
        }

        //初始化边，并统计边的个数
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j]=matrix[i][j];
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }

        }
    }

    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为: \n");
        for(int i = 0; i < vertexs.length; i++) {
            for(int j=0; j < vertexs.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();//换行
        }
    }

    /**
     * 冒泡排序
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 15:18 2022/3/25
     * @param edges 边的集合
     * @return void
     */
    private void sortEdge(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight>edges[j+1].weight) {
                    EData temp=edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     *
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 15:20 2022/3/25
     * @param ch 顶点的值，比如'A','B'
     * @return int 返回ch顶点对应的下标，如果找不到，返回-1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (ch == vertexs[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 功能: 获取图中边，放到EData[] 数组中，后面我们需要遍历该数组
     * 是通过matrix 邻接矩阵来获取
     * EData[] 形式 [['A','B', 12], ['B','F',7], .....]
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 15:26 2022/3/25
     * @param
     * @return kruskal.EData[]
     */
    private EData[] getEdges() {
        int index = 0;
        //根据边的个数，创建EData数组（边对象实例的数组）
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            //因矩形对称，只需要遍历半边
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++]=new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 功能: 获取下标为i的顶点的终点(), 用于后面判断两个顶点的终点是否相同
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 15:28 2022/3/25
     * @param ends 数组就是记录了各个顶点对应的终点是哪个,ends 数组是在遍历过程中，逐步形成
     * @param i 表示传入的顶点对应的下标
     * @return int 返回终点的下标，若第一次传入，返回自身的下标
     */
    public int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    public void kruskal() {
        int index = 0;//表示最后结果的索引
        int[] ends = new int[edgeNum];//用于保存"已有最小生成树" 中的每个顶点在最小生成树中的终点
        //创建结果数组，保存最后的最小生成树
        EData[] rest = new EData[edgeNum];

        //获取图中所有边的集合
        EData[] edges = getEdges();
        System.out.println("图的边的集合=" + Arrays.toString(edges) + " 共"+ edges.length); //12

        //按照边的权值进行大小排序
        sortEdge(edges);

        //遍历edges 数组，将边添加到最小生成树中时，判断是准备加入的边否形成了回路，如果没有，就加入 rets, 否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取i条边的起点
            int p1 = getPosition(edges[i].start);
            //获取第i条边的终点
            int p2 = getPosition(edges[i].end);
            //获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1);
            //获取p2这个顶点在已有最小生成树中的终点
            int n = getEnd(ends, p2);
            if (m != n) {//没有构成回路
                ends[m] = n;
                rest[index++] = edges[i];
            }
        }
        //<E,F> <C,D> <D,E> <B,F> <E,G> <A,B>。
        //统计并打印 "最小生成树", 输出  rets
        System.out.println("最小生成树为");
        for(int i = 0; i < index; i++) {
            System.out.println(rest[i]);
        }
    }


}

//其对象实例就表示一条边
class EData{
    char start;//边的一个点
    char end;//边的另外一个点
    int weight;//边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
