package com.pppanda.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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
import com.pppanda.request.ModifyPaswordRequest;
import com.pppanda.response.BaseResponse;
import com.pppanda.response.ModifyPasswordResponse;
import com.pppanda.response.MyPhoneSendCodeResponse;
import com.pppanda.util.StatusBarUtils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/15.
 */

public class ModifyPasswordActivity extends Activity{
    private static final int MSG_MODIFY_PASSWORD_SUCCEED = 0x86;
    private static final int MSG_MODIFY_PASSWORD_FAILED = 0x87;

    EditText etOldPassword,etNewPassword;
    ImageView ivOldEye,ivNewEye;
    ImageView ivPassBack;
    TextView tvPassTitle;
    Button btnPasswoerdComplete;
    boolean eyeOpen = false;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_MODIFY_PASSWORD_SUCCEED:
                    Intent intent = new Intent(ModifyPasswordActivity.this,MainActivity.class);
                    intent.putExtra("fragid",3);
                    startActivity(intent);
                    break;
                case MSG_MODIFY_PASSWORD_FAILED:
                    String msgObj = (String) msg.obj;
                    Toast.makeText(ModifyPasswordActivity.this,msgObj,Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_modify_password);

        initView();
    }

    private void initView(){
        etOldPassword = (EditText)findViewById(R.id.et_old_password);
        etNewPassword = (EditText)findViewById(R.id.et_new_password);
        ivOldEye = (ImageView)findViewById(R.id.iv_old_password_eye);
        ivNewEye = (ImageView)findViewById(R.id.iv_new_password_eye);
        ivPassBack = (ImageView)findViewById(R.id.back);
        tvPassTitle = (TextView)findViewById(R.id.tv_title);
        btnPasswoerdComplete = (Button)findViewById(R.id.btn_passwoer_complete);

        ChangeListener listener = new ChangeListener();
        tvPassTitle.setText("修改密码");
        ivOldEye.setOnClickListener(listener);
        ivNewEye.setOnClickListener(listener);
        ivPassBack.setOnClickListener(listener);
        btnPasswoerdComplete.setOnClickListener(listener);

    }

    //密码明文密文切换
    class ChangeListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_old_password_eye:
                    if (eyeOpen){
                        etOldPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        ivOldEye.setImageResource(R.mipmap.eye2);
                        eyeOpen = false;
                    }else{
                        etOldPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        ivOldEye.setImageResource(R.mipmap.eye);
                        eyeOpen = true;
                    }
                    break;
                case R.id.iv_new_password_eye:
                    if (eyeOpen){
                        etNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        ivNewEye.setImageResource(R.mipmap.eye2);
                        eyeOpen = false;
                    }else{
                        etNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        ivNewEye.setImageResource(R.mipmap.eye);
                        eyeOpen = true;
                    }
                    break;
                case R.id.btn_passwoer_complete:
                    modifyPassword();
                    break;
                case R.id.back:
                    Intent intent = new Intent(ModifyPasswordActivity.this,MainActivity.class);
                    intent.putExtra("fragid",3);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }

    private void modifyPassword(){
        final Thread mPassword = new Thread(){
            @Override
            public void run() {
                String access_token = Cache.accessToken;
                String old_pass = etOldPassword.getText().toString();
                String new_pass = etNewPassword.getText().toString();
                ModifyPaswordRequest mModifyPaswordRequest = new ModifyPaswordRequest(access_token,old_pass,new_pass);

                Gson mGson = new Gson();
                //OkHttp
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");

                String json = mGson.toJson(mModifyPaswordRequest);
                Log.e("ModifyPassword",json);

                String url = "http://api.pp-panda.cc:8080/v1/user/userchangepassword";
                OkHttpClient mOkHttpClient = new OkHttpClient();

                Request mRequest = new Request.Builder()
                        .url(url)
                        .post(RequestBody.create(JSON,json))
                        .build();

                Response mResponse = null;
                try {
                    mResponse = mOkHttpClient.newCall(mRequest).execute();
                }catch (IOException e){
                    e.printStackTrace();
                }

                String mResult = null;
                try {
                    mResult = mResponse.body().string();
                    Log.e("getUserID", "mResponse.body().string() = \n" + mResult);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BaseResponse mBaseResponse = mGson.fromJson(mResult,BaseResponse.class);
                int code = mBaseResponse.getCode();
                String codeMsg = mBaseResponse.getCode_msg();
                if (code == 0){
                    ModifyPasswordResponse mModifyPasswordResponse = mGson.fromJson(mResult, ModifyPasswordResponse.class);
                    mHandler.sendEmptyMessage(MSG_MODIFY_PASSWORD_SUCCEED);
                }else {
                    Message msg = new Message();
                    msg.what = MSG_MODIFY_PASSWORD_FAILED;
                    msg.obj = codeMsg;
                    mHandler.sendMessage(msg);
                }
            }
        };
        mPassword.start();
    }
}
