package com.pppanda.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/15.
 */

public class MyFamilyActivity extends Activity {
    private static final String ACTION_DELETE_FAMILY = "ACTION_DELETE_FAMILY";
    private static final String ACTION_CONFIRM = "ACTION_CONFIRM";

    ImageView ivBack,ivAddFamily;
    ListView lvMyFamily,lvActivitAddFamily,lvPassiveAddFamily;
    MyFamilyAdapter mMyFamilyAdapter,ActivityAddAdapter,PassiveAddAdapter;
    ArrayList<MyFamilyInfoEntity> myFamilyList = new ArrayList<>();
    ArrayList<MyFamilyInfoEntity> activityAddList = new ArrayList<>();
    ArrayList<MyFamilyInfoEntity> passiveAddList = new ArrayList<>();
    //我的信息
    ImageView ivMyHead;
    TextView tvMyName,tvMyRelation;
    MyFamilyInfoEntity mMyInfoEntity;
    RelativeLayout myLayout;

    MyFamilyReceiver myFamilyReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏颜色
        StatusBarUtils.compat(this, Color.rgb(27,161,212));
        setContentView(R.layout.activity_myfamily);

        initDatas();

        initViews();

        myFamilyReceiver = new MyFamilyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_DELETE_FAMILY);
        intentFilter.addAction(ACTION_CONFIRM);
        registerReceiver(myFamilyReceiver, intentFilter);
    }


    protected void onDestroy(){
        super.onDestroy();

        if(myFamilyReceiver != null){
            unregisterReceiver(myFamilyReceiver);
        }
    }

    private void initViews(){
        ivBack = (ImageView)findViewById(R.id.iv_mafamily_back);
        ivAddFamily = (ImageView)findViewById(R.id.iv_myfamily_add);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFamilyActivity.this.finish();
            }
        });

        ivAddFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyFamilyActivity.this,AddFamilyActivity.class);
                startActivity(intent);
            }
        });

        //关系已确认的家人列表
        lvMyFamily = (ListView)findViewById(R.id.lv_myfamily);
        mMyFamilyAdapter = new MyFamilyAdapter(MyFamilyActivity.this, myFamilyList,true);
        lvMyFamily.setAdapter(mMyFamilyAdapter);

        //等待家人确认的家人列表
        lvActivitAddFamily = (ListView)findViewById(R.id.lv_active_addfamily);
        ActivityAddAdapter = new MyFamilyAdapter(MyFamilyActivity.this, activityAddList,false);
        lvActivitAddFamily.setAdapter(ActivityAddAdapter);

        //等待自己确认的家人列表
        lvPassiveAddFamily = (ListView)findViewById(R.id.lv_passive_addfamily);
        PassiveAddAdapter = new MyFamilyAdapter(MyFamilyActivity.this, passiveAddList,false);
        lvPassiveAddFamily.setAdapter(PassiveAddAdapter);

        //我的信息
        ivMyHead = (ImageView)findViewById(R.id.iv_my_head);
        tvMyName = (TextView)findViewById(R.id.tv_my_nickname);
        tvMyRelation = (TextView)findViewById(R.id.tv_my_relation);
        myLayout = (RelativeLayout)findViewById(R.id.my_info);

        //我的数据
        String path1 = mMyInfoEntity.getfHead();
        if(!FSTextUtil.isEmptyAndNull(path1)){
            PicassoUtil.with(MyFamilyActivity.this).load(path1)
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
        tvMyName.setText(mMyInfoEntity.getfNickName());
        tvMyRelation.setText(mMyInfoEntity.getfRelation());

        myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MyFamilyActivity.this,mMyInfoEntity.getfNickName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyFamilyActivity.this, PersonalInfoActivity.class);
                intent.putExtra("isComplete", true);
                intent.putExtra("ENTITY", mMyInfoEntity);
                startActivity(intent);
            }
        });

    }

    private void initDatas(){
        myFamilyList.clear();
        activityAddList.clear();
        passiveAddList.clear();

        //家人数据
        for (int i=0;i<Cache.mUserRelationEntitys.size();i++){
            UserRelationEntity mUserRelationEntity = Cache.mUserRelationEntitys.valueAt(i);
            int FamilyUserID = Cache.mUserRelationEntitys.keyAt(i);
            BaseInfoEntity mFamily = Cache.mBaseInfoEntitys.get(FamilyUserID);
            if(mFamily == null){
                Log.e("TAG", "mFamily == null");
                continue;
            }

            String fMyHead = mFamily.getPhoto_url();
            String fNickName = mFamily.getLike_name();
            String fMyRelation = Cache.mDictRelationEntity.get(mUserRelationEntity.getRelation_code()).getRelation_desc();
            boolean isActivity = (mUserRelationEntity.getUser_id() == Cache.userID);
            MyFamilyInfoEntity mMyFamilyInfoEntity = new MyFamilyInfoEntity(FamilyUserID,fMyHead,fNickName,fMyRelation,isActivity);
            Log.e("is_confirm","is_confirm=" + mUserRelationEntity.getIs_confirm());

            if(mUserRelationEntity.getIs_confirm() == 2){
                myFamilyList.add(mMyFamilyInfoEntity);
            }else{
                if (mMyFamilyInfoEntity.isActivity()){
                    activityAddList.add(mMyFamilyInfoEntity);
                }else {
                    passiveAddList.add(mMyFamilyInfoEntity);
                }
            }

        }


        //我的数据
        int userID = Cache.userID;
        BaseInfoEntity mBaseInfoEntity = Cache.mBaseInfoEntitys.get(userID);
        String myHead = mBaseInfoEntity.getPhoto_url();
        String nickName = mBaseInfoEntity.getLike_name();
        String myRelation = "我";

        mMyInfoEntity = new MyFamilyInfoEntity(userID,myHead,nickName,myRelation,true);


    }


    class MyFamilyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action){
                case ACTION_DELETE_FAMILY:
//                    boolean isComplete = intent.getBooleanExtra("isComplete",true);
//                    boolean isActivity = intent.getBooleanExtra("isActivity", true);
                    boolean isComplete = intent.getExtras().getBoolean("isComplete");
                    boolean isActivity = intent.getExtras().getBoolean("isActivity");
                    int userID = intent.getExtras().getInt("userID");
                    if (isComplete){            //删除已确认关系的家人
                        for(int i=0;i<myFamilyList.size();i++){
                            if(myFamilyList.get(i).getUserID() == userID){
                                myFamilyList.remove(i);
                            }
                        }
                        mMyFamilyAdapter.setList(myFamilyList);
                        mMyFamilyAdapter.notifyDataSetChanged();

                    }else {                    //删除未确认关系的家人
                        if (isActivity){
                            for(int i=0;i<activityAddList.size();i++){
                                if(activityAddList.get(i).getUserID() == userID){
                                    activityAddList.remove(i);
                                }
                            }
                            ActivityAddAdapter.setList(activityAddList);
                            ActivityAddAdapter.notifyDataSetChanged();
                        }
                    }
                    break;
                case ACTION_CONFIRM:
                    int disagreeUserID = intent.getExtras().getInt("userID1");
                    int confirmAction = intent.getExtras().getInt("ACTION");
                    MyFamilyInfoEntity mMyFamilyInfoEntity = (MyFamilyInfoEntity)intent.getExtras().getSerializable("MyFamilyInfoEntity");
                    if (confirmAction == 2){            //同意家人申请
                        for(int i=0;i<passiveAddList.size();i++){
                            if(passiveAddList.get(i).getUserID() == disagreeUserID){
                                Log.e("userID1", passiveAddList.get(i).getUserID()+"");
                                passiveAddList.remove(i);                  //passiveAddList中移除该家人
                            }
                        }
                        PassiveAddAdapter.setList(passiveAddList);
                        PassiveAddAdapter.notifyDataSetChanged();

                        myFamilyList.add(mMyFamilyInfoEntity);             //myFamilyList中添加该家人
                        mMyFamilyAdapter.setList(myFamilyList);
                        mMyFamilyAdapter.notifyDataSetChanged();

//

                    }else {                                //拒绝家人申请
                        for(int i=0;i<passiveAddList.size();i++){
                            if(passiveAddList.get(i).getUserID() == disagreeUserID){
                                Log.e("userID1", passiveAddList.get(i).getUserID()+"");
                                passiveAddList.remove(i);
                            }
                        }
                        PassiveAddAdapter.setList(passiveAddList);
                        PassiveAddAdapter.notifyDataSetChanged();
                    }
                    break;
                default:
                    break;

            }
        }
    }

}













































