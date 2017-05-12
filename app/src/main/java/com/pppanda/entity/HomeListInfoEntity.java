package com.pppanda.entity;

/**
 * Created by Administrator on 2017/5/11.
 */

public class HomeListInfoEntity {
    int hInfoHead;
    String hInfoName;
    String hInfoContent;
    String hInfoYear;

    public HomeListInfoEntity(int hInfoHead, String hInfoName, String hInfoContent, String hInfoYear) {
        this.hInfoHead = hInfoHead;
        this.hInfoName = hInfoName;
        this.hInfoContent = hInfoContent;
        this.hInfoYear = hInfoYear;
    }

    public int gethInfoHead() {
        return hInfoHead;
    }

    public void sethInfoHead(int hInfoHead) {
        this.hInfoHead = hInfoHead;
    }

    public String gethInfoName() {
        return hInfoName;
    }

    public void sethInfoName(String hInfoName) {
        this.hInfoName = hInfoName;
    }

    public String gethInfoContent() {
        return hInfoContent;
    }

    public void sethInfoContent(String hInfoContent) {
        this.hInfoContent = hInfoContent;
    }

    public String gethInfoYear() {
        return hInfoYear;
    }

    public void sethInfoYear(String hInfoYear) {
        this.hInfoYear = hInfoYear;
    }

    @Override
    public String toString() {
        return "HomeListInfoEntity{" +
                "hInfoHead=" + hInfoHead +
                ", hInfoName='" + hInfoName + '\'' +
                ", hInfoContent='" + hInfoContent + '\'' +
                ", hInfoYear='" + hInfoYear + '\'' +
                '}';
    }
}
