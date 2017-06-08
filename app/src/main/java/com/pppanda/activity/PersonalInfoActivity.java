package com.pppanda.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pppanda.R;
import com.pppanda.cache.Cache;
import com.pppanda.entity.MyFamilyInfoEntity;
import com.pppanda.request.DeleteFamilyRequest;
import com.pppanda.response.BaseResponse;
import com.pppanda.response.DeleteFamilyResponse;
import com.pppanda.transformation.PicassoCircleTransform;
import com.pppanda.util.FSTextUtil;
import com.pppanda.util.PicassoUtil;
import com.pppanda.util.StatusBarUtils;
import com.squareup.picasso.MemoryPolicy;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/8.
 */

public class PersonalInfoActivity extends Activity {
    private static final int MSG_DELETE_FAMILY_SUCCEED = 0X25;
    private static final int MSG_DELETE_FAMILY_FAILED = 0X26;
    private static final int MSG_AGREE_SUCCEED = 0X27;
    private static final int MSG_AGREE_FAILED = 0X28;
    private static final int MSG_DISAGREE_SUCCEED = 0X29;
    private static final int MSG_DISAGREE_FAILED = 0X30;
    private static final String ACTION_DELETE_FAMILY = "ACTION_DELETE_FAMILY";

    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    Gson mGson = new Gson();

    ImageView ivBack;
    TextView tvTitle;
    ImageView ivHead;
    TextView tvNickName,tvRelation;
    Button btnCheckData,btnAgree,btnDelete,btnDisagree;
    MyFamilyInfoEntity mMyFamilyInfoEntity;
    int position;
    boolean isCompelte = true;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_DELETE_FAMILY_SUCCEED:
                    Toast.makeText(PersonalInfoActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                    //删除缓存
                    Cache.clearDatasByUserID(mMyFamilyInfoEntity.getUserID());
                    Intent dInrent = new Intent(ACTION_DELETE_FAMILY);
                    dInrent.putExtra("isCompelte", isCompelte);
//                    dInrent.putExtra("isActivity",mMyFamilyInfoEntity.isActivity());
                    dInrent.putExtra("userID", mMyFamilyInfoEntity.getUserID());
                    dInrent.putExtra("POSITION", position);
                    sendBroadcast(dInrent);


                    PersonalInfoActivity.this.finish();
                    break;
                case MSG_DELETE_FAMILY_FAILED:
                    Toast.makeText(PersonalInfoActivity.this,"操作失败",Toast.LENGTH_SHORT).show();
                    break;
                case MSG_AGREE_SUCCEED:
                    break;
                case MSG_AGREE_FAILED:
                    break;
                case MSG_DISAGREE_SUCCEED:
                    break;
                case MSG_DISAGREE_FAILED:
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏颜色
        StatusBarUtils.compat(this,Color.rgb(27,161,212));
        setContentView(R.layout.activity_personalinfo);

        initDatas();
        initViews();
    }

    private void initDatas(){
        Intent intent = getIntent();
        isCompelte = intent.getBooleanExtra("isComplete", false);
        mMyFamilyInfoEntity = (MyFamilyInfoEntity)intent.getSerializableExtra("ENTITY");
        position = intent.getExtras().getInt("POSITION");
        Log.e("TAG", mMyFamilyInfoEntity.toString());
    }

