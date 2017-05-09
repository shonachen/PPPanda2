package com.pppanda.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pppanda.R;
import com.pppanda.cache.Cache;
import com.pppanda.entity.BaseInfoEntity;
import com.pppanda.entity.HccDataRankEntity;
import com.pppanda.request.GetBaseInfoRequest;
import com.pppanda.request.GetHccDataRankRequset;
import com.pppanda.request.LoginRequest;
import com.pppanda.request.UserIdRequest;
import com.pppanda.response.BaseResponse;
import com.pppanda.response.GetBaseInfoResponse;
import com.pppanda.response.GetHccDataRankResponse;
import com.pppanda.response.LoginResponse;
import com.pppanda.response.UserIdResponse;
import com.pppanda.util.Md5Util;
import com.pppanda.util.StatusBarUtils;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/4/28.
 */

public class LoginActivity extends Activity {
    private static final int MSG_GET_USER_ID_SUCCEED = 0X13;
    private static final int MSG_GET_USER_ID_FAILED = 0X14;
    private static final int MSG_LOGIN_SUCCEED = 0X15;
    private static final int MSG_LOGIN_FAILED = 0X16;
//    private static final int MSG_GET_BASE_INFO_SUCCEED = 0X17;
//    private static final int MSG_GET_BASE_INFO_FAILED = 0X18;
//    private static final int MSG_GET_HCC_DATA_RANK_SUCCEED = 0X19;
//    private static final int MSG_GET_HCC_DATA_RANK_FAILED = 0X20;

    //OkHttp
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    Gson mGson = new Gson();
    boolean getBaseInfo;
    boolean getHccDataRank;
//    boolean getDictRelation;

    public ImageView imageView;
    public EditText editText;
    boolean eyeOpen = false;
    String phone,password;
    EditText etPhone,etPassword;
    ImageView ivEye;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_GET_USER_ID_SUCCEED:
                    int userID = msg.arg1;
                    Log.e("TAG", "userID = "+userID);

                    login(userID);
                    break;
                case MSG_GET_USER_ID_FAILED:
                    String msgObj = (String)msg.obj;
                    Toast.makeText(LoginActivity.this, msgObj, Toast.LENGTH_SHORT).show();
                    break;
                case MSG_LOGIN_SUCCEED:
//                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    getBaseInfo();
                    getHccDataRank();
                    break;
                case MSG_LOGIN_FAILED:
                    String msgObj1 = (String)msg.obj;
                    Toast.makeText(LoginActivity.this,msgObj1,Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }

    };

