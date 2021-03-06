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
import com.pppanda.entity.DictRelationEntity;
import com.pppanda.entity.HccDataRankEntity;
import com.pppanda.entity.UserRelationEntity;
import com.pppanda.request.GetBaseInfoRequest;
import com.pppanda.request.GetDictRelationRequest;
import com.pppanda.request.GetHccDataRankRequset;
import com.pppanda.request.GetUserRelationRequset;
import com.pppanda.request.LoginRequest;
import com.pppanda.request.UserIdRequest;
import com.pppanda.response.BaseResponse;
import com.pppanda.response.GetBaseInfoResponse;
import com.pppanda.response.GetDictRelationResponse;
import com.pppanda.response.GetHccDataRankResponse;
import com.pppanda.response.GetUserRelationResponse;
import com.pppanda.response.LoginResponse;
import com.pppanda.response.UserIdResponse;
import com.pppanda.util.Md5Util;
import com.pppanda.util.StatusBarUtils;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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
    private static final int MSG_GET_BASE_INFO_FAILED = 0X18;
//    private static final int MSG_GET_HCC_DATA_RANK_SUCCEED = 0X19;
    private static final int MSG_GET_HCC_DATA_RANK_FAILED = 0X20;
    private static final int MSG_GET_USER_RELATION_SUCCEED = 0X21;
    private static final int MSG_GET_USER_RELATION_FAILED = 0X22;
//    private static final int MSG_GET_DICT_RELATION_SUCCEED = 0X23;
    private static final int MSG_GET_DICT_RELATION_FAILED = 0X24;


    //OkHttp
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    Gson mGson = new Gson();
    boolean getBaseInfo;
    boolean getHccDataRank;
    boolean getUserRelation;
    boolean getDictRelation;

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

                    login();

//                    login(userID);
                    break;
                case MSG_GET_USER_ID_FAILED:
                    String msgObj = (String)msg.obj;
                    Toast.makeText(LoginActivity.this, msgObj, Toast.LENGTH_SHORT).show();
                    break;
                case MSG_LOGIN_SUCCEED:
