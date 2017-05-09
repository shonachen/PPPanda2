package com.pppanda.request;

/**
 * Created by Administrator on 2017/5/9.
 */

public class GetHccDataRankRequset {
    String access_token;
    int user_ids;

    public GetHccDataRankRequset(String access_token, int user_ids) {
        this.access_token = access_token;
        this.user_ids = user_ids;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getUser_ids() {
        return user_ids;
    }

    public void setUser_ids(int user_ids) {
        this.user_ids = user_ids;
    }

    @Override
    public String toString() {
        return "GetHccDataRankRequset{" +
                "access_token='" + access_token + '\'' +
                ", user_ids=" + user_ids +
                '}';
    }
}
