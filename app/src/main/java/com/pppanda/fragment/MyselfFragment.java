package com.pppanda.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.support.v4.app.Fragment;

import com.pppanda.R;


/**
 * Created by Administrator on 2017/5/4.
 */

public class MyselfFragment extends Fragment {
    RelativeLayout myInfo;
    Context context;
    View view;

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_myself, container, false);



    }
}