    private void initViews(){
        //标题栏
        ivBack = (ImageView)findViewById(R.id.back);
        tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText("个人信息");

        ivHead = (ImageView) findViewById(R.id.iv_personal_head);
        tvNickName = (TextView)findViewById(R.id.tv_personal_nickname);
        tvRelation = (TextView)findViewById(R.id.tv_personal_relation);
        btnCheckData = (Button)findViewById(R.id.btn_checkdata);
        btnDelete = (Button)findViewById(R.id.btn_delete);
        btnAgree = (Button)findViewById(R.id.btn_agree);
        btnDisagree = (Button)findViewById(R.id.btn_disagree);

        if (isCompelte){
            tvRelation.setText(mMyFamilyInfoEntity.getfRelation());
            if (mMyFamilyInfoEntity.getUserID() == Cache.userID){
                btnDelete.setVisibility(View.GONE);
                btnAgree.setVisibility(View.GONE);
                btnDisagree.setVisibility(View.GONE);
            }else {
                btnAgree.setVisibility(View.GONE);
                btnDisagree.setVisibility(View.GONE);
            }
        }else {
            if (mMyFamilyInfoEntity.isActivity()){
                btnCheckData.setVisibility(View.GONE);
                btnAgree.setVisibility(View.GONE);
                btnDisagree.setVisibility(View.GONE);
                tvRelation.setText(mMyFamilyInfoEntity.getfRelation());
            }else {
                btnCheckData.setVisibility(View.GONE);
                btnDelete.setVisibility(View.GONE);
                tvRelation.setText("对方请求添加您为：" + mMyFamilyInfoEntity.getfRelation());
            }
        }
        tvNickName.setText(mMyFamilyInfoEntity.getfNickName());
        String path = mMyFamilyInfoEntity.getfHead();
        if(!FSTextUtil.isEmptyAndNull(path)){
            PicassoUtil.with(PersonalInfoActivity.this).load(path)
                    .placeholder(R.mipmap.head)
                    .error(R.mipmap.head)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .transform(new PicassoCircleTransform())
                    .into(ivHead);
        }else{
            PicassoUtil.with(PersonalInfoActivity.this).load(R.mipmap.head)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .transform(new PicassoCircleTransform()).into(ivHead);
        }


        PersonalInfoListener listener = new PersonalInfoListener();
        ivBack.setOnClickListener(listener);
        btnCheckData.setOnClickListener(listener);
        btnDelete.setOnClickListener(listener);
        btnAgree.setOnClickListener(listener);
        btnDisagree.setOnClickListener(listener);
    }

    class PersonalInfoListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    PersonalInfoActivity.this.finish();
                    break;
                case R.id.btn_checkdata:
                    Toast.makeText(PersonalInfoActivity.this,mMyFamilyInfoEntity.getfNickName(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_delete:
                    DeleteFamily();
                    break;
                case R.id.btn_agree:
                    break;
                case R.id.btn_disagree:
                    break;
                default:
                    break;
            }
        }
    }

    private void DeleteFamily(){
        Thread mDelete = new Thread(){
            @Override
            public void run() {
                String access_token = Cache.accessToken;
                int relation_user_id = mMyFamilyInfoEntity.getUserID();
                DeleteFamilyRequest mDeleteFamilyRequest = new DeleteFamilyRequest(access_token,relation_user_id);
                String json = mGson.toJson(mDeleteFamilyRequest);
                Log.e("DeleteFamily", json);

                String url = "http://api.pp-panda.cc:8080/v1/user/userrelationdel";
                OkHttpClient mOkHttpClient = new OkHttpClient();
                Request mRequest = new Request.Builder()
                        .url(url)
                        .post(RequestBody.create(JSON, json))
                        .build();

                Response mResponse = null;
                try {
                    mResponse = mOkHttpClient.newCall(mRequest).execute();
                }catch (IOException e){
                    e.printStackTrace();
                }

                String mResult = null;
                try {
                    mResult = mResponse.body().string();
                    Log.e("DeleteFamily", "mResponse.body().string() = \n" + mResult);
                }catch (IOException e){
                    e.printStackTrace();
                }

                BaseResponse mBaseResponse = mGson.fromJson(mResult,BaseResponse.class);
                int code = mBaseResponse.getCode();
                String codeMsg = mBaseResponse.getCode_msg();
                if (code == 0){
                    DeleteFamilyResponse mDeleteFamilyResponse = mGson.fromJson(mResult,DeleteFamilyResponse.class);
                    String body = mDeleteFamilyResponse.getBody().toString();
                    Log.e("login", "code = " + code);
                    Log.e("login", "code_msg = " + codeMsg);
                    Log.e("login", "body = " + body);

                    Message msg = new Message();
                    msg.what = MSG_DELETE_FAMILY_SUCCEED;
                    mHandler.sendMessage(msg);
                }else {
                    Message msg = new Message();
                    msg.what = MSG_DELETE_FAMILY_FAILED;
                    mHandler.sendMessage(msg);
                }
            }
        };
        mDelete.start();
    }
}






























