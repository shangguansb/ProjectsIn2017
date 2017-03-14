import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Created by kingsley.zhang on 2017/3/6.
 */
public class Solution {
    static Logger logger = LoggerFactory.getLogger(Exam2.class.getName());

    public static String excuteCat(String s) {
        return s;
    }

    public static String excuteGrep(String s, String key) {
        StringBuffer tempStringBuffer = new StringBuffer();
        Scanner sc = new Scanner(s);
        String line = "";
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            if (line.contains(key)) {
                tempStringBuffer.append(line + '\n');
            }
        }
        sc.close();
        return tempStringBuffer.toString();
    }

    public static int excuteWc(String s) {
        int count = 0;
        Scanner sc = new Scanner(s);
        while (sc.hasNextLine()) {
            count++;
            sc.nextLine();
        }
        sc.close();
        return count;
    }

    public static String readFile(String path) {
        Scanner in = null;
        StringBuffer fileString = new StringBuffer();
        File file = new File(path);
        try {
            in = new Scanner(file);
            String line = "";
            while (in.hasNextLine()) {
                line = in.nextLine();
                fileString.append(line + '\n');
            }
        } catch (FileNotFoundException e) {
            logger.warn("文件不存在！");
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return fileString.toString();
    }
}
