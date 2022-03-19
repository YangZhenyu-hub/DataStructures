package com.yzy.hashtab;

import java.util.Scanner;

import static com.sun.deploy.uitoolkit.impl.awt.AWTClientPrintHelper.print;

/**
 * @ClassName HashTable
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-18 18:58
 * @Version
 **/
public class HashTable {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.find(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

class HashTab{
    private EmpLinkedList[] empLinkedListArr;
    int size;

    public HashTab(int size) {//初始化hash表长度
        this.size = size;
        empLinkedListArr=new EmpLinkedList[size];
    }

    public int hashFun(int id) {
        return id%size;
    }

    public void add(Emp emp) {
        int empLinkedListNo = hashFun(emp.getId());
        if (empLinkedListArr[empLinkedListNo] == null) {//当前位置为空，则初始化
            empLinkedListArr[empLinkedListNo] = new EmpLinkedList();
        }
        empLinkedListArr[empLinkedListNo].add(emp);

    }

    public void list() {
        for (int i = 0; i < size; i++) {
            if (empLinkedListArr[i] == null) {
                System.out.println("第%d链表为空");
            } else {
                empLinkedListArr[i].list();
            }
        }
    }

    public void find(int id) {
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArr[empLinkedListNo].findEmpById(id);
        if (emp == null) {
            System.out.println("并未找到该员工");
        } else {
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n", (empLinkedListNo + 1), id);
        }
    }


}

class EmpLinkedList {
    //头指针，指向当前链表的第一个员工
    private Emp head = null;

    public void add(Emp emp) {
        if (head == null) {//当前链表为空
            head = emp;
            return;
        } else {
            Emp curEmp = head;//辅助节点
            while (true) {
                if (curEmp.getNext() == null) {
                    curEmp.setNext(emp);
                    break;
                }
                curEmp = curEmp.getNext();
            }
        }
    }

    public void list() {
        if (head == null) {
            System.out.println("当前链表为空");
            return;
        } else {
            Emp curEmp = head;
            while (true) {
                if (curEmp == null) {
                    break;
                }
                System.out.printf("第%d号员工-->", curEmp.getId());
                curEmp = curEmp.getNext();
            }
            System.out.println();
        }
    }

    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("当前链表为空");
            return null;
        } else {
            Emp curEmp = head;
            while (true) {
                if (curEmp == null) {
                    return null;
                }
                if (id == curEmp.getId()) {
                    return curEmp;
                }
                curEmp = curEmp.getNext();
            }
        }
    }
}


class Emp{
    private int id;
    private String name;
    private Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Emp getNext() {
        return next;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNext(Emp next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", next=" + next +
                '}';
    }
}

