package com.pppanda.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
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
import com.pppanda.request.MyPhoneCheckCodeRequest;
import com.pppanda.request.MyPhoneSendCodeRequest;
import com.pppanda.response.BaseResponse;
import com.pppanda.response.MyPhoneCheckCodeResponse;
import com.pppanda.response.MyPhoneSendCodeResponse;
import com.pppanda.util.CountDownTimerUtils;
import com.pppanda.util.StatusBarUtils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/5/15.
 */

public class MyPhoneActivity extends Activity {
    private static final int MSG_SEND_CODE_SUCCEED = 0x34;
    private static final int MSG_SEND_CODE_FAILED = 0x35;
    private static final int MSG_CHECK_CODE_SUCCEED = 0x53;
    private static final int MSG_CHECK_CODE_FAILED = 0x54;

    ImageView ivPhoneBack;
    TextView tvPhoneTitle,tvOldPhone;
    EditText etNewPhone,etOldIdentify,etNewIdentify;
    Button btnSendCode,btnPhoneComplete;
    TextView tvPhoneRemark;
//    String newPhone;

    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    Gson mGson = new Gson();

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_SEND_CODE_SUCCEED:
                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(btnSendCode,90000,1000);
                    mCountDownTimerUtils.start();
                    break;
                case MSG_SEND_CODE_FAILED:
                    String msgObj = (String) msg.obj;
                    Toast.makeText(MyPhoneActivity.this,msgObj,Toast.LENGTH_SHORT).show();
                    break;
                case MSG_CHECK_CODE_SUCCEED:
//                    String newPhone = (String) msg.obj;
//                    tvPhoneRemark.setText(newPhone);
                    Intent intent = new Intent(MyPhoneActivity.this, MainActivity.class);
                    intent.putExtra("fragid",3);//判定返回MainActivity的Fragment

                    startActivity(intent);
                    break;
                case MSG_CHECK_CODE_FAILED:
                    String msgObj1 = (String) msg.obj;
                    Toast.makeText(MyPhoneActivity.this,msgObj1,Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏颜色
        StatusBarUtils.compat(this, Color.rgb(27,161,212));
        setContentView(R.layout.activity_myphone);

        ivPhoneBack = (ImageView)findViewById(R.id.back);
        tvPhoneTitle = (TextView)findViewById(R.id.tv_title);
        tvOldPhone = (TextView)findViewById(R.id.tv_old_phone);
        etNewPhone = (EditText)findViewById(R.id.et_new_phone);
        etOldIdentify = (EditText)findViewById(R.id.et_old_identify);
        etNewIdentify = (EditText)findViewById(R.id.et_new_identify);
        btnSendCode = (Button)findViewById(R.id.btn_send_code);
        btnPhoneComplete = (Button)findViewById(R.id.btn_complete);
        tvPhoneRemark = (TextView)findViewById(R.id.tv_myself_phone_remark);

        tvOldPhone.setText(Cache.mBaseInfoEntitys.get(Cache.userID).getMob_phone());
        tvPhoneTitle.setText("修改手机号");

        //返回按钮
        ivPhoneBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPhoneActivity.this, MainActivity.class);
                intent.putExtra("fragid",3);//判定返回MainActivity的Fragment

                startActivity(intent);
            }
        });

        //发送验证码按钮
        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCode();
            }
        });

        //完成按钮
        btnPhoneComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCode();
            }
        });
    }

    private void sendCode(){
        Thread mSendCode = new Thread(){
            @Override
            public void run() {
                String access_token = Cache.accessToken;
                String old_phone = Cache.mBaseInfoEntitys.get(Cache.userID).getMob_phone();
                String new_phone = etNewPhone.getText().toString();
                MyPhoneSendCodeRequest mMyPhoneSendCodeRequest = new MyPhoneSendCodeRequest(access_token,old_phone,new_phone);

                String json = mGson.toJson(mMyPhoneSendCodeRequest);
                Log.e("sendCode",json);

                String url = "http://api.pp-panda.cc:8080/v1/user/userphonechange";

                OkHttpClient mOkHttpClient = new OkHttpClient();

                Request mRequest = new Request.Builder()
                        .url(url)
                        .post(RequestBody.create(JSON,json))
                        .build();

                Response mResponse = null;
                try{
                    mResponse = mOkHttpClient.newCall(mRequest).execute();
                }catch (IOException e){
                    e.printStackTrace();
                }

                String mResult = null;
                try {
                    mResult = mResponse.body().string();
                    Log.e("sendCode", "mResponse.body().string() = \n" + mResult);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BaseResponse mBaseResponse = mGson.fromJson(mResult,BaseResponse.class);
                int code = mBaseResponse.getCode();
                String codeMsg = mBaseResponse.getCode_msg();
                if (code == 0){
                    MyPhoneSendCodeResponse mMyPhoneSendCodeResponse = mGson.fromJson(mResult,MyPhoneSendCodeResponse.class);
//                    Message msg = new Message();
//                    msg.what = MSG_SEND_CODE_SUCCEED;
                    mHandler.sendEmptyMessage(MSG_SEND_CODE_SUCCEED);
                }else{
                    Message msg = new Message();
                    msg.what = MSG_SEND_CODE_FAILED;
                    msg.obj = codeMsg;
                    mHandler.sendMessage(msg);
                }

            }
        };
        mSendCode.start();

    }

    private void checkCode(){
        Thread mCheckCode = new Thread(){
            @Override
            public void run() {
                String access_token = Cache.accessToken;
                String old_code = etOldIdentify.getText().toString();
                String new_code = etNewIdentify.getText().toString();
                MyPhoneCheckCodeRequest mMyPhoneCheckCodeRequest = new MyPhoneCheckCodeRequest(access_token,old_code,new_code);

                String json = mGson.toJson(mMyPhoneCheckCodeRequest);
                Log.e("checkCode",json);

                String url = "http://api.pp-panda.cc:8080/v1/user/userphonechangeconfirmcode";

                OkHttpClient mOkHttpClient = new OkHttpClient();

                Request mRequest = new Request.Builder()
                        .url(url)
                        .post(RequestBody.create(JSON,json))
                        .build();

                Response mResponse = null;
                try{
                    mResponse = mOkHttpClient.newCall(mRequest).execute();
                }catch (IOException e){
                    e.printStackTrace();
                }

                String mResult = null;
                try {
                    mResult = mResponse.body().string();
                    Log.e("checkCode", "mResponse.body().string() = \n" + mResult);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BaseResponse mBaseResponse= mGson.fromJson(mResult,BaseResponse.class);
                int code1 = mBaseResponse.getCode();
                String codeMsg1 = mBaseResponse.getCode_msg();
                if (code1 == 0){
                    MyPhoneCheckCodeResponse mMyPhoneCheckCodeResponse = mGson.fromJson(mResult,MyPhoneCheckCodeResponse.class);
                    Cache.mBaseInfoEntitys.get(Cache.userID).setMob_phone(etNewPhone.getText().toString());
                    Message msg = new Message();
//                    msg.what = MSG_CHECK_CODE_SUCCEED;
//                    msg.obj = etNewPhone.getText().toString();
                    mHandler.sendEmptyMessage(MSG_CHECK_CODE_SUCCEED);
                }else {
                    Message msg = new Message();
                    msg.what = MSG_CHECK_CODE_FAILED;
                    msg.obj = codeMsg1;
                    mHandler.sendMessage(msg);
                }
            }
        };
        mCheckCode.start();
    }

}































