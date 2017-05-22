package com.pppanda.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pppanda.R;
import com.pppanda.util.StatusBarUtils;

import okhttp3.HttpUrl;

/**
 * Created by Administrator on 2017/5/15.
 */

public class AboutUsActivity extends Activity {
    ImageView ivBack;
    TextView tvAboutUsTitle,tvTellPhone;
    LinearLayout tellPhone,serviceEmail,marketingEmail,web,weichat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏颜色
        StatusBarUtils.compat(this, Color.rgb(27,161,212));
        setContentView(R.layout.activity_about_us);

        initView();
    }

    public void initView(){
        ivBack = (ImageView)findViewById(R.id.back);
        tvAboutUsTitle = (TextView)findViewById(R.id.tv_title);
        tvTellPhone = (TextView)findViewById(R.id.tv_tellphone);
        tellPhone = (LinearLayout)findViewById(R.id.about_us_tellphone);
        serviceEmail = (LinearLayout)findViewById(R.id.about_us_service);
        marketingEmail = (LinearLayout) findViewById(R.id.about_us_marketing);
        web = (LinearLayout)findViewById(R.id.about_us_web);
        weichat = (LinearLayout)findViewById(R.id.about_us_weichat);

        tvAboutUsTitle.setText("关于PP熊猫");

        AboutUsListener listener = new AboutUsListener();
        ivBack.setOnClickListener(listener);
        tellPhone.setOnClickListener(listener);
        serviceEmail.setOnClickListener(listener);
        marketingEmail.setOnClickListener(listener);
        web.setOnClickListener(listener);
        weichat.setOnClickListener(listener);


    }

    class AboutUsListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    Intent intent = new Intent(AboutUsActivity.this,MainActivity.class);
                    intent.putExtra("fragid",3);//判定返回MainActivity的Fragment
                    startActivity(intent);
                    break;
                case R.id.about_us_tellphone:
                    String tellPhone = tvTellPhone.getText().toString();
                    Intent tellIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tellPhone));
                    startActivity(tellIntent);
                    break;
                case R.id.about_us_service:

                    break;
                case R.id.about_us_marketing:
                    break;
                case R.id.about_us_web:
                    break;
                case R.id.about_us_weichat:
                    break;
                default:
                    break;
            }
        }
    }
}