//    public void judgeSucceed(){
//        if (getBaseInfo && getHccDataRank && getDictRelation){
//            startActivity(new Intent(LoginActivity.this,MainActivity.class));
//        }
//    }

    public void judgeSucceed(){
        if (getBaseInfo && getHccDataRank){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        设置android系统状态栏透明
//        StatusBarUtils.compat(this, Color.TRANSPARENT);
//        //无title
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //设置图片全屏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        StatusBarUtils.transparentStatusBar(this);

        setContentView(R.layout.activity_login);
        imageView = (ImageView)findViewById(R.id.login_eye);
        editText = (EditText)findViewById(R.id.login_et_password);
        ivEye = (ImageView)findViewById(R.id.login_eye);
        etPassword = (EditText)findViewById(R.id.login_et_password);
        etPhone = (EditText)findViewById(R.id.login_et_phone);
        Button btnLogin = (Button)findViewById(R.id.login_btn_login);
        Button btnRegister = (Button)findViewById(R.id.login_btn_register);
        TextView tvForgetPassword = (TextView)findViewById(R.id.login_tv_forget_password) ;

        //密码明文密文切换
        ivEye.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (eyeOpen){
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivEye.setImageResource(R.mipmap.eye2);
                    eyeOpen = false;
                }else{
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivEye.setImageResource(R.mipmap.eye);
                    eyeOpen = true;
                }
            }
        });

        //登录
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserID();
            }
        });

        //点击注册按钮进入注册页面
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        //点击忘记密码进入密码找回页面
        tvForgetPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
        tvForgetPassword.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//添加下划线
        tvForgetPassword.getPaint().setAntiAlias(true);//抗锯齿
    }


    private void getUserID(){
        final Thread mUserId = new Thread(){
            @Override
            public void run(){
                String client_id = "zx2016";
                int req_time = (int)(System.currentTimeMillis()/1000);
                String sign_key = Md5Util.md5(req_time + "binwen");
                String mob_phone = etPhone.getText().toString();
                String card_id = "";
                UserIdRequest mUserIdRequest = new UserIdRequest(client_id,req_time,sign_key,mob_phone,card_id);

                String json = mGson.toJson(mUserIdRequest);
                Log.e("TAG", json);

                String url = "http://api.pp-panda.cc:8080/v1/user/usergetid";

                //发送者
                OkHttpClient mOkHttpClient = new OkHttpClient();
                //请求
                Request mRequest = new Request.Builder()
                        .url(url)
                        .post(RequestBody.create(JSON, json))
                        .build();
                //请求过后的响应
                Response mResponse = null;
                try {
                    mResponse = mOkHttpClient.newCall(mRequest).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String mResult = null;
                try {
                    mResult = mResponse.body().string();
                    Log.e("TAG", "mResponse.body().string() = \n" + mResult);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BaseResponse mBaseResponse = mGson.fromJson(mResult, BaseResponse.class);
                int code1 = mBaseResponse.getCode();
                String codeMsg1= mBaseResponse.getCode_msg();
                if(code1 == 0){
                    UserIdResponse mUserIdResponseString = mGson.fromJson(mResult,UserIdResponse.class);
//                    int userID = mUserIdResponseString.getBody().getUser_id();
                    Cache.userID = mUserIdResponseString.getBody().getUser_id();

                    Log.e("TAG", "code_msg = " + codeMsg1);
                    Log.e("TAG", "code = " + code1);
                    Log.e("TAG", "user_id = " + Cache.userID);

//                        Message msg = new Message();
//                        msg.what = MSG_GET_USER_ID_SUCCEED;
//                        msg.arg1 = 0;
//                        msg.arg2 = 2;
//                        msg.obj = "Hello";
//                        mHandler.sendMessage(msg);
                    Message msg = new Message();
                    msg.what = MSG_GET_USER_ID_SUCCEED;
                    msg.arg1 = Cache.userID;
                    mHandler.sendMessage(msg);
                }else {
//                    mHandler.sendEmptyMessage(MSG_GET_USER_ID_FAILED);
                    Message msg = new Message();
                    msg.what = MSG_GET_USER_ID_FAILED;
                    msg.obj = codeMsg1;
                    mHandler.sendMessage(msg);
                }
            }
        };

        mUserId.start();
    }


    private void login(final int userID){

        Thread mlogin = new Thread(){
            @Override
            public void run(){
                String client_id = "zx2016";
                int req_time = (int)(System.currentTimeMillis()/1000);
                String sign_key = Md5Util.md5(req_time + "binwen");
//                        int user_id = 5570;
//                        String password = "Aa123456";
                password = etPassword.getText().toString();
                password = Md5Util.md5(password);
                int user_id = userID;
                String finger = "";
                int login_type = 1;
                String login_deviceid = "Android" + "," + "JPUSH" + "," + userID;
                String login_lang = "zh_CN";
                LoginRequest mLoginRequest = new LoginRequest(client_id,req_time,sign_key,user_id,password,finger,login_type,login_deviceid,login_lang);
                String json = mGson.toJson(mLoginRequest);
                Log.e("TAG", json);

                String url = "http://api.pp-panda.cc:8080/v1/user/userlogin";


                //发送者
                OkHttpClient mOkHttpClient = new OkHttpClient();
                //请求
                Request mRequest = new Request.Builder()
                        .url(url)
                        .post(RequestBody.create(JSON, json))
                        .build();
                //请求过后的响应
                Response mResponse = null;
                try {
                    mResponse = mOkHttpClient.newCall(mRequest).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String mResult = null;
                try {
                    mResult = mResponse.body().string();
                    Log.e("TAG", "mResponse.body().string() = \n" + mResult);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BaseResponse mBaseResponse = mGson.fromJson(mResult,BaseResponse.class);
                int code2 = mBaseResponse.getCode();
                String codeMsg2 = mBaseResponse.getCode_msg();

                if (code2 == 0){
                    LoginResponse mLoginResponse = mGson.fromJson(mResult,LoginResponse.class);
                    String body = mLoginResponse.getBody().toString();
                    Cache.accessToken = mLoginResponse.getBody().getAccess_token();
                    Log.e("TAG", "code = " + code2);
                    Log.e("TAG", "code_msg = " + codeMsg2);
                    Log.e("TAG", "body = " + body);

                    Message msg = new Message();
                    msg.what = MSG_LOGIN_SUCCEED;
                    mHandler.sendMessage(msg);
                }else {
                    Message msg = new Message();
                    msg.what = MSG_LOGIN_FAILED;
                    msg.obj = codeMsg2;
                    mHandler.sendMessage(msg);
                }

            }
        };
        mlogin.start();

    }

    private void getBaseInfo(){

        Thread mBaseInfo = new Thread(){
            @Override
            public void run() {
                String access_token = Cache.accessToken;
                int user_id = Cache.userID;
                GetBaseInfoRequest mGetBaseInfoRequest = new GetBaseInfoRequest(access_token,user_id);
                String json = mGson.toJson(mGetBaseInfoRequest);
                Log.e("TAG",json);

                String url = "http://api.pp-panda.cc:8080/v1/user/userprofileget";

                //发送者
                OkHttpClient mOkHttpClient = new OkHttpClient();
                //请求
                Request mRequest = new Request.Builder()
                        .url(url)
                        .post(RequestBody.create(JSON, json))
                        .build();
                //请求过后的响应
                Response mResponse = null;
                try {
                    mResponse = mOkHttpClient.newCall(mRequest).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String mResult = null;
                try {
                    mResult = mResponse.body().string();
                    Log.e("TAG", "mResponse.body().string() = \n" + mResult);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BaseResponse mBaseResponse = mGson.fromJson(mResult,BaseResponse.class);
                int code3 = mBaseResponse.getCode();
                String codeMsg3 = mBaseResponse.getCode_msg();
                if (code3 == 0){
                    GetBaseInfoResponse mGetBaseInfoResponse = mGson.fromJson(mResult,GetBaseInfoResponse.class);
                    String body = mGetBaseInfoResponse.getBody().toString();
                    ArrayList<BaseInfoEntity> mBaseInfo = mGetBaseInfoResponse.getBody().getPis();
                    for (int i=0;i<mBaseInfo.size();i++){
                        Cache.mBaseInfoEntity.put(mBaseInfo.get(i).getUser_id(),mBaseInfo.get(i));
                    }
                    getBaseInfo = true;

                    Log.e("TAG", "code = " + code3);
                    Log.e("TAG", "code_msg = " + codeMsg3);
                    Log.e("TAG", "body = " + body);

                }else {
                    getBaseInfo();
                }
            }
        };
        mBaseInfo.start();

    }

    public void getHccDataRank(){

        Thread mHccDataRank = new Thread(){
            @Override
            public void run(){
                String access_token = Cache.accessToken;
                int user_id = Cache.userID;
                GetHccDataRankRequset mGetHccDataRankRequset = new GetHccDataRankRequset(access_token,user_id);
                String json = mGson.toJson(mGetHccDataRankRequset);
                Log.e("TAG",json);

                String url = "http://api.pp-panda.cc:8080/v1/hccdata/hccdatarank";

                OkHttpClient mOkHttpClient = new OkHttpClient();
                Request mRequest = new Request.Builder()
                        .url(url)
                        .post(RequestBody.create(JSON,json))
                        .build();
                Response mResponse = null;
                try {
                    mResponse = mOkHttpClient.newCall(mRequest).execute();
                } catch (IOException e){
                    e.printStackTrace();
                }
                String mResult = null;
                try {
                    mResult = mResponse.body().string();
                    Log.e("TAG", "mResponse.body().string() = \n" + mResult);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BaseResponse mBaseResponse = mGson.fromJson(mResult,BaseResponse.class);
                int code4 = mBaseResponse.getCode();
                String codeMsg4 = mBaseResponse.getCode_msg();
                if (code4 == 0){
                    GetHccDataRankResponse mGetHccDataRankResponse = mGson.fromJson(mResult,GetHccDataRankResponse.class);
                    String body = mGetHccDataRankResponse.getBody().toString();
                    ArrayList<HccDataRankEntity> mHccDataRank = mGetHccDataRankResponse.getBody().getRis();
                    for(int i=0;i<mHccDataRank.size();i++){
                        Cache.mHccDataRankEntity.put(mHccDataRank.get(i).getUser_id(),mHccDataRank.get(i));
                    }
                    getHccDataRank = true;

                    Log.e("TAG", "code = " + code4);
                    Log.e("TAG", "code_msg = " + codeMsg4);
                    Log.e("TAG", "body = " + body);

                }else {
                    getHccDataRank();
                }
            }
        };
        mHccDataRank.start();
    }

//    public void getDictRelation(){
//
//        Thread mDictRelation = new Thread(){
//
//            @Override
//            public void run(){
//
//            }
//        };
//        mDictRelation.start();
//    }


}







































