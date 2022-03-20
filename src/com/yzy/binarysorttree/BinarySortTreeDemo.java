package com.yzy.binarysorttree;

/**
 * @ClassName BinarySortTreeDemo
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-20 11:02
 * @Version
 **/
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int arr[] = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树~");
        binarySortTree.infixOrder(); // 1, 3, 5, 7, 9, 10, 12

        //测试一下删除叶子结点


        binarySortTree.delNode(12);


        binarySortTree.delNode(5);
        binarySortTree.delNode(10);
        binarySortTree.delNode(2);
        binarySortTree.delNode(3);

        binarySortTree.delNode(9);
        binarySortTree.delNode(1);
        binarySortTree.delNode(7);


        System.out.println("root=" + binarySortTree.getRoot());


        System.out.println("删除结点后");
        binarySortTree.infixOrder();
    }
}

class BinarySortTree {
    private Node root;

    public Node getRoot() {
        return this.root;
    }

    public void add(Node node) {
        if (this.root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("树为空");
        } else {
            root.infixOrder();
        }
    }

    public void delNode(int value) {
        if (root == null) {
            System.out.println("空树，删除失败");
            return;
        }
        Node targetNode = search(value);
        if (targetNode == null) {
            System.out.println("未找到该节点，删除失败");
            return;
        }
        Node parentNode = searchParent(value);
        //1、叶子节点
        if (targetNode.leftNode==null&&targetNode.rightNode==null) {
            if (parentNode.leftNode == targetNode) {
                parentNode.leftNode = null;
            } else if (parentNode.rightNode == targetNode) {
                parentNode.rightNode = null;
            }
        } else if (targetNode.leftNode != null && targetNode.rightNode != null) {
            //2、删除有两颗子树的节点
            //思路：找到右子树中最小的值并删除，替换删除的节点||找到左子树中最大的值，替换删除的节点。
            int min = rightTreeMin(targetNode.rightNode);
            targetNode.value = min;
        } else {//只有一颗子树的节点
            if (targetNode.leftNode != null) {//只有左节点
                if (parentNode != null) {
                    if (parentNode.leftNode == targetNode) {//target是parent的左节点
                        parentNode.leftNode = targetNode.leftNode;
                    } else {//target是parent的右节点
                        parentNode.leftNode = targetNode.leftNode;
                    }
                } else {//根节点
                    root = targetNode.leftNode;
                }
            } else {//只有右节点
                if (parentNode != null) {
                    if (parentNode.leftNode == targetNode) {
                        parentNode.leftNode = targetNode.rightNode;
                    } else {
                        parentNode.rightNode = targetNode.rightNode;
                    }
                } else {
                    root=targetNode.rightNode;
                }
            }

        }


    }

    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    public int rightTreeMin(Node node) {
        Node target = node;
        //最小值在左边
        while (target.leftNode != null) {
            target = target.leftNode;
        }
        //删除该节点
        delNode(target.value);
        return target.value;
    }
}
class Node{
    int value;
    Node leftNode;
    Node rightNode;

    public Node(int value) {
        this.value = value;
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.leftNode == null) {
                this.leftNode = node;
            } else {
                this.leftNode.add(node);
            }
        } else {
            if (this.rightNode == null) {
                this.rightNode = node;
            } else {
                this.rightNode.add(node);
            }
        }
//        if (this.leftNode == null && this.value > node.value) {
//            this.leftNode=node;
//            return;
//        }
//        if (this.rightNode == null && this.value < node.value) {
//            this.rightNode=node;
//            return;
//        }
//        if (this.value > node.value && this.leftNode != null) {
//            this.leftNode.add(node);
//        }
//        if (this.value < node.value && this.rightNode != null) {
//            this.rightNode.add(node);
//        }

    }

    public void infixOrder() {
        if (this.leftNode != null) {
            this.leftNode.infixOrder();
        }
        System.out.println(this);
        if (this.rightNode != null) {
            this.rightNode.infixOrder();
        }
    }

    public Node search(int value) {
        if (this.value == value) {
            return this;
        }
        if (value >= this.value && this.rightNode != null) {
            return this.rightNode.search(value);
        }
        if (value < this.value && this.leftNode != null) {
            return this.leftNode.search(value);
        }
        return null;
    }

    public Node searchParent(int value) {
        if ((this.leftNode != null && this.rightNode.value == value) || (this.rightNode != null && this.rightNode.value == value)) {
            return this;
        } else {
            if (value < this.value && this.leftNode != null) {
                return this.leftNode.searchParent(value);
            } else if (value >= this.value && this.rightNode != null) {
                return this.rightNode.searchParent(value);
            } else {
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}