package prim;

import java.util.Arrays;

/**
 * @ClassName PrimAlgorithm
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-25 10:28
 * @Version
 **/
public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试看看图是否创建ok
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int [][]weight=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},};

        //创建MGraph对象
        MGraph graph = new MGraph(verxs);
        //创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight);
        //输出
        minTree.showGraph(graph);
        //测试普利姆算法
        minTree.prim(graph, 1);//
    }
}

//最小生成树
class MinTree {
    /**
     * 创建图的邻接矩阵
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 10:33 2022/3/25
     * @param graph
     * @param verxs
     * @param data
     * @param weights
     * @return void
     */
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weights) {
        int i, j;
        for (i = 0; i <verxs ; i++) {
            graph.data[i] = data[i];
            for (j = 0; j <verxs ; j++) {
                graph.weights[i][j]=weights[i][j];
            }
        }
    }

    /**
     *
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 10:37 2022/3/25
     * @param graph
     * @return void
     */
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weights) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * prim算法
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 10:39 2022/3/25
     * @param graph 图
     * @param v 表示从图的第几个顶点开始生成'A'->0 'B'->1...
     * @return void
     */
    public void prim(MGraph graph, int v) {
        //标记是否被访问过
        int isVisited[] = new int[graph.verxs];
        //visited[] 默认元素的值都是0, 表示没有访问过
		for(int i =0; i <graph.verxs; i++) {
			isVisited[i] = 0;
		}
        isVisited[v] = 1;
        //记录两个顶点的下标
        int h1 = -1, h2 = -1;
        int minWeight = 10000;
        for (int k = 1; k < graph.verxs ; k++) {//prim算法对于n个节点，产生n-1条边
            for (int i = 0; i < graph.verxs; i++) {//i表示已经访问过的节点
                for (int j = 0; j < graph.verxs; j++) {//j表示还未访问的节点
                    if (isVisited[i] == 1 && isVisited[j] == 0 && graph.weights[i][j] < minWeight) {
                        h1 = i;
                        h2 = j;
                        minWeight=graph.weights[i][j];
                    }
                }
            }
            //找到一条边是最小
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);
            //将h2代表的节点设置为已访问
            isVisited[h2] = 1;
            //minWeight重新置为大值
            minWeight = 10000;
        }
    }

}

class MGraph{
    int verxs;//表示图的节点个数
    char[] data;//存放节点数据
    int[][] weights;//存放邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data=new char[verxs];
        weights = new int[verxs][verxs];
    }
}
