package com.pppanda.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pppanda.R;
import com.pppanda.cache.Cache;
import com.pppanda.request.AddRelationRequset;
import com.pppanda.request.GetFamilyUserIdRequest;
import com.pppanda.response.AddRelationResponse;
import com.pppanda.response.BaseResponse;
import com.pppanda.response.GetFamilyUserIdResponse;
import com.pppanda.util.Md5Util;
import com.pppanda.util.StatusBarUtils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/1.
 */

public class AddFamilyActivity extends Activity {
    private static final int MSG_GET_FAMILY_USERID_SUCCEED = 0X33;
    private static final int MSG_GET_FAMILY_USERID_FAILED = 0X34;
    private static final int MSG_ADD_RELATION_SUCCEED = 0X35;
    private static final int MSG_ADD_RELATION_FAILED = 0X36;

    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    Gson mGson = new Gson();
    ImageView ivBack;
    TextView tvTitle;
    EditText etName,etIdCard;
    LinearLayout selectRelation;
    TextView tvRelation;
    Button btnConfirm;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_GET_FAMILY_USERID_SUCCEED:
                    AddRelation();
                    break;
                case MSG_GET_FAMILY_USERID_FAILED:
                    String msgObj = (String)msg.obj;
                    Toast.makeText(AddFamilyActivity.this, msgObj, Toast.LENGTH_SHORT).show();
                    break;
                case MSG_ADD_RELATION_SUCCEED:
                    int action = msg.arg1;
                    if (action == 1){

                    }else {
                        Intent twoIntent = new Intent(AddFamilyActivity.this,UserShouleKnowActivity.class);
                        startActivity(twoIntent);
                    }
                    break;
                case MSG_ADD_RELATION_FAILED:
                    String msgObj1 = (String)msg.obj;
                    Toast.makeText(AddFamilyActivity.this,msgObj1,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏颜色
        StatusBarUtils.compat(this, Color.rgb(27,161,212));
        setContentView(R.layout.activity_addfamily);

        getRelationCode();

        initViews();

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

        etName = (EditText)findViewById(R.id.et_family_name);
        etIdCard = (EditText)findViewById(R.id.et_family_idcard);
        selectRelation = (LinearLayout)findViewById(R.id.select_layout);
        tvRelation = (TextView)findViewById(R.id.tv_family_select_relation);
        btnConfirm = (Button)findViewById(R.id.btn_confirm);

        selectRelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoise();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFamilyUserID();
            }
        });
    }

    private void showChoise(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AddFamilyActivity.this,R.style.MyDialog);
        //builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("选择关系");
        //    指定下拉列表的显示数据
        final String[] relations = {"父亲","母亲","爷爷","奶奶","外公","外婆","哥哥","姐姐","弟弟","妹妹","儿子",
                "女儿","孙子","孙女","外孙子","外孙女","老公","老婆"};
