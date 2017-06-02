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
    TextView tvAboutUsTitle,tvTellPhone,tvServiceEmail,tvMarketingEmail,tvWeb,tvWeichat;
    LinearLayout tellPhone,serviceEmail,marketingEmail,web,weichat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏颜色
        StatusBarUtils.compat(this, Color.rgb(27,161,212));
        setContentView(R.layout.activity_about_us);

        initViews();
    }

    public void initViews(){
        ivBack = (ImageView)findViewById(R.id.back);
        tvAboutUsTitle = (TextView)findViewById(R.id.tv_title);
        tvTellPhone = (TextView)findViewById(R.id.tv_tellphone);
        tvServiceEmail = (TextView)findViewById(R.id.tv_service_email);
        tvMarketingEmail = (TextView)findViewById(R.id.tv_marketing_email);
        tvWeb = (TextView)findViewById(R.id.tv_web);
        tvWeichat = (TextView)findViewById(R.id.tv_weichat_email);

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
                    String serviceEmail = tvServiceEmail.getText().toString();
                    Intent serviceIntent = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:" + serviceEmail));
                    startActivity(serviceIntent);
                    break;
                case R.id.about_us_marketing:
                    String marketingEmail = tvMarketingEmail.getText().toString();
                    Intent marketingIntent = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:" + marketingEmail));
                    startActivity(marketingIntent);
                    break;
                case R.id.about_us_web:
                    String web = tvWeb.getText().toString();
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + web));
                    startActivity(webIntent);
                    break;
                case R.id.about_us_weichat:
                    Intent weichatIntent = getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
                    startActivity(weichatIntent);
                    break;
                default:
                    break;
            }
        }
    }
}
