import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kingsley.zhang on 2017/2/20.
 */
public class SortCount {
    public static ResultEntry sortchat(String ChatLog) {
        List<String> list = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new StringReader(ChatLog));
        String line = "";
        String result = "";
        String result2 = "";
        ResultEntry mResult = new ResultEntry();
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            line = reader.readLine();
            while (line != null) {
                String tempstr = line.substring((line.indexOf("    ") + 4), (line.indexOf("    ") + 23));
                list.add(tempstr + line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            result = result + (list.get(i)).substring(19) + "\n";
            mResult.setChatLog(result);
            if (list.get(i).indexOf("(") > 0) {
                String temps = list.get(i).substring(19, list.get(i).indexOf("("));
                if (map.get(temps) != null) {
                    map.put(temps, map.get(temps) + 1);
                } else {
                    map.put(temps, 1);
                }
            } else {
                String temps = list.get(i).substring(19, list.get(i).indexOf("<"));
                if (map.get(temps) != null) {
                    map.put(temps, map.get(temps) + 1);
                } else {
                    map.put(temps, 1);
                }
            }

        }
        for (String key : map.keySet()) {
            result2 = result2 + key + "    " + map.get(key) + "\n";

        }
        mResult.setCount(result2);
        return mResult;
    }
}
