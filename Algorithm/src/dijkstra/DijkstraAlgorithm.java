package dijkstra;

import java.util.Arrays;

/**
 * @ClassName DijkstraAlgorithm
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-25 21:44
 * @Version
 **/
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        //创建 Graph对象
        Graph graph = new Graph(vertex, matrix);
        //测试, 看看图的邻接矩阵是否ok
        graph.showGraph();
        graph.dijkstra(6);

    }
}

class Graph{
    private char[] vertex;//顶点数组
    private int[][] matrix;//邻接矩阵
    private VisitedVertex vv;//已经访问的顶点集合

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void showDijkstra() {
        vv.show();
    }

    /**
     * dijkstra算法的实现
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 22:06 2022/3/25
     * @param index 出发顶点的下标
     * @return void
     */
    public void dijkstra(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);
        for (int i = 1; i < vertex.length; i++) {
            index = vv.updateArr();//选择并返回新的访问顶点
            update(index);
        }
    }

    /**
     * 更新index顶到到周围顶点的距离和周围顶点的前驱顶点
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 22:08 2022/3/25
     * @param index
     * @return void
     */
    public void update(int index) {
        int len = 0;
        //遍历邻接矩阵的matrix[index]行
        for (int i = 0; i < matrix[index].length; i++) {
            //len：出发顶点到index顶点的距离+index顶点到i顶点的距离
            len = vv.getDis(index) + matrix[index][i];
            //如果i顶点未被访问过，且len距离小于出发顶点到i顶点的距离
            if (!vv.in(i) && len < vv.getDis(i)) {
                vv.updateDis(i, len);//更新出发顶点到i的距离
                vv.updatePre(i, index);//更新i顶点的前驱为index
            }
        }
    }


}

//已访问顶点的集合
class VisitedVertex{
    // 记录各个顶点是否访问过 1表示访问过,0未访问,会动态更新
    public int[] already_arr;
    // 每个下标对应的值为前一个顶点下标, 会动态更新
    public int[] pre_visited;
    // 记录出发顶点到其他所有顶点的距离,比如G为出发顶点，就会记录G到其它顶点的距离，会动态更新，求的最短距离就会存放到dis
    public int[] dis;

    /**
     * 构造器
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 21:52 2022/3/25
     * @param length 顶点的个数
     * @param index 出发顶点的下标
     * @return
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis数组
        Arrays.fill(dis, 65536);
        //设置出发顶点被访问
        this.already_arr[index] = 1;
        //设置出发顶点的访问距离为0
        this.dis[index] = 0;
    }

    /**
     * 判断index顶点是否被访问过
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 21:56 2022/3/25
     * @param index
     * @return boolean
     */
    public boolean in(int index) {
        return already_arr[index]==1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 21:57 2022/3/25
     * @param index
     * @param len
     * @return void
     */
    public void updateDis(int index,int len) {
        dis[index] = len;
    }

    /**
     * 更新pre这个顶点的前驱顶点为index
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 21:58 2022/3/25
     * @param pre
     * @param index
     * @return void
     */
    public void updatePre(int pre,int index) {
        pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到index节点的距离
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 21:59 2022/3/25
     * @param index
     * @return int
     */
    public int getDis(int index) {
        return dis[index];
    }

    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {//该顶点未被访问，且距离最短
                min = dis[i];
                index = i;
            }
        }
        already_arr[index] = 1;
        return index;
    }

    //显示最后的结果
    //即将三个数组的情况输出
    public void show() {

        System.out.println("==========================");
        //输出already_arr
        for(int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        //输出pre_visited
        for(int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        //输出dis
        for(int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();
        //为了好看最后的最短距离，我们处理
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "("+i+") ");
            } else {
                System.out.println("N ");
            }
            count++;
        }
        System.out.println();

    }

}
