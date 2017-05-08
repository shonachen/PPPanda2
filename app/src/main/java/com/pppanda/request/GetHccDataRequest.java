package com.pppanda.request;

/**
 * Created by Administrator on 2017/5/8.
 */

public class GetHccDataRequest {
    String access_token;
    int page;
    int page_size;
    int start_time;
    int end_time;
    int user_id;

    public GetHccDataRequest(String access_token, int page, int page_size, int start_time, int end_time, int user_id) {
        this.access_token = access_token;
        this.page = page;
        this.page_size = page_size;
        this.start_time = start_time;
        this.end_time = end_time;
        this.user_id = user_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
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

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "GetHccDataRequest{" +
                "access_token='" + access_token + '\'' +
                ", page=" + page +
                ", page_size=" + page_size +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", user_id=" + user_id +
                '}';
    }
}
