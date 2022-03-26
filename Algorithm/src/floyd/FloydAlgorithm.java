package floyd;

import java.util.Arrays;

/**
 * @ClassName FloydAlgorithm
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-26 11:06
 * @Version
 **/
public class FloydAlgorithm {
    public static void main(String[] args) {
        // 测试看看图是否创建成功
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };

        //创建 Graph 对象
        Graph graph = new Graph(vertex.length, matrix, vertex);
        //调用弗洛伊德算法
        graph.floyd();
        graph.show();
    }
}

class Graph{
    private char[] vertex;//存放顶点的数组
    private int[][] dis;//保存，从各个顶点出发到其他顶点的距离，最后的结果保留在该数组中
    private int[][] pre;//保存到目标顶点的前驱顶点

    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        //初始哈pre
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    // 显示pre数组和dis数组
    public void show() {

        //为了显示便于阅读，我们优化一下输出
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        for (int k = 0; k < dis.length; k++) {
            // 先将pre数组输出的一行
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();
            // 输出dis数组的一行数据
            for (int i = 0; i < dis.length; i++) {
                System.out.print("("+vertex[k]+"到"+vertex[i]+"的最短路径是" + dis[k][i] + ") ");
            }
            System.out.println();
            System.out.println();

        }

    }

    //floyd算法实现
    public void floyd() {
        int len = 0;//变量保存距离
        //对中间顶点进行遍历，k为中间顶点的下标
        for (int k = 0; k < dis.length; k++) {
            //选定一个中间顶点，对起点顶点进行遍历
            for (int i = 0; i < dis.length; i++) {
                //选定中间顶点和起点顶点，对重点顶点进行遍历
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j];//=> 求出从i 顶点出发，经过 k中间顶点，到达 j 顶点距离
                    if (len < dis[i][j]) {//找到更近的距离
                        dis[i][j] = len;//更新距离
                        pre[i][j] = pre[k][j];//更新前驱节点
                    }
                }
            }
        }
    }
}
