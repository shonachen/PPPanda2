package com.pppanda.util;

/**
 * Created by zc on 2016/12/13.
 */

public class FSTextUtil {

    public static boolean isEmptyAndNull(String content){
        if(content == null || content.trim().equals("") || content.trim().length() == 0)
            return true;
        else
            return false;
    }
}
