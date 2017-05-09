package com.pppanda.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pppanda.R;
import com.pppanda.cache.Cache;

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
    Context context;
    ImageView homeAddFamily;
    ImageView homeBangding;
    TextView homeScore;
    int card_status;

//    public void setContext(Context context) {
//        this.context = context;
//    }
//
//    public HomeFragment(){
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        startActivity(new Intent(getActivity(), LoginActivity.class));//跳转页面
//
//         Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        homeAddFamily = (ImageView)view.findViewById(R.id.iv_home_addfamily);
        homeBangding = (ImageView)view.findViewById(R.id.iv_home_bangding);
        homeScore = (TextView)view.findViewById(R.id.tv_home_score);
        card_status = Cache.mBaseInfoEntity.get(Cache.userID).getCard_status();
        if (card_status != 3){
//            homeAddFamily.setImageResource();
        }
        return view;
    }




}
