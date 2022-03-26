package dynamic;

/**
 * 0，1背包问题
 * @ClassName KnapsackProblem
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-23 20:49
 * @Version
 **/
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] weights = {1, 4, 3};//物品的重量
        int[] values = {1500, 3000, 2000};//物品的价值
        int m = 4;//背包的容量
        int n = values.length;//物品的个数


        //创建二维数组
        //v[i][j]表示前i个物品在容量为j的背包中放入的最大值，多出一行一列置为0，表示0个物品和0容量
        int[][] v = new int[n + 1][m + 1];
        //为了记录放入物品的情况
        int[][] path=new int[n + 1][m + 1];

        //初始化第一行和第一列
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;//第一列置为0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;//第一行置为0
        }

        //根据公式来处理动态规划
        //（1）v[i][0]=v[0][j]=0
        //（2）第i个物品的重量大于背包容量j:  weight[i]>j: v[i][j]=v[i-1][j]
        //（3）weight[i]<=j :  v[i][j]=max(v[i-1][j]，values[i]+v[i-1][j-weights[i]])
        for (int i = 1; i < v.length; i++) {//不处理第一行和第一列
            for (int j = 1; j < v[0].length; j++) {
                if (weights[i - 1] > j) {//因为i从1开始，对应的weight数组应从i-1
                    v[i][j] = v[i - 1][j];
                } else {
                    //说明:
                    //因为我们的i 从1开始的， 因此公式需要调整成
                    //v[i][j]=Math.max(v[i-1][j], val[i-1]+v[i-1][j-weights[i-1]]);
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - weights[i - 1]]);
                    //为了记录商品存放到背包的情况，我们不能直接的使用上面的公式，需要使用if-else来体现公式
                    if (v[i - 1][j] < values[i - 1] + v[i - 1][j - weights[i - 1]]) {
                        v[i][j] = values[i - 1] + v[i - 1][j - weights[i - 1]];
                        //记录物品i放入背包
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }


        //输出一下v 看看目前的情况
        for(int i =0; i < v.length;i++) {
            for(int j = 0; j < v[i].length;j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("============================");

        int i = path.length - 1;//行的最大下标
        int j = path[0].length - 1;//列的最大下标
        while (i > 0 && j > 0) {//从path的最后开始寻找,i从大到小，(物品的放入情况)
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j = j - weights[i - 1];//放入 物品i后，背包所剩容量减少
            }
            i--;
        }
    }
}
