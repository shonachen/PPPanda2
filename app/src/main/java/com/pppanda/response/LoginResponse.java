package com.pppanda.response;

/**
 * Created by Administrator on 2017/5/3.
 */

public class LoginResponse extends BaseResponse {
    Body body;

    public LoginResponse(int code, String code_msg, Body body) {
        super(code, code_msg);
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public class Body{
        int user_id;
        String access_token;
        String refresh_token;
        int expired_time;

        public Body(int user_id, String access_token, String refresh_token, int expired_time) {
            this.user_id = user_id;
            this.access_token = access_token;
            this.refresh_token = refresh_token;
            this.expired_time = expired_time;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public int getExpired_time() {
            return expired_time;
        }

        public void setExpired_time(int expired_time) {
            this.expired_time = expired_time;
        }

        @Override
        public String toString() {
            return "Body{" +
                    "user_id=" + user_id +
                    ", access_token='" + access_token + '\'' +
                    ", refresh_token='" + refresh_token + '\'' +
                    ", expired_time=" + expired_time +
                    '}';
        }
    }

}
