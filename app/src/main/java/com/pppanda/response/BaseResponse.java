package com.pppanda.response;

/**
 * Created by Administrator on 2017/5/3.
 */

public class BaseResponse {
    int code;
    String code_msg;

    public BaseResponse(int code,String code_msg){
        this.code = code;
        this.code_msg = code_msg;
    }


    public int getCode(){
        return code;
    }

    public void setCode(int code){
        this.code = code;
    }

    public String getCode_msg(){
        return code_msg;
    }

    public void setCode_msg(String code_msg){
        this.code_msg = code_msg;
    }

    public String toString(){
        return "BaseResponse{" +
                "code='" + code + '\'' +
                "code_msg='" + code_msg + '\'' +
                "}";
    }
}
