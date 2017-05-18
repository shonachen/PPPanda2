package com.pppanda.request;

/**
 * Created by Administrator on 2017/5/18.
 */

public class ModifyPaswordRequest {
    String access_token;
    String old_pass;
    String new_pass;

    public ModifyPaswordRequest(String access_token, String old_pass, String new_pass) {
        this.access_token = access_token;
        this.old_pass = old_pass;
        this.new_pass = new_pass;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getOld_pass() {
        return old_pass;
    }

    public void setOld_pass(String old_pass) {
        this.old_pass = old_pass;
    }

    public String getNew_pass() {
        return new_pass;
    }

    public void setNew_pass(String new_pass) {
        this.new_pass = new_pass;
    }

    @Override
    public String toString() {
        return "ModifyPaswordRequest{" +
                "access_token='" + access_token + '\'' +
                ", old_pass='" + old_pass + '\'' +
                ", new_pass='" + new_pass + '\'' +
                '}';
    }
}
