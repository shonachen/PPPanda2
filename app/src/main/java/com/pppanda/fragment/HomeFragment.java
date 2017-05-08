package com.pppanda.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pppanda.R;

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
    public void setContext(Context context) {
        this.context = context;
    }

    public HomeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        startActivity(new Intent(getActivity(), LoginActivity.class));//跳转页面
//
//         Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }


}
