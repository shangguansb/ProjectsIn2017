import java.io.*;
import java.util.Scanner;

/**
 * Created by kingsley.zhang on 2017/2/20.
 */
public class Orderedmsg {

    public static void main(String args[]) {
        FileReadWrite frw = new FileReadWrite();
        ResultEntry res = SortCount.sortchat(frw.ReadFile());
        frw.WriteFile(res);
    }
}
