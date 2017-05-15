package com.pppanda.fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pppanda.R;

/**
 * Created by Administrator on 2017/5/12.
 */

public class UploadCredentialsFragment extends Fragment {
    Context mContext;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        view = inflater.inflate(R.layout.fragment_uploadcredentials,null);
        return view;
    }
}
