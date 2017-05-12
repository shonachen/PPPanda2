package com.pppanda.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.pppanda.R;
import com.pppanda.adapter.MainFragmentAdapter;
import com.pppanda.fragment.HealthDataFragment;
import com.pppanda.fragment.HomeFragment;
import com.pppanda.fragment.IllManagementFragment;
import com.pppanda.fragment.MyselfFragment;
import com.pppanda.util.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RadioButton rbHome,rbHealthdata,rbIllmanagement,rbMyself;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏颜色
        StatusBarUtils.compat(this, Color.rgb(27,161,212));
        setContentView(R.layout.activity_main);
        mContext = this;

        initView();
    }

    private void initView(){
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        rbHome = (RadioButton)findViewById(R.id.rb_home);
        rbHealthdata = (RadioButton)findViewById(R.id.rb_healthdata);
        rbIllmanagement = (RadioButton)findViewById(R.id.rb_illmanagement);
        rbMyself =(RadioButton)findViewById(R.id.rb_myself);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        /**
                         * setCurrentItem第二个参数控制页面切换动画
                         * true:打开/false:关闭
                         */
                        viewPager.setCurrentItem(0, false);
                        Log.e("TAG","00000000000000000");
                        break;
                    case R.id.rb_healthdata:
                        viewPager.setCurrentItem(1, false);
                        Log.e("TAG","111111111111111111");
                        break;
                    case R.id.rb_illmanagement:
                        viewPager.setCurrentItem(2, false);
                        Log.e("TAG","222222222222222222");
                        break;
                    case R.id.rb_myself:
                        viewPager.setCurrentItem(3, false);
                        Log.e("TAG","3333333333333333333");
                        break;
                }
            }
        });

        /**
         * ViewPager部分
         */
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        HomeFragment mHomeFragment = new HomeFragment();
        mHomeFragment.setContext(MainActivity.this);
        HealthDataFragment mHealthDataFragment = new HealthDataFragment();
        IllManagementFragment mIllManagementFragment = new IllManagementFragment();
        MyselfFragment mMyselfFragment = new MyselfFragment();

        List<Fragment> alFragment = new ArrayList<>();
        alFragment.add(mHomeFragment);
        alFragment.add(mHealthDataFragment);
        alFragment.add(mIllManagementFragment);
        alFragment.add(mMyselfFragment);

        //ViewPager设置适配器
        viewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(),alFragment));
        //ViewPager显示第一个Fragment
        viewPager.setCurrentItem(0);
        //ViewPager页面切换监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioGroup.check(R.id.rb_home);
                        break;
                    case 1:
                        radioGroup.check(R.id.rb_healthdata);
                        break;
                    case 2:
                        radioGroup.check(R.id.rb_illmanagement);
                        break;
                    case 3:
                        radioGroup.check(R.id.rb_myself);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
