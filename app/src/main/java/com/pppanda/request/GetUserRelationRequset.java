package com.pppanda.request;

/**
 * Created by Administrator on 2017/6/1.
 */

public class GetUserRelationRequset {
    String access_token;

    public GetUserRelationRequset(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Override
    public String toString() {
        return "GetUserRelationRequset{" +
                "access_token='" + access_token + '\'' +
                '}';
    }
}
