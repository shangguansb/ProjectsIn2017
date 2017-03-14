
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
            in = new Scanner(new File(Orderedmsg.class
                    .getResource("/unorderedmsg.txt").getPath()));
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


    public void WriteFile(ResultEntry resultEntry) {
        String ChatLog = resultEntry.getChatLog();
        String Count = resultEntry.getCount();
        String path1 = FileReadWrite.class.getResource("/").getPath() + "orderedmsg.txt";
        String path2 = FileReadWrite.class.getResource("/").getPath() + "count.txt";
        File ChatFile = new File(path1); // 相对路径
        if (!ChatFile.exists()) {
            try {
                ChatFile.createNewFile();
                BufferedWriter out = new BufferedWriter(new FileWriter(ChatFile));
                out.write(ChatLog);
                out.flush(); // 把缓存区内容压入文件
                out.close(); //关闭文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File CountFile = new File(path2); // 相对路径
        try {
            if (!CountFile.exists()) {
                CountFile.createNewFile();
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(CountFile));
            out.write(Count);
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后关闭文件
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
