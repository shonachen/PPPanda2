package com.pppanda.cache;

import android.util.Log;
import android.util.SparseArray;

import com.pppanda.entity.BaseInfoEntity;
import com.pppanda.entity.DictRelationEntity;
import com.pppanda.entity.HccDataRankEntity;
import com.pppanda.entity.UserRelationEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/9.
 */

public class Cache {
    public static final boolean ISDEBUG = true;
    public static String accessToken;
    public static int userID;   //自己的userID
    public static SparseArray<BaseInfoEntity> mBaseInfoEntitys = new SparseArray<>();
    public static SparseArray<HccDataRankEntity> mHccDataRankEntitys = new SparseArray<>();
    public static SparseArray<UserRelationEntity> mUserRelationEntitys = new SparseArray<>();
    public static SparseArray<DictRelationEntity> mDictRelationEntity = new SparseArray<>();

    /** 删除用户，清理与其相关数据 */
    public static void clearDatasByUserID(int userID){
        Log.e("TAG", "删除相关数据 UserID = "+userID);

        mBaseInfoEntitys.delete(userID);
        mUserRelationEntitys.delete(userID);
        mHccDataRankEntitys.delete(userID);
//        mHealthDetailDatas.delete(userID);
//        mCdms.delete(userID);
    }

}
