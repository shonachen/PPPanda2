package com.pppanda.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pppanda.R;
import com.pppanda.adapter.HomeListInfoAdapter;
import com.pppanda.cache.Cache;
import com.pppanda.entity.HomeListInfoEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/4.
 */

//public class HomeFragment extends Fragment {
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_home, container, false);
//    }
//}
public class HomeFragment extends Fragment {
    Context mContext;
    View view;
    ImageView homeAddFamily;
    RelativeLayout homeData;
    TextView homeScore;
    int card_status;

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }
//
//    public HomeFragment(){
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
//        startActivity(new Intent(getActivity(), LoginActivity.class));//跳转页面
//
//         Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, null);
        homeAddFamily = (ImageView)view.findViewById(R.id.iv_home_addfamily);
        homeScore = (TextView)view.findViewById(R.id.tv_home_score);
        card_status = Cache.mBaseInfoEntitys.get(Cache.userID).getCard_status();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置时间显示格式

        if (card_status != 3){
            int hInfoHead = R.mipmap.icon_team;
            String hInfoName = "PP熊猫团队";
            String hInfoContent = "欢迎使用PP熊猫";
            String hInfoYear = sdf.format(new java.util.Date());//获取系统时间

            final ArrayList<HomeListInfoEntity> mList = new ArrayList<>();
            HomeListInfoEntity mHomeListInfoEntity = new HomeListInfoEntity(hInfoHead,hInfoName,hInfoContent,hInfoYear);
            mList.add(mHomeListInfoEntity);

            ListView lvHomeInfo = (ListView)view.findViewById(R.id.lv_home_information);
            HomeListInfoAdapter mHomeListInfoAdapter = new HomeListInfoAdapter(mContext, mList);
            lvHomeInfo.setAdapter(mHomeListInfoAdapter);

//            return view;
        }else {
            homeData = (RelativeLayout)view.findViewById(R.id.home_data);
            homeData.setVisibility(View.VISIBLE);
            homeAddFamily.setVisibility(View.VISIBLE);

            if (Cache.mHccDataRankEntitys.get(Cache.userID) == null){
                homeScore.setText("--");
            }else {
                homeScore.setText((int) Cache.mHccDataRankEntitys.get(Cache.userID).getScore() + "");
            }


            int[] hInfoHead = {R.mipmap.icon_team,R.mipmap.icon_famliy,R.mipmap.icon_manage,R.mipmap.icon_info};
            String[] hInfoName = this.getResources().getStringArray(R.array.home_info_name);
            String[] hInfoContent = this.getResources().getStringArray(R.array.home_info_content);
            String hInfoYear = sdf.format(new java.util.Date());

            final ArrayList<HomeListInfoEntity> mList = new ArrayList<>();
            for(int i=0;i<4;i++){
                HomeListInfoEntity mHomeListInfoEntity = new HomeListInfoEntity(hInfoHead[i%4],hInfoName[i%4],hInfoContent[i%4],hInfoYear);
                mList.add(mHomeListInfoEntity);
            }
            ListView lvHomeInfo = (ListView)view.findViewById(R.id.lv_home_information);
            HomeListInfoAdapter mHomeListInfoAdapter = new HomeListInfoAdapter(mContext,mList);
            lvHomeInfo.setAdapter(mHomeListInfoAdapter);

        }

        return view;
    }


}
