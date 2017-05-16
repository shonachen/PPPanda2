package com.pppanda.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pppanda.R;
import com.pppanda.cache.Cache;
import com.pppanda.util.StatusBarUtils;

import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/5/15.
 */

public class MyPhoneActivity extends Activity {
    ImageView ivPhoneBack;
    TextView tvPhoneTitle,tvOldPhone;
    EditText etNewPhone,etOldIdentify,etNewIdentify;
    Button btnSendCode,btnPhoneComplete;

    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    Gson mGson = new Gson();

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

        tvOldPhone.setText(Cache.mBaseInfoEntitys.get(Cache.userID).getMob_phone());
        tvPhoneTitle.setText("修改手机号");

        ivPhoneBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPhoneActivity.this, MainActivity.class);
                intent.putExtra("fragid",3);//判定返回MainActivity的Fragment

                startActivity(intent);
            }
        });
    }

    private void sendCode(){

    }
}
