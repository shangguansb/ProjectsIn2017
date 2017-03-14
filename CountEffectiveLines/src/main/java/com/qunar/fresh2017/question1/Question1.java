package com.qunar.fresh2017.question1;

/**
 * Created by kingsley.zhang on 2017/2/20.
 */
public class Question1 {

    public static void main(String args[]) {
        FileReadWrite frw = new FileReadWrite();
        frw.WriteFile((Count.getCount(frw.ReadFile().toString()))+"");
    }
}
