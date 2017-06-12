package com.pppanda.request;

/**
 * Created by Administrator on 2017/6/12.
 */

public class AddRelationRequset {
    String access_token;
    int relation_user_id;
    int relation_code;

    public AddRelationRequset(String access_token, int relation_user_id, int relation_code) {
        this.access_token = access_token;
        this.relation_user_id = relation_user_id;
        this.relation_code = relation_code;
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

    public int getRelation_code() {
        return relation_code;
    }

    public void setRelation_code(int relation_code) {
        this.relation_code = relation_code;
    }

    @Override
    public String toString() {
        return "AddRelationRequset{" +
                "access_token='" + access_token + '\'' +
                ", relation_user_id=" + relation_user_id +
                ", relation_code=" + relation_code +
                '}';
    }
}
