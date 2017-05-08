package com.pppanda.request;

/**
 * Created by Administrator on 2017/5/8.
 */

public class RegisterRequest {
    String client_id,sign_key,name,password;
    int req_time,name_type;

    public RegisterRequest(String client_id, String sign_key, String name, String password, int req_time, int name_type) {
        this.client_id = client_id;
        this.sign_key = sign_key;
        this.name = name;
        this.password = password;
        this.req_time = req_time;
        this.name_type = name_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getSign_key() {
        return sign_key;
    }

    public void setSign_key(String sign_key) {
        this.sign_key = sign_key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getReq_time() {
        return req_time;
    }

    public void setReq_time(int req_time) {
        this.req_time = req_time;
    }

    public int getName_type() {
        return name_type;
    }

    public void setName_type(int name_type) {
        this.name_type = name_type;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "client_id='" + client_id + '\'' +
                ", sign_key='" + sign_key + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", req_time=" + req_time +
                ", name_type=" + name_type +
                '}';
    }
}
