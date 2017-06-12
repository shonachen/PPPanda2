package com.pppanda.response;

/**
 * Created by Administrator on 2017/6/12.
 */

public class GetFamilyUserIdResponse extends BaseResponse {
    Body body;

    public GetFamilyUserIdResponse(int code, String code_msg, Body body) {
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
        String finger1;
        String finger2;
        int hcccard_status;

        public Body(int user_id, String finger1, String finger2, int hcccard_status) {
            this.user_id = user_id;
            this.finger1 = finger1;
            this.finger2 = finger2;
            this.hcccard_status = hcccard_status;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getFinger1() {
            return finger1;
        }

        public void setFinger1(String finger1) {
            this.finger1 = finger1;
        }

        public String getFinger2() {
            return finger2;
        }

        public void setFinger2(String finger2) {
            this.finger2 = finger2;
        }

        public int getHcccard_status() {
            return hcccard_status;
        }

        public void setHcccard_status(int hcccard_status) {
            this.hcccard_status = hcccard_status;
        }

        @Override
        public String toString() {
            return "Body{" +
                    "user_id=" + user_id +
                    ", finger1='" + finger1 + '\'' +
                    ", finger2='" + finger2 + '\'' +
                    ", hcccard_status=" + hcccard_status +
                    '}';
        }
    }
}
