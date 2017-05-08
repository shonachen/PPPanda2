package com.pppanda.response;

import com.pppanda.UserInfomation;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/8.
 */

public class GetUserInfoResponse extends BaseResponse {
    Body body;

    public GetUserInfoResponse(int code, String code_msg, Body body) {
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
        ArrayList<UserInfomation> pis;

        public Body(ArrayList<UserInfomation> pis) {
            this.pis = pis;
        }

        public ArrayList<UserInfomation> getPis() {
            return pis;
        }

        public void setPis(ArrayList<UserInfomation> pis) {
            this.pis = pis;
        }

        @Override
        public String toString() {
            return "Body{" +
                    "pis=" + pis +
                    '}';
        }
    }
}
