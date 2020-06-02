package cn.lmcw.becomic.comm;

import java.util.regex.Pattern;

public class Verifys {


    public static boolean testUserName(String uname) {
        String pattern = "^([A-zA-Z0-9]){5,}$";
        return Pattern.matches(pattern, uname);
    }

}
