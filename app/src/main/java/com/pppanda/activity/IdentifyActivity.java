package com.pppanda.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pppanda.R;
import com.pppanda.adapter.IdentifyFragmentAdapter;
import com.pppanda.adapter.MainFragmentAdapter;

import com.pppanda.fragment.MyselfFragment;
import com.pppanda.fragment.UploadCredentialsFragment;
import com.pppanda.fragment.WriteInfoFragment;
import com.pppanda.util.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/5/15.
 */

public class IdentifyActivity extends FragmentActivity {
    Context mContext;
    private ViewPager viewPager;
    RelativeLayout identifyWriteInfo,identifyUpload;
    LinearLayout llidentify;
    TextView tvTitle,tvinfoNumber,tvuploadNumber;
    TextView tvinfoHint,tvuploadHint;
    ImageView ivinfoLine,ivuploadLine,ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏颜色
        StatusBarUtils.compat(this, Color.rgb(27,161,212));
        setContentView(R.layout.activity_identify);
        mContext = this;

        initView();
        selectFragment(viewPager);
    }

    public void selectFragment(View view){
        switch (view.getId()){
            case R.id.identify_write_info:
                tvinfoNumber.setBackgroundResource(R.drawable.identify_number_check);
                tvinfoHint.setTextColor(getResources().getColor(R.color.identify_hint_check));
                ivinfoLine.setVisibility(View.VISIBLE);
                break;

            case R.id.identify_upload_credentials:
                tvuploadNumber.setBackgroundResource(R.drawable.identify_number_check);
                tvuploadHint.setTextColor(getResources().getColor(R.color.identify_hint_check));
                ivuploadLine.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    public void initView(){
        identifyWriteInfo = (RelativeLayout)findViewById(R.id.identify_write_info);
        identifyUpload = (RelativeLayout)findViewById(R.id.identify_upload_credentials);
        llidentify = (LinearLayout)findViewById(R.id.ll_identify);
        tvTitle = (TextView)findViewById(R.id.tv_title);
        tvinfoNumber = (TextView)findViewById(R.id.identify_write_info_number);
        tvuploadNumber = (TextView)findViewById(R.id.identify_upload_credentials_number);
        tvinfoHint = (TextView)findViewById(R.id.identify_write_info_hint);
        tvuploadHint = (TextView)findViewById(R.id.identify_upload_credentials_hint);
        ivinfoLine = (ImageView)findViewById(R.id.iv_write_line);
        ivuploadLine = (ImageView)findViewById(R.id.iv_upload_line);
        ivBack = (ImageView)findViewById(R.id.back);

        tvinfoNumber.setBackgroundResource(R.drawable.identify_number_check);
        tvinfoHint.setTextColor(getResources().getColor(R.color.identify_hint_check));
        ivinfoLine.setVisibility(View.VISIBLE);

        tvuploadNumber.setBackgroundResource(R.drawable.identify_number_nocheck);
        tvuploadHint.setTextColor(getResources().getColor(R.color.identify_hint_nocheck));
        ivuploadLine.setVisibility(View.INVISIBLE);


        tvTitle.setText("身份认证");

        viewPager = (ViewPager)findViewById(R.id.identify_viewPager);

        WriteInfoFragment mWriteInfoFragment = new WriteInfoFragment();
        UploadCredentialsFragment mUploadCredentialsFragment = new UploadCredentialsFragment();

        List<Fragment> alFragment = new ArrayList<>();
        alFragment.add(mWriteInfoFragment);
        alFragment.add(mUploadCredentialsFragment);

        viewPager.setAdapter(new IdentifyFragmentAdapter(getSupportFragmentManager(),alFragment));
        viewPager.setCurrentItem(0);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IdentifyActivity.this, MainActivity.class);
                intent.putExtra("fragid",3);//判定返回MainActivity的Fragment

                startActivity(intent);
            }
        });

    }

}
