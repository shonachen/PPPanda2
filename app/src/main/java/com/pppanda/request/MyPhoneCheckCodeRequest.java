package com.pppanda.request;

/**
 * Created by Administrator on 2017/5/16.
 */

public class MyPhoneCheckCodeRequest {
    String access_token;
    String old_code;
    String new_code;

    public MyPhoneCheckCodeRequest(String access_token, String old_code, String new_code) {
        this.access_token = access_token;
        this.old_code = old_code;
        this.new_code = new_code;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getOld_code() {
        return old_code;
    }

    public void setOld_code(String old_code) {
        this.old_code = old_code;
    }

    public String getNew_code() {
        return new_code;
    }

    public void setNew_code(String new_code) {
        this.new_code = new_code;
    }

    @Override
    public String toString() {
        return "MyPhoneCheckCodeRequest{" +
                "access_token='" + access_token + '\'' +
                ", old_code='" + old_code + '\'' +
                ", new_code='" + new_code + '\'' +
                '}';
    }
}
