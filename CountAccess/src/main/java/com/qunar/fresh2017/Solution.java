package com.qunar.fresh2017;

import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.sun.org.apache.bcel.internal.generic.DADD;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by kingsley.zhang on 2017/2/28.
 */
public class Solution {
    List<Map.Entry<String, Long>> requestList = new ArrayList<Map.Entry<String, Long>>();
    Multiset requestMulSet = HashMultiset.create();
    Multimap<String, String> AaaMultimap = HashMultimap.create();
    HashMap<String, Long> requestMap = new HashMap<String, Long>();
    Set<String> requestSet = null;

    long countLine = 0;
    long countGET = 0;
    long countPOST = 0;
    long countLine1 = 0;

    public void getAnswer() {
        Scanner in = null;
        try {
            in = new Scanner(new File(Solution.class
                    .getResource("/access.log").getPath()));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (in.hasNext()) {
            String line = "";
            if ((line = in.nextLine().trim()) != "") {
                String s = line.substring(0, line.indexOf(' '));
                if (s.equals("GET")) {
                    countGET++;
                } else if (s.equals("POST")) {
                    countPOST++;
                }
                line = line.substring(line.indexOf(' '), line.indexOf('?') > 0 ?
                        line.indexOf('?') : line.length());
                requestMulSet.add(line);
                countLine++;
            }
        }
        requestSet = requestMulSet.elementSet();
        Iterator<String> it1 = requestSet.iterator();
        while (it1.hasNext()) {
            String temp = it1.next();
            requestMap.put(temp, (long) requestMulSet.count(temp));
        }
        Iterator<String> it2 = requestMulSet.iterator();
        while (it2.hasNext()) {
            String temp = it2.next();
            Iterable<String> result = Splitter.on('/').split(temp);
            List list = new ArrayList();
            for (String s : result) {
                list.add(s);
            }
            if (!AaaMultimap.containsEntry(list.get(1).toString(), temp)) {
                AaaMultimap.put(list.get(1).toString(), temp);
            }
        }
        requestList.addAll(requestMap.entrySet());
        Comparator<Map.Entry<String, Long>> entryComparator =
                Ordering.from(new Comparator<Map.Entry<String, Long>>() {
                    public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                        return o1.getValue() < o2.getValue() ?
                                1 : o1.getValue() == o2.getValue() ? 0 : -1;
                    }
                });
        Collections.sort(requestList, entryComparator);
        System.out.println("请求总量:" + countLine + "\n\n" + "请求量最大的10个接口:");
        for (int i = 0; i < 10; i++) {
            System.out.println(requestList.get(i).getKey() + ":" + requestList.get(i).getValue());
        }
        System.out.println("\nGET:" + countGET + "\n" + "POST:" + countPOST);
        for (String keys : AaaMultimap.keys().elementSet()) {
            Collection keysCollection = AaaMultimap.get(keys);
            System.out.println("\n/" + keys + "/:");
            Iterator values = keysCollection.iterator();
            while (values.hasNext()) {
                countLine1++;
                System.out.println(values.next());
            }
        }
    }
}
