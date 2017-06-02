package com.pppanda.response;

import com.pppanda.entity.HCCDataEntity;
import com.pppanda.entity.UserRelationEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/1.
 */

public class GetUserRelationResponse extends BaseResponse {
    Body body;

    public GetUserRelationResponse(int code, String code_msg, Body body) {
        super(code, code_msg);
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public class Body {
        ArrayList<UserRelationEntity> ris;

        public Body(ArrayList<UserRelationEntity> ris) {
            this.ris = ris;
        }

        public ArrayList<UserRelationEntity> getRis() {
            return ris;
        }

        public void setRis(ArrayList<UserRelationEntity> ris) {
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