//        ArrayList<String> al = new ArrayList<>();
////        for (int i=0;i<Cache.mDictRelation.size();i++){
////            al.add(Cache.mDictRelation.valueAt(i));
////        }
////        Log.e("asdfdfasdfas","al=" + al);
////        int size = al.size();
//////        final String[] relations = (String[])al.toArray()(new String[size]);
////        final String[] relations = new String[size];
////        for (int i=0;i<size;i++){
////            relations[i] = (String) al.get(i);
////        }
////        Log.e("asdfgasdf","relations=" + relations);

        //    设置一个下拉的列表选择项
        builder.setItems(relations, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
//                Toast.makeText(AddFamilyActivity.this, "选择的城市为：" + cities[which], Toast.LENGTH_SHORT).show();
                tvRelation.setText(relations[which]);
            }
        });
        builder.show();
    }

    public void getRelationCode(){
        for (int i=0;i<Cache.mDictRelation.size();i++){
            if (Cache.mDictRelation.valueAt(i) == tvRelation.getText()){
                Cache.RelationCode = Cache.mDictRelation.keyAt(i);
            }
        }
    }

    public void getFamilyUserID(){
        Thread mGetID = new Thread(){
            @Override
            public void run() {
                String client_id = "zx2016";
                int req_time = (int)(System.currentTimeMillis()/1000);
                String sign_key = Md5Util.md5(req_time + "binwen");
                String card_id = etIdCard.getText().toString();
                GetFamilyUserIdRequest mGetFamilyUserIdRequest = new GetFamilyUserIdRequest(client_id,req_time,sign_key,card_id);

                String json = mGson.toJson(mGetFamilyUserIdRequest);
                Log.e("getFamilyUserID",json);

                String url = "http://api.pp-panda.cc:8080/v1/user/cardidget";

                OkHttpClient mOkHttpClient = new OkHttpClient();
                Request mRequest = new Request.Builder()
                        .url(url)
                        .post(RequestBody.create(JSON,json))
                        .build();

                Response mResponse = null;
                try{
                    mResponse = mOkHttpClient.newCall(mRequest).execute();
                }catch (IOException e){
                    e.printStackTrace();
                }

                String mResult = null;
                try {
                    mResult = mResponse.body().string();
                    Log.e("getFamilyUserID", "mResponse.body().string() = \n" + mResult);
                }catch (IOException e){
                    e.printStackTrace();
                }

                BaseResponse mBaseResponse = mGson.fromJson(mResult,BaseResponse.class);
                int code = mBaseResponse.getCode();
                String codeMsg = mBaseResponse.getCode_msg();
                if (code == 0){
                    GetFamilyUserIdResponse mGetFamilyUserIdResponse = mGson.fromJson(mResult,GetFamilyUserIdResponse.class);
                    Cache.familyUserID = mGetFamilyUserIdResponse.getBody().getUser_id();

                    Log.e("getFamilyUserID", "code_msg = " + codeMsg);
                    Log.e("getFamilyUserID", "code = " + code);
                    Log.e("getFamilyUserID", "user_id = " + Cache.familyUserID);

                    Message msg = new Message();
                    msg.what = MSG_GET_FAMILY_USERID_SUCCEED;
                    mHandler.sendMessage(msg);
                }else {
                    Message msg = new Message();
                    msg.what = MSG_GET_FAMILY_USERID_FAILED;
                    msg.obj = codeMsg;
                    mHandler.sendMessage(msg);
                }
            }
        };
        mGetID.start();
    }

    public void AddRelation(){
        Thread mAddRelation = new Thread(){
            @Override
            public void run() {
                String access_token = Cache.accessToken;
                int relation_user_id = Cache.familyUserID;
                int relation_code = Cache.RelationCode;
                Log.e("RelationCode","RelationCode = " + relation_code);
                AddRelationRequset mAddRelationRequest = new AddRelationRequset(access_token,relation_user_id,relation_code);

                String json = mGson.toJson(mAddRelationRequest);
                Log.e("AddRelation", json);

                String url = "http://api.pp-panda.cc:8080/v1/user/userrelationadd";

                OkHttpClient mOkHttpClient = new OkHttpClient();
                Request mRequest = new Request.Builder()
                        .url(url)
                        .post(RequestBody.create(JSON,json))
                        .build();

                Response mResponse = null;
                try{
                    mResponse = mOkHttpClient.newCall(mRequest).execute();
                }catch (IOException e){
                    e.printStackTrace();
                }

                String mResult = null;
                try {
                    mResult = mResponse.body().string();
                    Log.e("AddRelation", "mResponse.body().string() = \n" + mResult);
                }catch (IOException e){
                    e.printStackTrace();
                }

                BaseResponse mBaseResponse = mGson.fromJson(mResult,BaseResponse.class);
                int code = mBaseResponse.getCode();
                String codeMsg = mBaseResponse.getCode_msg();
                if (code == 0){
                    AddRelationResponse mAddRelationResponse = mGson.fromJson(mResult,AddRelationResponse.class);
                    Message msg = new Message();
                    msg.what = MSG_ADD_RELATION_SUCCEED;
                    msg.arg1 = mAddRelationResponse.getBody().getAction();
                    mHandler.sendMessage(msg);
                }else {
                    Message msg = new Message();
                    msg.what = MSG_ADD_RELATION_FAILED;
                    msg.obj = codeMsg;
                    mHandler.sendMessage(msg);
                }
            }
        };
        mAddRelation.start();
    }
}


























