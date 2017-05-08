package com.pppanda.request;

/**
 * Created by Administrator on 2017/5/8.
 */

public class GetDictRelationRequest {
    String access_token;
    int relation_code;
    int page;
    int page_size;

    public GetDictRelationRequest(String access_token, int relation_code, int page, int page_size) {
        this.access_token = access_token;
        this.relation_code = relation_code;
        this.page = page;
        this.page_size = page_size;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getRelation_code() {
        return relation_code;
    }

    public void setRelation_code(int relation_code) {
        this.relation_code = relation_code;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    @Override
    public String toString() {
        return "GetDictRelationRequest{" +
                "access_token='" + access_token + '\'' +
                ", relation_code=" + relation_code +
                ", page=" + page +
                ", page_size=" + page_size +
                '}';
    }
}
