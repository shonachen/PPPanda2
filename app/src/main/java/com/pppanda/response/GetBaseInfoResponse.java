package com.pppanda.response;

import com.pppanda.entity.BaseInfoEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/8.
 */

public class GetBaseInfoResponse extends BaseResponse {
    Body body;

    public GetBaseInfoResponse(int code, String code_msg, Body body) {
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
        ArrayList<BaseInfoEntity> pis;

        public Body(ArrayList<BaseInfoEntity> pis) {
            this.pis = pis;
        }

        public ArrayList<BaseInfoEntity> getPis() {
            return pis;
        }

        public void setPis(ArrayList<BaseInfoEntity> pis) {
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
