package com.pppanda.request;

/**
 * Created by Administrator on 2017/5/4.
 */

public class UserIdRequest {
    String client_id;
    int req_time;
    String sign_key;
    String mob_phone;
    String card_id;

    public UserIdRequest(String client_id, int req_time, String sign_key, String mob_phone, String card_id) {
        this.client_id = client_id;
        this.req_time = req_time;
        this.sign_key = sign_key;
        this.mob_phone = mob_phone;
        this.card_id = card_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public int getReq_time() {
        return req_time;
    }

    public void setReq_time(int req_time) {
        this.req_time = req_time;
    }

    public String getSign_key() {
        return sign_key;
    }

    public void setSign_key(String sign_key) {
        this.sign_key = sign_key;
    }

    public String getMob_phone() {
        return mob_phone;
    }

    public void setMob_phone(String mob_phone) {
        this.mob_phone = mob_phone;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    @Override
    public String toString() {
        return "UserIdRequest{" +
                "client_id='" + client_id + '\'' +
                ", req_time=" + req_time +
                ", sign_key='" + sign_key + '\'' +
                ", mob_phone='" + mob_phone + '\'' +
                ", card_id='" + card_id + '\'' +
                '}';
    }
}
