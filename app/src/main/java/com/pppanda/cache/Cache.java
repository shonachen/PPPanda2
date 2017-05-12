package com.pppanda.cache;

import android.util.SparseArray;

import com.pppanda.entity.BaseInfoEntity;
import com.pppanda.entity.HccDataRankEntity;

/**
 * Created by Administrator on 2017/5/9.
 */

public class Cache {
    public static String accessToken;
    public static int userID;   //自己的userID
    public static SparseArray<BaseInfoEntity> mBaseInfoEntitys = new SparseArray<>();
    public static SparseArray<HccDataRankEntity> mHccDataRankEntitys = new SparseArray<>();

}
