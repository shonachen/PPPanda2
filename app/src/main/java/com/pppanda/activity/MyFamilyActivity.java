package com.pppanda.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.pppanda.R;
import com.pppanda.adapter.MyFamilyAdapter;
import com.pppanda.cache.Cache;
import com.pppanda.entity.BaseInfoEntity;
import com.pppanda.entity.MyFamilyInfoEntity;
import com.pppanda.entity.UserRelationEntity;
import com.pppanda.transformation.PicassoCircleTransform;
import com.pppanda.transformation.PicassoRoundTransform;
import com.pppanda.util.FSTextUtil;
import com.pppanda.util.PicassoUtil;
import com.pppanda.util.StatusBarUtils;
import com.squareup.picasso.MemoryPolicy;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/15.
 */

public class MyFamilyActivity extends Activity {
    ImageView ivBack,ivAddFamily;
    ListView lvMyFamily;
    MyFamilyAdapter mMyFamilyAdapter;
    ArrayList<MyFamilyInfoEntity> myFamilyList;
    ImageView ivMyFamilyHead;
    TextView tvMyFamilyName,tvMyFamilyRelation;
    //我的信息
    ImageView ivMyHead;
    TextView tvMyName,tvMyRelation;
    MyFamilyInfoEntity mMyFamilyInfoEntity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏颜色
        StatusBarUtils.compat(this, Color.rgb(27,161,212));
        setContentView(R.layout.activity_myfamily);

        initDatas();

        initViews();
    }

    private void initViews(){
//        //家人信息
        ivBack = (ImageView)findViewById(R.id.iv_mafamily_back);
        ivAddFamily = (ImageView)findViewById(R.id.iv_myfamily_add);
        ivMyFamilyHead = (ImageView)findViewById(R.id.iv_myfamily_head);
        tvMyFamilyName = (TextView)findViewById(R.id.tv_myself_nickname);
        tvMyFamilyRelation = (TextView)findViewById(R.id.tv_myfamily_relation);
        //我的信息
        ivMyHead = (ImageView)findViewById(R.id.iv_my_head);
        tvMyName = (TextView)findViewById(R.id.tv_my_nickname);
        tvMyRelation = (TextView)findViewById(R.id.tv_my_relation);

        lvMyFamily = (ListView)findViewById(R.id.lv_myfamily);
        mMyFamilyAdapter = new MyFamilyAdapter(MyFamilyActivity.this, myFamilyList);
        lvMyFamily.setAdapter(mMyFamilyAdapter);


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyFamilyActivity.this,MainActivity.class);
                intent.putExtra("fragid",3);//判定返回MainActivity的Fragment
                startActivity(intent);
            }
        });

        String path = mMyFamilyInfoEntity.getfHead();
        if(!FSTextUtil.isEmptyAndNull(path)){
            PicassoUtil.with(MyFamilyActivity.this).load(path)
                    .placeholder(R.mipmap.head)
                    .error(R.mipmap.head)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .transform(new PicassoCircleTransform())
                    .into(ivMyHead);
        }else{
            PicassoUtil.with(MyFamilyActivity.this).load(R.mipmap.head)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .transform(new PicassoCircleTransform()).into(ivMyHead);
        }
        tvMyName.setText(mMyFamilyInfoEntity.getfNickName());
        tvMyRelation.setText(mMyFamilyInfoEntity.getfRelation());



    }

    private void initDatas(){
        for(int i=0;i<Cache.mUserRelationEntitys.size();i++){
            UserRelationEntity mUserRelationEntity = Cache.mUserRelationEntitys.valueAt(i);
            int userID = Cache.mUserRelationEntitys.keyAt(i);
            BaseInfoEntity mBaseInfoEntity = Cache.mBaseInfoEntitys.get(userID);
            if (mBaseInfoEntity == null){
                Log.e("TAG","mBaseInfoEntity == null");
                continue;
            }

            String myHead = mBaseInfoEntity.getPhoto_url();
            String nickName = mBaseInfoEntity.getLike_name();
            String myRelation = Cache.mDictRelationEntity.get(mUserRelationEntity.getRelation_code()).toString();
//            mMyFamilyInfoEntity = new MyFamilyInfoEntity(userID,myHead,nickName,myRelation);

            mMyFamilyInfoEntity = new MyFamilyInfoEntity(userID,myHead,nickName,myRelation);


        }

        //我的数据
        int userID = Cache.userID;
        BaseInfoEntity mBaseInfoEntity = Cache.mBaseInfoEntitys.get(userID);
        String myHead = mBaseInfoEntity.getPhoto_url();
        String nickName = mBaseInfoEntity.getLike_name();
        String myRelation = "我";

        mMyFamilyInfoEntity = new MyFamilyInfoEntity(userID,myHead,nickName,myRelation);


    }

}













































