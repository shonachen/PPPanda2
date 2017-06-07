package com.pppanda.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/1.
 */

public class MyFamilyInfoEntity implements Serializable {
    int userID;
    String fHead;
    String fNickName;
    String fRelation;
    boolean activity;

    public MyFamilyInfoEntity(int userID, String fHead, String fNickName, String fRelation,boolean activity) {
        this.userID = userID;
        this.fHead = fHead;
        this.fNickName = fNickName;
        this.fRelation = fRelation;
        this.activity = activity;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getfHead() {
        return fHead;
    }

    public void setfHead(String fHead) {
        this.fHead = fHead;
    }

    public String getfNickName() {
        return fNickName;
    }

    public void setfNickName(String fNickName) {
        this.fNickName = fNickName;
    }

    public String getfRelation() {
        return fRelation;
    }

    public void setfRelation(String fRelation) {
        this.fRelation = fRelation;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "MyFamilyInfoEntity{" +
                "userID=" + userID +
                ", fHead='" + fHead + '\'' +
                ", fNickName='" + fNickName + '\'' +
                ", fRelation='" + fRelation + '\'' +
                ", activity=" + activity +
                '}';
    }
}
