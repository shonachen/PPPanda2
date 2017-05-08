package com.pppanda.response;

import com.pppanda.HccData;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/8.
 */

public class GetHccDataResponse extends BaseResponse {
    Body body;

    public GetHccDataResponse(int code, String code_msg, Body body) {
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
        int total;
        ArrayList<HccData> hdia;

        public Body(int total, ArrayList<HccData> hdia) {
            this.total = total;
            this.hdia = hdia;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public ArrayList<HccData> getHdia() {
            return hdia;
        }

        public void setHdia(ArrayList<HccData> hdia) {
            this.hdia = hdia;
        }

        @Override
        public String toString() {
            return "Body{" +
                    "total=" + total +
                    ", hdia=" + hdia +
                    '}';
        }
    }
}
