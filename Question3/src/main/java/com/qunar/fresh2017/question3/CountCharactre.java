package com.qunar.fresh2017.question3;

import java.io.*;
import java.util.*;

/**
 * Created by kingsley.zhang on 2017/2/23.
 */
public class CountCharactre {
    Map<Character, Integer> letterDetailsCount = new TreeMap<Character, Integer>();
    Map<Character, Integer> numDetailsCount = new TreeMap<Character, Integer>();
    private int numCount = 0;
    private int letterCount = 0;
    private int chineseCount = 0;
    private int spaceCount = 0;
    private int totalCount = 0;
    private StringBuffer resulrBuffer = new StringBuffer("");
    private List<File> fileList = new LinkedList<File>();

    public void excute(String path) {
        File file = new File(path);
        getAllFiles(file);
        for (int i = 0; i < fileList.size(); i++) {
            Scanner in = null;
            try {
                in = new Scanner(fileList.get(i));
                String line = "";
                while (in.hasNext()) {
                    line = in.nextLine();
                    totalCount++;
                    for (int j = 0; j < line.length(); j++) {
                        char c = line.charAt(j);
                        if (('0' <= c) && (c <= '9')) {
                            numCount++;
                            if (numDetailsCount.containsKey(c)) {
                                int count = numDetailsCount.get(c);
                                numDetailsCount.put(c, count + 1);
                            } else {
                                numDetailsCount.put(c, 1);
                            }
                        } else if (('a' <= c && c <= 'z') || 'A' <= c && c <= 'Z') {
                            letterCount++;
                            if (letterDetailsCount.containsKey(c)) {
                                int count = letterDetailsCount.get(c);
                                letterDetailsCount.put(c, count + 1);
                            } else {
                                letterDetailsCount.put(c, 1);
                            }
                        } else if (c == ' ') {
                            spaceCount++;
                        } else if (c >= 0x0391 && c <= 0xFFE5) {
                            chineseCount++;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("请输入正确的路径");
            } finally {
                in.close();
            }
        }
        resulrBuffer.append("数字：" + numCount + "个\n" + "字母：" + letterCount
                + "个\n" + "汉字：" + chineseCount + "个\n" + "空格：" + spaceCount
                + "个\n" + "行数：" + totalCount + "行\n\n");
        Set<Map.Entry<Character, Integer>> numEntrySet = numDetailsCount.entrySet();
        for (Map.Entry<Character, Integer> entry : numEntrySet) {
            resulrBuffer.append("数字" + entry.getKey()
                    + ":" + entry.getValue() + "个\n");
        }
        resulrBuffer.append("\n");
        Set<Map.Entry<Character, Integer>> letterEntrySet = letterDetailsCount.entrySet();
        for (Map.Entry<Character, Integer> entry : letterEntrySet) {
            resulrBuffer.append("字母" + entry.getKey()
                    + ":" + entry.getValue() + "个\n");
        }
        String outPath = Question3.class.getResource("/").getPath() + "characterType.txt";
        File outFile = new File(outPath);
        BufferedWriter out = null;
        if (!outFile.exists()) {
            try {
                outFile.createNewFile();
                out = new BufferedWriter(new FileWriter(outFile));
                out.write(resulrBuffer.toString());
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void getAllFiles(File file) {

        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                getAllFiles(files[i]);
            } else {
                fileList.add(files[i]);
            }
        }
    }

}
