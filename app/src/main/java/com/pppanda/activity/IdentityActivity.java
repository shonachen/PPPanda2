package com.pppanda.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.pppanda.R;
import com.pppanda.util.StatusBarUtils;

/**
 * Created by Administrator on 2017/5/8.
 */

public class IdentityActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏颜色
        StatusBarUtils.compat(this, Color.rgb(27,161,212));
        setContentView(R.layout.activity_identity);
    }
}
