package com.pppanda.response;

import com.pppanda.entity.HccDataRankEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/9.
 */

public class GetHccDataRankResponse extends BaseResponse {
    Body body;

    public GetHccDataRankResponse(int code, String code_msg, Body body) {
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
        ArrayList<HccDataRankEntity> ris;

        public Body(ArrayList<HccDataRankEntity> ris) {
            this.ris = ris;
        }

        public ArrayList<HccDataRankEntity> getRis() {
            return ris;
        }

        public void setRis(ArrayList<HccDataRankEntity> ris) {
            this.ris = ris;
        }

        @Override
        public String toString() {
            return "Body{" +
                    "ris=" + ris +
                    '}';
        }
    }
}
