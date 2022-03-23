package com.yzy.graph;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @ClassName Graph
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-20 21:59
 * @Version
 **/
public class Graph {
    private ArrayList<String> vertexList;//储存节点集合
    private int[][] edges;//储存图的邻接矩阵
    private int numOfEdges;//边的数目
    private boolean[] isVisited;//记录节点是否被访问过

    public static void main(String[] args) {
        //测试一把图是否创建ok
        int n = 8;  //结点的个数
        //String Vertexs[] = {"A", "B", "C", "D", "E"};
        String Vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};

        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for(String vertex: Vertexs) {
            graph.insertVertex(vertex);
        }

        //添加边
        //A-B A-C B-C B-D B-E
//		graph.insertEdge(0, 1, 1); // A-B
//		graph.insertEdge(0, 2, 1); //
//		graph.insertEdge(1, 2, 1); //
//		graph.insertEdge(1, 3, 1); //
//		graph.insertEdge(1, 4, 1); //

        //更新边的关系
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);



        //显示一把邻结矩阵
        graph.showGraph();

        //测试一把，我们的dfs遍历是否ok
        System.out.println("深度遍历");
        graph.dfs(); // A->B->C->D->E [1->2->4->8->5->3->6->7]
//		System.out.println();
        System.out.println("广度优先!");
        graph.bfs(); // A->B->C->D-E [1->2->3->4->5->6->7->8]
    }

    //构造器
    public Graph(int n) {
        edges = new int[n][n];
        vertexList=new ArrayList<String>(n);
        numOfEdges = 0;
    }

    //返回节点个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //得到边的数目
    public int getNumOfEdges() {
        return this.numOfEdges;
    }

    //返回节点i（下标）对应的数据   0-->A 1-->B
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1/v2的权值
    public int getWeight(int v1,int v2) {
        return edges[v1][v2];
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边
    public void insertEdge(int v1,int v2,int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
    /**
     * 找到第一个邻接点
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 15:42 2022/3/23
     * @param index
     * @return int 存在即返回下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //下一个邻接节点
    public int getNextNeighbor(int index,int currNeighbor) {
        for (int i = currNeighbor + 1; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }


    //深度优先遍历算法
    //第一次i是0
    public void dfs(boolean[] isVisited, int i) {
        System.out.print(getValueByIndex(i)+"->");
        isVisited[i]=true;
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);//体现深度优先
            }
            w = getNextNeighbor(i, w);
        }
    }

    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //广度优先遍历
    public void bfs(boolean[] isVisited, int i) {
        int u;//队列头节点的下标
        int w;//邻接节点
        //队列，节点访问顺序
        LinkedList queue = new LinkedList();
        //输出当前节点
        System.out.print(getValueByIndex(i)+"->");
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            //取出当前的节点
            u = (Integer)queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]){
                    System.out.print(getValueByIndex(w)+"->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);//体现广度优先
            }
        }
    }

    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                bfs(isVisited, i);
            }
        }
    }


}
