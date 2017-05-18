package com.pppanda.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pppanda.R;
import com.pppanda.util.StatusBarUtils;

/**
 * Created by Administrator on 2017/5/15.
 */

public class AboutUsActivity extends Activity {
    ImageView ivBack;
    TextView tvAboutUsTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏颜色
        StatusBarUtils.compat(this, Color.rgb(27,161,212));
        setContentView(R.layout.activity_about_us);

        ivBack = (ImageView)findViewById(R.id.back);
        tvAboutUsTitle = (TextView)findViewById(R.id.tv_title);

        tvAboutUsTitle.setText("关于PP熊猫");

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutUsActivity.this,MainActivity.class);
                intent.putExtra("fragid",3);//判定返回MainActivity的Fragment
                startActivity(intent);
            }
        });

    }
}
