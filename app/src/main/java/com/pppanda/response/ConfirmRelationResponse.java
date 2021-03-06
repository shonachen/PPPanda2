package com.pppanda.response;

/**
 * Created by Administrator on 2017/6/9.
 */

public class ConfirmRelationResponse extends BaseResponse {
    Body body;

    public ConfirmRelationResponse(int code, String code_msg, Body body) {
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
        public Body() {
        }

        @Override
        public String toString() {
            return "Body{}";
        }
    }
}
