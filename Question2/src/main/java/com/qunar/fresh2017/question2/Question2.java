package com.qunar.fresh2017.question2;

/**
 * Created by kingsley.zhang on 2017/2/23.
 */
public class Question2 {
    private static final String TACKPROP_URL  = "https://owncloud.corp.qunar.com/index.php/s/2pvS0d2Zs5onsF2/download";
    private static final String  TASK3_URL = "https://owncloud.corp.qunar.com/index.php/s/2mElvSWUJgppSBx/download";

    public static void main(String[] args){
        String[] URL=new String[2];
        URL[0]=TACKPROP_URL;
        URL[1]=TASK3_URL;
        RestoreTxt returnedTxt = new RestoreTxt();
        returnedTxt.replaceShendiao(URL);
    }


}
