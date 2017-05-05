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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pppanda.R;

/**
 * Created by Administrator on 2017/5/3.
 */

public class RegisterActivity extends Activity {
    boolean eyeOpen = false;
    boolean checkBox = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置图片全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        final ImageView ivBack = (ImageView)findViewById(R.id.register_back);
        final ImageView ivEye = (ImageView)findViewById(R.id.register_eye);
        final EditText etLoginPassword = (EditText)findViewById(R.id.register_et_password);
        final ImageView ivCheckBox = (ImageView)findViewById(R.id.register_check_box);
        TextView tvProtocol = (TextView)findViewById(R.id.register_tv_protocol);

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
                    etLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivEye.setImageResource(R.mipmap.eye2);
                    eyeOpen = false;
                }else{
                    etLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivEye.setImageResource(R.mipmap.eye);
                    eyeOpen = true;
                }
            }
        });

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
