package com.pppanda.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pppanda.R;
import com.pppanda.util.StatusBarUtils;

/**
 * Created by Administrator on 2017/6/1.
 */

public class AddFamilyActivity extends Activity {
    ImageView ivBack;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏颜色
        StatusBarUtils.compat(this, Color.rgb(27,161,212));
        setContentView(R.layout.activity_addfamily);


    }

    private void initViews(){
        ivBack = (ImageView)findViewById(R.id.back);
        tvTitle = (TextView)findViewById(R.id.tv_title);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFamilyActivity.this.finish();
            }
        });

        tvTitle.setText("添加家人");
    }
}
