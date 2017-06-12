package com.pppanda.response;

/**
 * Created by Administrator on 2017/6/12.
 */

public class AddRelationResponse extends BaseResponse {
    Body body;

    public AddRelationResponse(int code, String code_msg, Body body) {
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
        int action;

        public Body(int action) {
            this.action = action;
        }

        public int getAction() {
            return action;
        }

        public void setAction(int action) {
            this.action = action;
        }

        @Override
        public String toString() {
            return "Body{" +
                    "action=" + action +
                    '}';
        }
    }
}
