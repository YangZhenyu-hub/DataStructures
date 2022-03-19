package com.yzy.tree;

/**
 * @ClassName BinaryTree
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-18 22:30
 * @Version
 **/
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的结点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        //测试
//		System.out.println("前序遍历"); // 1,2,3,5,4
//		binaryTree.preOrder();

        //测试
//		System.out.println("中序遍历");
//		binaryTree.infixOrder(); // 2,1,5,3,4
//
//		System.out.println("后序遍历");
//		binaryTree.postOrder(); // 2,5,4,3,1

        //前序遍历
        //前序遍历的次数 ：4
//		System.out.println("前序遍历方式~~~");
//		HeroNode resNode = binaryTree.preOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("没有找到 no = %d 的英雄", 5);
//		}

        //中序遍历查找
        //中序遍历3次
//		System.out.println("中序遍历方式~~~");
//		HeroNode resNode = binaryTree.infixOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("没有找到 no = %d 的英雄", 5);
//		}

        //后序遍历查找
        //后序遍历查找的次数  2次
//		System.out.println("后序遍历方式~~~");
//		HeroNode resNode = binaryTree.postOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("没有找到 no = %d 的英雄", 5);
//		}

        //测试一把删除结点

//        System.out.println("删除前,前序遍历");
//        binaryTree.preOrder(); //  1,2,3,5,4
//        binaryTree.delNode(5);
//        //binaryTree.delNode(3);
//        System.out.println("删除后，前序遍历");
//        binaryTree.preOrder(); // 1,2,3,4
    }
}

//创建二叉树
class BinaryTree{
    //定义根节点
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void delNode(int no) {
        if (root == null) {
            System.out.println("空树，不能删除");
        } else {
            if (root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        }
    }

    //前序遍历
    public void preOrder() {
        if (this.root == null) {
            System.out.println("二叉树为空");
        } else {
            this.root.preOrder();
        }
    }
    //中序遍历
    public void infixOrder() {
        if (this.root == null) {
            System.out.println("二叉树为空");
        } else {
            this.root.infixOrder();
        }
    }
    //后序遍历
    public void postOrder() {
        if (this.root == null) {
            System.out.println("二叉树为空");
        } else {
            this.root.postOrder();
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (this.root == null) {
            return null;
        } else {
            return this.root.preOrderSearch(no);
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (this.root == null) {
            return null;
        } else {
            return this.root.infixOrderSearch(no);
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (this.root == null) {
            return null;
        } else {
            return this.root.postOrderSearch(no);
        }
    }

}


//树节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }


    //前序遍历  父、左、右
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历  左、父、右
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (right != null) {
            this.right.infixOrder();
        }
    }
    //后序遍历  左、右、父
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        System.out.println("前序遍历一次");
        if (this.no == no) {
            return this;
        }
        //1.则判断当前结点的左子节点是否为空，如果不为空，则递归前序查找
        //2.如果左递归前序查找，找到结点，则返回
        HeroNode resNode=null;
        if (this.left!=null) {
            resNode=this.left.preOrderSearch(no);
        }
        //若左递归找到节点，则返回 (不可省略，若左递归找到后，不返回则进入右递归，resNode会置为Null)
        if (resNode != null) {
            return resNode;
        }

        //1.左递归前序查找，找到结点，则返回，否继续判断，
        //2.当前的结点的右子节点是否为空，如果不空，则继续向右递归前序查找
        if (this.right != null) {
            resNode=this.right.preOrderSearch(no);
        }
        return resNode;

    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        //左递归
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("中序查找一次");
        if (this.no == no) {
            return this;
        }

        if (this.right != null) {
            resNode=this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode=null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("后序遍历一次");
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

    //删除节点
    //1.是叶子节点则删除该节点
    //2.如果是非叶子节点，则删除该子树
    public void delNode(int no) {
        //思路
		/*
		 * 	1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除结点.
			2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
			3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
			4. 如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
			5.  如果第4步也没有删除结点，则应当向右子树进行递归删除.

		 */
        //2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        //3.如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
        if (this.right != null && this.right.no == no) {
            this.right=null;
            return;
        }

        //4.左子树递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }

        //5.右子树递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }


}
