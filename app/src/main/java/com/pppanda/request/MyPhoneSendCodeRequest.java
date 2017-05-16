package com.pppanda.request;

/**
 * Created by Administrator on 2017/5/16.
 */

public class MyPhoneSendCodeRequest {
    String access_token;
    String old_phone;
    String new_phone;

    public MyPhoneSendCodeRequest(String access_token, String old_phone, String new_phone) {
        this.access_token = access_token;
        this.old_phone = old_phone;
        this.new_phone = new_phone;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getOld_phone() {
        return old_phone;
    }

    public void setOld_phone(String old_phone) {
        this.old_phone = old_phone;
    }

    public String getNew_phone() {
        return new_phone;
    }

    public void setNew_phone(String new_phone) {
        this.new_phone = new_phone;
    }

    @Override
    public String toString() {
        return "MyPhoneSendCodeRequest{" +
                "access_token='" + access_token + '\'' +
                ", old_phone='" + old_phone + '\'' +
                ", new_phone='" + new_phone + '\'' +
                '}';
    }
}
