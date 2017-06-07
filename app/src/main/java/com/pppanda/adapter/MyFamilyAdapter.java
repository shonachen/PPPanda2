package com.pppanda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pppanda.R;
import com.pppanda.entity.MyFamilyInfoEntity;
import com.pppanda.util.FSTextUtil;
import com.pppanda.util.PicassoUtil;
import com.pppanda.util.XCRoundImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/1.
 */

public class MyFamilyAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<MyFamilyInfoEntity> mList;
    boolean isComplete;


    public MyFamilyAdapter(Context mContext, ArrayList<MyFamilyInfoEntity> mList,boolean isComplete) {
        this.mContext = mContext;
        if (mList == null){
            this.mList = new ArrayList<>();
        }else {
            this.mList = mList;
        }
        this.isComplete = isComplete;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (isComplete){
            ViewHolderComplete mViewHolderComplete = null;
            if (convertView == null){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_myfamily,parent,false);

                mViewHolderComplete = new ViewHolderComplete();
                mViewHolderComplete.fHead = (ImageView)convertView.findViewById(R.id.iv_myfamily_head);
                mViewHolderComplete.fNickName = (TextView)convertView.findViewById(R.id.tv_myfamily_nickname);
                mViewHolderComplete.fRelation = (TextView)convertView.findViewById(R.id.tv_myfamily_relation);
                mViewHolderComplete.myFamily = (RelativeLayout)convertView.findViewById(R.id.layout_myfamily);

                convertView.setTag(mViewHolderComplete);
            }else {
                mViewHolderComplete = (ViewHolderComplete)convertView.getTag();
            }
            final MyFamilyInfoEntity mMyFamilyInfoEntity = mList.get(position);
            if (FSTextUtil.isEmptyAndNull(mMyFamilyInfoEntity.getfHead())){
                PicassoUtil.with(mContext).load(R.mipmap.head).into(mViewHolderComplete.fHead);
            }else {
                PicassoUtil.with(mContext).load(mMyFamilyInfoEntity.getfHead()).into(mViewHolderComplete.fHead);
            }
//        mViewHolder.fHead.setImageResource(mMyFamilyInfoEntity.getfHead());
            mViewHolderComplete.fNickName.setText(mMyFamilyInfoEntity.getfNickName());
            mViewHolderComplete.fRelation.setText(mMyFamilyInfoEntity.getfRelation());

            mViewHolderComplete.myFamily.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,mMyFamilyInfoEntity.getfNickName(),Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            ViewHolderNoComplete mViewHolderNoComplete = null;
            if (convertView == null){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_myfamily_no,parent,false);

                mViewHolderNoComplete = new ViewHolderNoComplete();
                mViewHolderNoComplete.fHead1 = (ImageView)convertView.findViewById(R.id.iv_myfamily_head);
                mViewHolderNoComplete.fNickName1 = (TextView)convertView.findViewById(R.id.tv_myfamily_nickname);
                mViewHolderNoComplete.fRelation1 = (TextView)convertView.findViewById(R.id.tv_myfamily_relation);
                mViewHolderNoComplete.tvConfirm1 = (TextView)convertView.findViewById(R.id.tv_confirm);
                mViewHolderNoComplete.myFamily1 = (RelativeLayout)convertView.findViewById(R.id.layout_myfamily_no);

                convertView.setTag(mViewHolderNoComplete);
            }else {
                mViewHolderNoComplete = (ViewHolderNoComplete)convertView.getTag();
            }

            final MyFamilyInfoEntity mMyFamilyInfoEntity = mList.get(position);
            if (FSTextUtil.isEmptyAndNull(mMyFamilyInfoEntity.getfHead())){
                PicassoUtil.with(mContext).load(R.mipmap.head).into(mViewHolderNoComplete.fHead1);
            }else {
                PicassoUtil.with(mContext).load(mMyFamilyInfoEntity.getfHead()).into(mViewHolderNoComplete.fHead1);
            }
            mViewHolderNoComplete.fNickName1.setText(mMyFamilyInfoEntity.getfNickName());
            if (mMyFamilyInfoEntity.isActivity()){
                mViewHolderNoComplete.fRelation1.setText(mMyFamilyInfoEntity.getfRelation());
                mViewHolderNoComplete.tvConfirm1.setText("等待对方确认");
            }else {
                mViewHolderNoComplete.fRelation1.setText("对方请求添加您为：" + mMyFamilyInfoEntity.getfRelation());
                mViewHolderNoComplete.tvConfirm1.setText("家人绑定申请");
            }

            mViewHolderNoComplete.myFamily1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,mMyFamilyInfoEntity.getfNickName(),Toast.LENGTH_SHORT).show();
                }
            });

        }

        return convertView;
    }

    class ViewHolderComplete{
        ImageView fHead;
        TextView fNickName;
        TextView fRelation;
        RelativeLayout myFamily;
    }

    class ViewHolderNoComplete{
        ImageView fHead1;
        TextView fNickName1;
        TextView fRelation1;
        TextView tvConfirm1;
        RelativeLayout myFamily1;
    }

    public void setList(ArrayList<MyFamilyInfoEntity> mList){
        this.mList = mList;
    }
}
