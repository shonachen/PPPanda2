package com.pppanda.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pppanda.R;
import com.pppanda.entity.HomeListInfoEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/11.
 */

public class HomeListInfoAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<HomeListInfoEntity> mList;

    public HomeListInfoAdapter(Context mContext, ArrayList<HomeListInfoEntity> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount(){
        return mList.size();
    }

    @Override
    public HomeListInfoEntity getItem(int position){
        return mList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (convertView==null){
            if(mContext == null){
                Log.e("TAG", "mContex == null");
            }
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_home_information,parent,false);

            mViewHolder = new ViewHolder();
            mViewHolder.hInfoHead = (ImageView)convertView.findViewById(R.id.iv_home_info_head);
            mViewHolder.hInfoName = (TextView)convertView.findViewById(R.id.tv_home_info_name);
            mViewHolder.hInfoContent = (TextView)convertView.findViewById(R.id.tv_home_info_content);
            mViewHolder.hInfoYear = (TextView)convertView.findViewById(R.id.tv_home_info_year);
            mViewHolder.hInfo = (RelativeLayout)convertView.findViewById(R.id.home_info);

            convertView.setTag(mViewHolder);
        }else {
            mViewHolder = (ViewHolder)convertView.getTag();
        }

        final HomeListInfoEntity mHomeListInfoEntity = mList.get(position);
        mViewHolder.hInfoHead.setImageResource(mHomeListInfoEntity.gethInfoHead());
        mViewHolder.hInfoName.setText(mHomeListInfoEntity.gethInfoName());
        mViewHolder.hInfoContent.setText(mHomeListInfoEntity.gethInfoContent());
        mViewHolder.hInfoYear.setText(mHomeListInfoEntity.gethInfoYear());

        mViewHolder.hInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,mHomeListInfoEntity.gethInfoName(),Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    class ViewHolder{
        ImageView hInfoHead;
        TextView hInfoName;
        TextView hInfoContent;
        TextView hInfoYear;
        RelativeLayout hInfo;
    }
}
