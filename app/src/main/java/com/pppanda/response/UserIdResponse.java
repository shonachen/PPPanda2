package com.pppanda.response;

/**
 * Created by Administrator on 2017/5/4.
 */

public class UserIdResponse extends BaseResponse{

    Body body;

    public UserIdResponse(int code, String code_msg, Body body) {
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

        public Body(int user_id) {
            this.user_id = user_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        @Override
        public String toString() {
            return "Body{" +
                    "user_id=" + user_id +
                    '}';
        }
    }
}
