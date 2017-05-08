package com.pppanda.activity;

import android.app.Activity;
import android.os.Bundle;

import com.pppanda.R;
import com.pppanda.util.StatusBarUtils;

/**
 * Created by Administrator on 2017/5/3.
 */

public class ProtocolActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        StatusBarUtils.transparentStatusBar(this);
        setContentView(R.layout.activity_protocol);
    }
}
