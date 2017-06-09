package com.pppanda.request;

/**
 * Created by Administrator on 2017/6/9.
 */

public class ConfirmRelationRequest {
    String access_token;
    int relation_user_id;
    int action;

    public ConfirmRelationRequest(String access_token, int relation_user_id, int action) {
        this.access_token = access_token;
        this.relation_user_id = relation_user_id;
        this.action = action;
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

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "ConfirmRelationRequest{" +
                "access_token='" + access_token + '\'' +
                ", relation_user_id=" + relation_user_id +
                ", action=" + action +
                '}';
    }
}
