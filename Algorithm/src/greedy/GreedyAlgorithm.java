package greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @ClassName GreedyAlgorithm
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-24 19:40
 * @Version
 **/
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台，放入到Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");


        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //allAreas 存放所有地区
        HashSet<String> allAreas = new HashSet<String>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建ArrayList,存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();
        //定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        //即当前电台所能覆盖的有效区域（除去已经被覆盖的区域）
        HashSet<String> tempSet = new HashSet<>();

        //定义给maxKey ， 保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的key
        //如果maxKey 不为null , 则会加入到 selects
        String maxKey = null;
        while (allAreas.size() != 0) {//如果allAreas不为空，则说明还没有覆盖所有地区
            //每次选择出一个电台后，将maxKey置为Null
            maxKey = null;
            //遍历电台，选择一个加入selects
            for (String key : broadcasts.keySet()) {
                //每次遍历时，将tempSet置为空
                tempSet.clear();
                //得到每个电台所能覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                //将areas放入tempSet
                tempSet.addAll(areas);
                //求出tempSet 和   allAreas 集合的交集, 交集会赋给 tempSet
                tempSet.retainAll(allAreas);

                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区还多
                //就需要重置maxKey
                // tempSet.size() >broadcasts.get(maxKey).size()) 体现出贪心算法的特点,每次都选择最优的
                if (tempSet.size() > 0 && tempSet.size() > broadcasts.get(maxKey).size()) {
                    maxKey = key;
                }
                if (maxKey != null) {
                    selects.add(maxKey);
                    allAreas.removeAll(broadcasts.get(maxKey));
                }

            }
            System.out.println("得到的选择结果是" + selects);//[K1,K2,K3,K5]

        }
    }
}
