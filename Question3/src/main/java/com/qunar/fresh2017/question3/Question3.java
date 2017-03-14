package com.qunar.fresh2017.question3;

import java.util.Scanner;

/**
 * Created by kingsley.zhang on 2017/2/23.
 */
public class Question3 {
    public static void main(String args[]) {
        CountCharactre countCharactre = new CountCharactre();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入路径");
        String path = sc.nextLine();
        sc.close();
        countCharactre.excute(path);
    }
}
