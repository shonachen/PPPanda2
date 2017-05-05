package com.pppanda.request;

/**
 * Created by Administrator on 2017/5/3.
 */

public class LoginRequest {
    String client_id;
    int req_time;
    String sign_key;
    int user_id;
    String password;
    String finger;
    int login_type;
    String login_deviceid;
    String login_lang;

    public LoginRequest(String client_id, int req_time, String sign_key, int user_id, String password, String finger, int login_type, String login_deviceid, String login_lang) {
        this.client_id = client_id;
        this.req_time = req_time;
        this.sign_key = sign_key;
        this.user_id = user_id;
        this.password = password;
        this.finger = finger;
        this.login_type = login_type;
        this.login_deviceid = login_deviceid;
        this.login_lang = login_lang;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFinger() {
        return finger;
    }

    public void setFinger(String finger) {
        this.finger = finger;
    }

    public int getLogin_type() {
        return login_type;
    }

    public void setLogin_type(int login_type) {
        this.login_type = login_type;
    }

    public String getLogin_deviceid() {
        return login_deviceid;
    }

    public void setLogin_deviceid(String login_deviceid) {
        this.login_deviceid = login_deviceid;
    }

    public String getLogin_lang() {
        return login_lang;
    }

    public void setLogin_lang(String login_lang) {
        this.login_lang = login_lang;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "client_id='" + client_id + '\'' +
                ", req_time=" + req_time +
                ", sign_key='" + sign_key + '\'' +
                ", user_id=" + user_id +
                ", password='" + password + '\'' +
                ", finger='" + finger + '\'' +
                ", login_type=" + login_type +
                ", login_deviceid='" + login_deviceid + '\'' +
                ", login_lang='" + login_lang + '\'' +
                '}';
    }
}
