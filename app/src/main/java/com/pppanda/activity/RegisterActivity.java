package com.pppanda.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pppanda.R;
import com.pppanda.util.StatusBarUtils;

/**
 * Created by Administrator on 2017/5/3.
 */

public class RegisterActivity extends Activity {
    ImageView ivBack,ivEye,ivCheckBox;
    EditText etPhone,etPassword,etIdentify;
    TextView tvProtocol;
    Button btnidentify,btnregister;
    boolean eyeOpen = false;
    boolean checkBox = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//        //无title
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //设置图片全屏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //状态栏透明
        StatusBarUtils.transparentStatusBar(this);
        setContentView(R.layout.activity_register);
//        final ImageView ivBack = (ImageView)findViewById(R.id.register_back);
//        final ImageView ivEye = (ImageView)findViewById(R.id.register_eye);
//        final EditText etLoginPassword = (EditText)findViewById(R.id.register_et_password);
//        final ImageView ivCheckBox = (ImageView)findViewById(R.id.register_check_box);
//        TextView tvProtocol = (TextView)findViewById(R.id.register_tv_protocol);
        ivBack = (ImageView)findViewById(R.id.register_back);
        ivEye = (ImageView)findViewById(R.id.register_eye);
        etPhone = (EditText)findViewById(R.id.register_et_phone);
        etPassword = (EditText)findViewById(R.id.register_et_password);
        etIdentify = (EditText)findViewById(R.id.register_et_identify);
        ivCheckBox = (ImageView)findViewById(R.id.register_check_box);
        tvProtocol = (TextView)findViewById(R.id.register_tv_protocol);
        btnidentify = (Button)findViewById(R.id.register_identify_acquire);
        btnregister = (Button)findViewById(R.id.register_btn_register);

        //返回
        ivBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

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

        //点击点击获取按钮，点击后账号注册，并发送验证码



        //勾选用户注册协议勾选框
        ivCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox){
                    ivCheckBox.setImageResource(R.mipmap.checkbox_1);
                    checkBox = false;
                }else {
                    ivCheckBox.setImageResource(R.mipmap.checkbox_2);
                    checkBox = true;
                }
            }
        });

        //点击用户注册协议
        tvProtocol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,ProtocolActivity.class);
                startActivity(intent);
            }
        });
        tvProtocol.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//添加下划线
        tvProtocol.getPaint().setAntiAlias(true);//抗锯齿

    }
}
