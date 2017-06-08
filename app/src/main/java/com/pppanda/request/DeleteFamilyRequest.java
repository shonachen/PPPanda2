package com.pppanda.request;

import android.app.Activity;

/**
 * Created by Administrator on 2017/6/8.
 */

public class DeleteFamilyRequest {
    String access_token;
    int relation_user_id;

    public DeleteFamilyRequest(String access_token, int relation_user_id) {
        this.access_token = access_token;
        this.relation_user_id = relation_user_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getRelation_user_id() {
        return relation_user_id;
    }

    public void setRelation_user_id(int relation_user_id) {
        this.relation_user_id = relation_user_id;
    }

    @Override
    public String toString() {
        return "DeleteFamilyRequest{" +
                "access_token='" + access_token + '\'' +
                ", relation_user_id=" + relation_user_id +
                '}';
    }
}
