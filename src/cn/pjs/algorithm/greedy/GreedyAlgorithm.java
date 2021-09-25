package cn.pjs.algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        HashSet<String> allCity = new HashSet<>();

        HashSet<String> set1 = new HashSet<>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");
        HashSet<String> set2 = new HashSet<>();
        set2.add("广州");
        set2.add("北京");
        set2.add("深圳");
        HashSet<String> set3 = new HashSet<>();
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");
        HashSet<String> set4 = new HashSet<>();
        set4.add("上海");
        set4.add("天津");
        HashSet<String> set5 = new HashSet<>();
        set5.add("杭州");
        set5.add("大连");
        Map<String, HashSet<String>> channel = new HashMap<>();
        channel.put("k1", set1);
        channel.put("k2", set2);
        channel.put("k3", set3);
        channel.put("k4", set4);
        channel.put("k5", set5);
        for (Map.Entry<String, HashSet<String>> map : channel.entrySet()) {
            allCity.addAll(map.getValue());
        }
        ArrayList<String> result = greedy(allCity, channel);
        System.out.println(result);
    }

    public static ArrayList<String> greedy(HashSet<String> allCity, Map<String, HashSet<String>> channel) {
        int max = 0;
        String maxKey = null;
        HashSet<String> tempSet = new HashSet<>();
        ArrayList<String> result = new ArrayList<String>();
        while (!allCity.isEmpty()) {
            max = 0;
            maxKey = null;
            for (Map.Entry<String, HashSet<String>> map : channel.entrySet()) {
                tempSet = map.getValue();
                tempSet.retainAll(allCity);
                if (tempSet.size() > 0 && (max == 0 || tempSet.size() > max)) {
                    max = tempSet.size();
                    maxKey = map.getKey();
                }
            }
            //写for循环外面 找到这次循环中最长符合条件的集合
            if (maxKey != null) {
                result.add(maxKey);
                allCity.removeAll(channel.get(maxKey));
                channel.remove(maxKey);
            }
        }
        return result;
    }
}