//                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    getUserRelation();
                    getDictRelation();
                    break;
                case MSG_LOGIN_FAILED:
                    String msgObj1 = (String)msg.obj;
                    Toast.makeText(LoginActivity.this,msgObj1,Toast.LENGTH_SHORT).show();
                    break;
                case MSG_GET_BASE_INFO_FAILED:
                    Toast.makeText(LoginActivity.this,"获取数据失败",Toast.LENGTH_SHORT).show();
                    break;
                case MSG_GET_HCC_DATA_RANK_FAILED:
                    Toast.makeText(LoginActivity.this,"获取数据失败",Toast.LENGTH_SHORT).show();
                    break;
                case MSG_GET_USER_RELATION_SUCCEED:
                    getBaseInfo();
                    getHccDataRank();
                    break;
                case MSG_GET_USER_RELATION_FAILED:
                    Toast.makeText(LoginActivity.this,"获取数据失败",Toast.LENGTH_SHORT).show();
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
        if (getBaseInfo && getHccDataRank && getUserRelation && getDictRelation){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            LoginActivity.this.finish();
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
                Log.e("getUserID", json);

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
                    Log.e("getUserID", "mResponse.body().string() = \n" + mResult);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BaseResponse mBaseResponse = mGson.fromJson(mResult, BaseResponse.class);
                int code1 = mBaseResponse.getCode();
                String codeMsg1= mBaseResponse.getCode_msg();
                if(code1 == 0){
                    UserIdResponse mUserIdResponse = mGson.fromJson(mResult,UserIdResponse.class);
//                    int userID = mUserIdResponse.getBody().getUser_id();
                    Cache.userID = mUserIdResponse.getBody().getUser_id();

                    Log.e("getUserID", "code_msg = " + codeMsg1);
                    Log.e("getUserID", "code = " + code1);
                    Log.e("getUserID", "user_id = " + Cache.userID);

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


    private void login(){

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
                int user_id = Cache.userID;
                String finger = "";
                int login_type = 1;
                String login_deviceid = "Android" + "," + "JPUSH" + "," + Cache.userID;
                String login_lang = "zh_CN";
                LoginRequest mLoginRequest = new LoginRequest(client_id,req_time,sign_key,user_id,password,finger,login_type,login_deviceid,login_lang);
                String json = mGson.toJson(mLoginRequest);
                Log.e("login", json);

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
                    Log.e("login", "mResponse.body().string() = \n" + mResult);
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
                    Log.e("login", "code = " + code2);
                    Log.e("login", "code_msg = " + codeMsg2);
                    Log.e("login", "body = " + body);

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
                ArrayList<Integer> user_ids = new ArrayList<>();
                user_ids.add(Cache.userID);
                for (int i=0;i<Cache.mUserRelationEntitys.size();i++){
                    user_ids.add(Cache.mUserRelationEntitys.keyAt(i));
                    Log.e("FamilyUserId","familyuser_id=" + user_ids);
                }
                Log.e("user_id","user_id=" + user_ids);

                GetBaseInfoRequest mGetBaseInfoRequest = new GetBaseInfoRequest(access_token,user_ids);
                String json = mGson.toJson(mGetBaseInfoRequest);
                Log.e("getBaseInfo",json);

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
                    Log.e("getBaseInfo", "mResponse.body().string() = \n" + mResult);
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
                    if (mBaseInfo == null){
                        getBaseInfo = true;
                        judgeSucceed();
                    }else{
                        for(int i=0;i<mBaseInfo.size();i++){
                            Cache.mBaseInfoEntitys.put(mBaseInfo.get(i).getUser_id(),mBaseInfo.get(i));
                        }
                        getBaseInfo = true;
                        judgeSucceed();

                        Log.e("getBaseInfo", "body = " + body);
                    }
                }else {
//                    getBaseInfo();
                    mHandler.sendEmptyMessage(MSG_GET_BASE_INFO_FAILED);
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
                ArrayList<Integer> user_ids = new ArrayList<>();
                user_ids.add(Cache.userID);
                GetHccDataRankRequset mGetHccDataRankRequset = new GetHccDataRankRequset(access_token,user_ids);
                String json = mGson.toJson(mGetHccDataRankRequset);
                Log.e("getHccDataRank",json);

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
                    Log.e("getHccDataRank", "mResponse.body().string() = \n" + mResult);
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
                    if (mHccDataRank == null){
                        getHccDataRank = true;
                        judgeSucceed();
                    }else{
                        for(int i=0;i<mHccDataRank.size();i++){
                            Cache.mHccDataRankEntitys.put(mHccDataRank.get(i).getUser_id(),mHccDataRank.get(i));
                        }
                        getHccDataRank = true;
                        judgeSucceed();

                        Log.e("getHccDataRank", "body = " + body);
                    }
                }else {
//                    getHccDataRank();
                    mHandler.sendEmptyMessage(MSG_GET_HCC_DATA_RANK_FAILED);
                }
            }
        };
        mHccDataRank.start();
    }

    public void getUserRelation(){
        Thread mUserRelation = new Thread(){
            @Override
            public void run() {
                String access_token = Cache.accessToken;
                GetUserRelationRequset mGetUserRelationRequset = new GetUserRelationRequset(access_token);
                String json = mGson.toJson(mGetUserRelationRequset);
                Log.e("getUserRelation",json);

                String url = "http://api.pp-panda.cc:8080/v1/user/userrelationget";

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
                    Log.e("getUserRelation", "mResponse.body().string() = \n" + mResult);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BaseResponse mBaseResponse = mGson.fromJson(mResult, BaseResponse.class);
                int code5 = mBaseResponse.getCode();
                String codeMsg = mBaseResponse.getCode_msg();
                if (code5 == 0){
                    GetUserRelationResponse mGetUserRelationResponse = mGson.fromJson(mResult,GetUserRelationResponse.class);
                    String body = mGetUserRelationResponse.getBody().toString();
                    ArrayList<UserRelationEntity> mUserRelation = mGetUserRelationResponse.getBody().getRis();
                    if (mUserRelation == null){
                        getUserRelation = true;
                        mHandler.sendEmptyMessage(MSG_GET_USER_RELATION_SUCCEED);
                        judgeSucceed();
                    }else {
                        for(int i=0;i<mUserRelation.size();i++){
                            if (mUserRelation.get(i).getRelation_user_id() == Cache.userID){
                                Cache.mUserRelationEntitys.put(mUserRelation.get(i).getUser_id(), mUserRelation.get(i));
                            }else {
                                Cache.mUserRelationEntitys.put(mUserRelation.get(i).getRelation_user_id(), mUserRelation.get(i));
                            }

                        }
                        getUserRelation = true;
                        mHandler.sendEmptyMessage(MSG_GET_USER_RELATION_SUCCEED);
                        judgeSucceed();

                        Log.e("getUserRelation", "body = " + body);
                    }

                }else {
                    getUserRelation();
                    mHandler.sendEmptyMessage(MSG_GET_USER_RELATION_FAILED);
                }

            }
        };
        mUserRelation.start();
    }

    public void getDictRelation(){

        Thread mDictRelation = new Thread(){

            @Override
            public void run(){
                String access_token = Cache.accessToken;
                int relation_code = 0;
                int page = 1;
                int page_size = Integer.MAX_VALUE;
                GetDictRelationRequest mGetDictRelationRequest = new GetDictRelationRequest(access_token,
                        relation_code,page,page_size);
                String json = mGson.toJson(mGetDictRelationRequest);
                Log.e("getDictRelation",json);

                String url = "http://api.pp-panda.cc:8080/v1/dict/dictrelationget";

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
                    Log.e("getDictRelation", "mResponse.body().string() = \n" + mResult);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BaseResponse mBaseResponse = mGson.fromJson(mResult, BaseResponse.class);
                int code6 = mBaseResponse.getCode();
                String codeMsg = mBaseResponse.getCode_msg();
                if (code6 == 0){
                    GetDictRelationResponse mGetDictRelationResponse = mGson.fromJson(mResult,GetDictRelationResponse.class);
                    String body = mGetDictRelationResponse.getBody().toString();
                    ArrayList<DictRelationEntity> mDictRelation = mGetDictRelationResponse.getBody().getRis();
                    if (mDictRelation == null){
                        getDictRelation = true;
                        judgeSucceed();
                    }else {
                        for(int i=0;i<mDictRelation.size();i++){
                            Cache.mDictRelationEntity.put(mDictRelation.get(i).getRelation_code(),mDictRelation.get(i));
                            Cache.mDictRelation.put(mDictRelation.get(i).getRelation_code(),mDictRelation.get(i).getRelation_desc());
                        }
                        getDictRelation = true;
                        judgeSucceed();

                        Log.e("getDictRelation", "body = " + body);
                    }

                }else {
                    getDictRelation();
                    mHandler.sendEmptyMessage(MSG_GET_DICT_RELATION_FAILED);
                }
            }
        };
        mDictRelation.start();
    }

    /** 获取UsersIDs */
    private ArrayList<Integer> getUserIDs(){
        ArrayList<Integer> user_ids = new ArrayList<>();
        user_ids.add(Cache.userID);
        for(int i=0;i<Cache.mUserRelationEntitys.size();i++){
            user_ids.add(Cache.mUserRelationEntitys.keyAt(i));
        }

        return user_ids;
    }


}







































