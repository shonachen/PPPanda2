package com.pppanda.response;

import com.pppanda.DictRelation;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/8.
 */

public class GetDictRelationResponse extends BaseResponse {
    Body body;

    public GetDictRelationResponse(int code, String code_msg, Body body) {
        super(code, code_msg);
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public  class Body{
        int total;
        ArrayList<DictRelation> ris;

        public Body(int total, ArrayList<DictRelation> ris) {
            this.total = total;
            this.ris = ris;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public ArrayList<DictRelation> getRis() {
            return ris;
        }

        public void setRis(ArrayList<DictRelation> ris) {
            this.ris = ris;
        }

        @Override
        public String toString() {
            return "Body{" +
                    "total=" + total +
                    ", ris=" + ris +
                    '}';
        }
    }
}
