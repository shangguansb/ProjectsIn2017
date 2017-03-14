import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fangming.yi on 2017/3/6.
 */
public class Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);


    @org.junit.Test
    public void testCat() {
        Exam2 exam2 = new Exam2();
        String command = "cat C:\\Users\\kingsley.zhang\\Documents\\training1\\orderedmsg\\src\\main\\java\\SortCount.java";
        exam2.inputCommand(command);
    }

    @org.junit.Test
    public void testCatGrep() {
        Exam2 exam2 = new Exam2();
        String command = "cat C:\\Users\\kingsley.zhang\\Documents\\training1\\orderedmsg\\src\\main\\java\\SortCount.java | grep b";
        exam2.inputCommand(command);
    }

    @org.junit.Test
    public void testCatGrepWc() {
        Exam2 exam2 = new Exam2();
        String command = "cat C:\\Users\\kingsley.zhang\\Documents\\training1\\orderedmsg\\src\\main\\java\\SortCount.java | grep b | wc -l";
        exam2.inputCommand(command);
    }

    @org.junit.Test
    public void testGrepWc() {
        Exam2 exam2 = new Exam2();
        String command = "grep b C:\\Users\\kingsley.zhang\\Documents\\training1\\orderedmsg\\src\\main\\java\\SortCount.java | wc -l";
        exam2.inputCommand(command);
    }

    @org.junit.Test
    public void testCatWc() {
        Exam2 exam2 = new Exam2();
        String command = "cat C:\\Users\\kingsley.zhang\\Documents\\training1\\orderedmsg\\src\\main\\java\\SortCount.java | wc -l";
        exam2.inputCommand(command);
    }
}
