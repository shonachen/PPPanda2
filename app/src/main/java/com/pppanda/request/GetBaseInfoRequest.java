package com.pppanda.request;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/8.
 */

public class GetBaseInfoRequest {
    String access_token;
    ArrayList<Integer> user_ids;

    public GetBaseInfoRequest(String access_token, ArrayList<Integer> user_ids) {
        this.access_token = access_token;
        this.user_ids = user_ids;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public ArrayList<Integer> getUser_ids() {
        return user_ids;
    }

    public void setUser_ids(ArrayList<Integer> user_ids) {
        this.user_ids = user_ids;
    }

    @Override
    public String toString() {
        return "GetuserinfoRequest{" +
                "access_token='" + access_token + '\'' +
                ", user_ids=" + user_ids +
                '}';
    }
}
