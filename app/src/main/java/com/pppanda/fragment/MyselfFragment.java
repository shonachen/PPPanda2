package com.pppanda.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.widget.Toast;

import com.pppanda.R;
import com.pppanda.activity.AboutUsActivity;
import com.pppanda.activity.IdentifyActivity;
import com.pppanda.activity.ModifyPasswordActivity;
import com.pppanda.activity.MyFamilyActivity;
import com.pppanda.activity.MyPhoneActivity;

import com.pppanda.activity.PersonalInfoActivity;
import com.pppanda.cache.Cache;


/**
 * Created by Administrator on 2017/5/4.
 */

public class MyselfFragment extends Fragment {
    Context mContext;
    View view;
    int card_status;

    RelativeLayout myInfo;
    RelativeLayout myIdentify;
    RelativeLayout myFamily;
    RelativeLayout myPhone;
    RelativeLayout myPassword;
    RelativeLayout myAboutUs;

    ImageView ivMyHead;
    TextView tvMyNickName;
    TextView tvMyName;
    TextView tvIdentifyRemark;
    TextView tvPhoneRemark;

//    public void setContext(Context context) {
//        this.mContext = mContext;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_myself,null);
        card_status = Cache.mBaseInfoEntitys.get(Cache.userID).getCard_status();

        myInfo = (RelativeLayout)view.findViewById(R.id.my_info);
        myIdentify = (RelativeLayout)view.findViewById(R.id.my_identify);
        myFamily = (RelativeLayout)view.findViewById(R.id.my_family);
        myPhone = (RelativeLayout)view.findViewById(R.id.my_phone);
        myPassword = (RelativeLayout)view.findViewById(R.id.my_password);
        myAboutUs = (RelativeLayout)view.findViewById(R.id.my_aboutus);

        ivMyHead = (ImageView)view.findViewById(R.id.iv_myself_head);
        tvMyNickName =(TextView)view.findViewById(R.id.tv_myself_nickname);
        tvMyName = (TextView)view.findViewById(R.id.tv_myself_name);
        tvIdentifyRemark = (TextView)view.findViewById(R.id.tv_myself_identify_remark);
        tvPhoneRemark = (TextView)view.findViewById(R.id.tv_myself_phone_remark);

//        String path = mMyFamilyInfoEntity.getfHead();
//        if(!FSTextUtil.isEmptyAndNull(path)){
//            PicassoUtil.with(MyFamilyActivity.this).load(path)
//                    .placeholder(R.mipmap.head)
//                    .error(R.mipmap.head)
//                    .memoryPolicy(MemoryPolicy.NO_CACHE)
//                    .transform(new PicassoCircleTransform())
//                    .into(ivMyHead);
//        }else{
//            PicassoUtil.with(MyFamilyActivity.this).load(R.mipmap.head)
//                    .memoryPolicy(MemoryPolicy.NO_CACHE)
//                    .transform(new PicassoCircleTransform()).into(ivMyHead);
//        }
        tvPhoneRemark.setText(Cache.mBaseInfoEntitys.get(Cache.userID).getMob_phone());

        if (card_status != 3){
            tvMyNickName.setText(Cache.mBaseInfoEntitys.get(Cache.userID).getMob_phone());
            tvMyName.setText("游客");
            tvIdentifyRemark.setText("未认证");
        }else {
            tvMyNickName.setText(Cache.mBaseInfoEntitys.get(Cache.userID).getLike_name());
            tvMyName.setText(Cache.mBaseInfoEntitys.get(Cache.userID).getName());
            tvIdentifyRemark.setText("已认证");
        }

        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //个人信息点击事件
        myInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PersonalInfoActivity.class);
                startActivity(intent);
            }
        });

        //身份认证点击事件
        myIdentify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (card_status != 3){
                    Intent intent = new Intent(mContext, IdentifyActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(mContext,"您已经认证过了" ,Toast.LENGTH_SHORT).show();
                }
            }
        });

        //我的家人点击事件
        myFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (card_status != 3){
                    Toast.makeText(mContext,"请先进行身份认证",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(mContext, MyFamilyActivity.class);
                    startActivity(intent);
                }
            }
        });

        //我的手机点击事件
        myPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyPhoneActivity.class);
                startActivity(intent);
            }
        });

        //修改密码点击时间
        myPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ModifyPasswordActivity.class);
                startActivity(intent);
            }
        });

        //关于我们点击事件
        myAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AboutUsActivity.class);
                startActivity(intent);
            }
        });

    }

}