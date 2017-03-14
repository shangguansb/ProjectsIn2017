import com.google.common.base.Splitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by kingsley.zhang on 2017/3/6.
 */
public class Exam2 {
    String currentString = "";
    int count = 0;
    Logger logger = LoggerFactory.getLogger(Exam2.class.getName());

    public static void main(String srgs[]) {
        Scanner sc = new Scanner(System.in);
        String excute = sc.nextLine();
        sc.close();
        Exam2 exam2 = new Exam2();
        exam2.inputCommand(excute);
    }

    public void inputCommand(String string) {
        List<String> exList = new ArrayList<String>();
        for (String s : Splitter.on(' ').omitEmptyStrings().split(string)) {
            exList.add(s);
        }
        for (int i = 0; i < exList.size(); i++) {
            if (exList.get(i).equals("cat")) {
                currentString = Solution.excuteCat(Solution.readFile(exList.get(i + 1)));
                i = i++;
                continue;
            } else if (exList.get(i).equals("grep")) {
                if (!currentString.equals("")) {
                    currentString = Solution.excuteGrep(currentString, exList.get(i + 1));
                } else {
                    currentString = Solution.excuteGrep(Solution.readFile(exList.get(i + 2)), exList.get(i + 1));
                }
                i = i++;
                continue;
            } else if (exList.get(i).equals("wc")) {
                if (!currentString.equals("")) {
                    count = Solution.excuteWc(currentString);
                } else {
                    count = Solution.excuteWc(Solution.readFile(exList.get(i + 2)));
                }
                logger.info(count + "");
                i++;
                continue;
            } else if ((i == exList.size() - 1) && !currentString.equals("")) {
                logger.info(currentString);
            }
        }
    }
}
