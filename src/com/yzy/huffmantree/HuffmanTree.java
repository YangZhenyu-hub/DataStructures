package com.yzy.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName HuffmanTress
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-19 22:05
 * @Version
 **/
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
    }

    public static void preOrder(Node root) {
        if (root == null) {
            System.out.println("空树");
        } else {
            root.preOrder();
        }
    }

    /**
     * 创建huffmanTree
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 23:15 2022/3/19
     * @param arr 需要创建HuffmanTree的数组
     * @return com.yzy.huffmantree.Node 创建好后HuffmanTree的root
     */
    public static Node createHuffmanTree(int[] arr) {
        // 第一步为了操作方便
        // 1. 遍历 arr 数组
        // 2. 将arr的每个元素构成成一个Node
        // 3. 将Node 放入到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {

            //对list nodes排序
            Collections.sort(nodes);

            //取出根节点权值最小的两颗二叉树
            //(1) 取出权值最小的结点（二叉树）
            Node leftNode = nodes.get(0);
            //(2) 取出权值第二小的结点（二叉树）
            Node rightNode = nodes.get(1);
            //(3)构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left=leftNode;
            parent.right=rightNode;
            //（4）移除两个节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //(5)将新生成的节点加入nodes
            nodes.add(parent);
        }
        return nodes.get(0);



    }
}

// 创建结点类
// 为了让Node 对象持续排序Collections集合排序
// 让Node 实现Comparable接口
class Node implements Comparable<Node>{
    int value;//节点权值
    Node left;//左节点
    Node right;//右节点

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }

    }


    //从小到大排序接口
    @Override
    public int compareTo(Node o) {
        return this.value-o.value;
    }
}
