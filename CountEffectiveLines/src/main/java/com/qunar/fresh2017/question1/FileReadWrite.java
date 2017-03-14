package com.qunar.fresh2017.question1;

import java.io.*;
import java.util.Scanner;

/**
 * Created by kingsley.zhang on 2017/2/20.
 */
public class FileReadWrite {
    public String ReadFile() {
        String str = "";
        Scanner in = null;
        try {
            in = new Scanner(new File(Question1.class.getResource("/StringUtils.txt").getPath()));
            while (in.hasNext()) {
                str = str + in.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return str;
    }


    public void WriteFile(String str) {
        String path = FileReadWrite.class.getResource("/").getPath() + "validLineCount.txt";
        File ChatFile = new File(path); // 相对路径
        BufferedWriter out = null;
        if (!ChatFile.exists()) {
            try {
                ChatFile.createNewFile();
                out = new BufferedWriter(new FileWriter(ChatFile));
                out.write(str);
                out.flush(); // 把缓存区内容压入文件
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close(); //关闭文件
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
