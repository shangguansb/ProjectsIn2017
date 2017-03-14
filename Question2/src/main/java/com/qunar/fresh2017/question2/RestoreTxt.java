package com.qunar.fresh2017.question2;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by kingsley.zhang on 2017/2/23.
 */
public class RestoreTxt {
    private LinkedList<String> keyList = new LinkedList<String>();
    private HashMap<String, String> keyMap = new HashMap<String, String>();
    private static final String OUTPUT_PATH = Question2.class.getResource("/").getFile()+"sdxl.txt";

    public static List<String> readContext(String url) {
        URL myUrl = null;
        HttpURLConnection conn = null;
        BufferedReader buff = null;
        List<String> list = new LinkedList<String>();
        try {
            myUrl = new URL(url);
            conn = (HttpURLConnection) myUrl.openConnection();
            conn.setDoInput(true);
            buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String s = "";
            while ((s = buff.readLine()) != null) {
                list.add(s);
            }
            return list;
        } catch (IOException e) {
            System.out.println("IO错误");
        } finally {
            if (buff != null) {
                try {
                    buff.close();
                } catch (IOException e) {
                    System.out.println("IO错误");
                }
            }
        }
        return list;
    }

    public void processTask3Prop(String tackpropURL ) {
        List<String> list = readContext(tackpropURL);
        for (String str : list) {
            str = str.trim();
            if (str.equals("")) continue;

            String[] s = null;
            s = str.split("\t");
            String index = s[0];
            String words = s[1];

            keyList.add(words);
            keyMap.put(index, words);
        }
    }

    public static class FunctionDesc {
        int start, end;
        String functionName;
        String index;

        public FunctionDesc(int s, int e, String name, String index) {
            this.start = s;
            this.end = e;
            this.functionName = name;
            this.index = index;
        }

        public String toString() {
            return "[start = " + start + ", end = " + end + ", functionName = " + functionName + ", index = " + index + "]";
        }
    }

    public List<FunctionDesc> processTask3Line(String line) {
        List<FunctionDesc> funtionList = new ArrayList<FunctionDesc>();
        int index = -1, prev = -1;
        while ((index = line.indexOf("$", prev + 1)) != -1) {
            int tmpEnd = line.indexOf("(", index);
            String name = line.substring(index + 1, tmpEnd);
            int end = line.indexOf(")", tmpEnd);
            String indexS = line.substring(tmpEnd + 1, end);
            funtionList.add(new FunctionDesc(index, end, name, indexS));
            prev = index;
        }
        return funtionList;
    }

    public String function(String functionName, String indexName, int i) {
        boolean desc = false;
        if (functionName.equals("natureOrder")) {
            return keyList.get(i);
        } else if (functionName.equals("indexOrder")) {
            return keyMap.get(indexName);
        } else if (functionName.equals("charOrderDESC")) {
            desc = true;
        }
        String[] items = keyList.get(i).split("");
        if (desc) {
            Arrays.sort(items);
        } else {

            Arrays.sort(items, new Comparator<String>() {

                public int compare(String o1, String o2) {
                    return -o1.compareTo(o2);
                }

            });
        }
        StringBuffer buffer = new StringBuffer();
        for (String item : items) {
            buffer.append(item);
        }
        return buffer.toString();
    }

    public void replaceShendiao(String[] Url) {

        FileWriter file = null;
        BufferedWriter buff = null;
        PrintWriter tmp = null;

        try {
            this.processTask3Prop(Url[0]);
            List<String> taskLines = readContext(Url[1]);
            StringBuffer buffer = new StringBuffer();
            int index = 0;
            for (String line : taskLines) {
                line = line.trim();
                if (line.equals("")) {
                    buffer.append("\n");
                    continue;
                }
                List<FunctionDesc> items = this.processTask3Line(line);
                int last = 0;
                StringBuffer tmpLine = new StringBuffer();
                int prev = -1;
                for (FunctionDesc fun : items) {
                    tmpLine.append(line.substring(prev + 1, fun.start));
                    tmpLine.append(function(fun.functionName, fun.index, index++));
                    prev = fun.end;
                    last = fun.end;
                }
                tmpLine.append(line.substring(last + 1));
                tmpLine.append("\n");
                buffer.append(tmpLine);
            }
            String context = buffer.toString();

            file = new FileWriter(OUTPUT_PATH);
            buff = new BufferedWriter(file);
            tmp = new PrintWriter(buff);
            tmp.println(context);

        } catch (IOException e) {
            System.out.println("IO错误");
        } finally {
            if (tmp != null) {
                tmp.close();
            }
            if (buff != null) {
                try {
                    buff.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

    }
}
