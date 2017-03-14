package com.qunar.fresh2017.question1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * Created by kingsley.zhang on 2017/2/20.
 */
public class Count {

    public static int getCount(String str) {
        boolean annotation = false;
        int invalidLines = 0;
        int codeLines = 0;
        int i = 0;
        BufferedReader reader = new BufferedReader(new StringReader(str));
        String line = "";
        try {
            line = reader.readLine();
            while (line != null) {
                line = line.trim();
                if (line.equals("")) {// 空行
                    invalidLines++;
                    i++;
                } else if (line.startsWith("/*") && !line.endsWith("*/")) {// 多行注释开头
                    invalidLines++;
                    annotation = true;
                } else if (annotation == true && !line.endsWith("*/")) {// 多行注释
                    invalidLines++;
                } else if (annotation == true && line.endsWith("*/")) {// 多行注释结尾
                    invalidLines++;
                    annotation = false;
                } else if (line.startsWith("//")) {// 单行注释
                    invalidLines++;
                } else if (line.startsWith("*")) {// 单行注释
                    invalidLines++;
                } else {
                    codeLines++;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return codeLines;
    }
}
