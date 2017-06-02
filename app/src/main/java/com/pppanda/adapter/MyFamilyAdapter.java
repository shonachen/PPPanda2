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


    public MyFamilyAdapter(Context mContext, ArrayList<MyFamilyInfoEntity> mList) {
        this.mContext = mContext;
        if (mList == null){
            this.mList = new ArrayList<>();
        }else {
            this.mList = mList;
        }
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
        ViewHolder mViewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_myfamily,parent,false);

            mViewHolder = new ViewHolder();
            mViewHolder.fHead = (ImageView)convertView.findViewById(R.id.iv_myfamily_head);
            mViewHolder.fNickName = (TextView)convertView.findViewById(R.id.tv_myfamily_nickname);
            mViewHolder.fRelation = (TextView)convertView.findViewById(R.id.tv_myfamily_relation);
            mViewHolder.myFamily = (RelativeLayout)convertView.findViewById(R.id.layout_myfamily);

            convertView.setTag(mViewHolder);
        }else {
            mViewHolder = (ViewHolder)convertView.getTag();
        }
        final MyFamilyInfoEntity mMyFamilyInfoEntity = mList.get(position);
        if (FSTextUtil.isEmptyAndNull(mMyFamilyInfoEntity.getfHead())){
            PicassoUtil.with(mContext).load(R.mipmap.head).into(mViewHolder.fHead);
        }else {
            PicassoUtil.with(mContext).load(mMyFamilyInfoEntity.getfHead()).into(mViewHolder.fHead);
        }
//        mViewHolder.fHead.setImageResource(mMyFamilyInfoEntity.getfHead());
        mViewHolder.fNickName.setText(mMyFamilyInfoEntity.getfNickName());
        mViewHolder.fRelation.setText(mMyFamilyInfoEntity.getfRelation());

        mViewHolder.myFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,mMyFamilyInfoEntity.getfNickName(),Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }

    class ViewHolder{
        ImageView fHead;
        TextView fNickName;
        TextView fRelation;
        RelativeLayout myFamily;
    }
}
