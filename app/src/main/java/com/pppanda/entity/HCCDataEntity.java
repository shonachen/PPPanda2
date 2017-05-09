package com.pppanda.entity;

/**
 * Created by Administrator on 2017/5/9.
 */

public class HCCDataEntity {
    String msg_id;
    String device_id;
    int user_id;
    int detect_time;
    float height;
    float weight;
    float heart_rate;
    float temp;
    float xybhd;
    float qztz;
    float tgzs;
    float tzfl;
    float ggjl;
    float gkhl;
    float stsfl;
    float lzzfhl;
    float slll;
    float jcdx;
    float xbwysf;
    float ssy;
    float szy;
    float score;
    float rank;

    public HCCDataEntity(String msg_id, String device_id, int user_id, int detect_time,
                   float height, float weight, float heart_rate, float temp, float xybhd,
                   float qztz, float tgzs, float tzfl, float ggjl, float gkhl, float stsfl,
                   float lzzfhl, float slll, float jcdx, float xbwysf, float ssy, float szy,
                   float score, float rank) {
        this.msg_id = msg_id;
        this.device_id = device_id;
        this.user_id = user_id;
        this.detect_time = detect_time;
        this.height = height;
        this.weight = weight;
        this.heart_rate = heart_rate;
        this.temp = temp;
        this.xybhd = xybhd;
        this.qztz = qztz;
        this.tgzs = tgzs;
        this.tzfl = tzfl;
        this.ggjl = ggjl;
        this.gkhl = gkhl;
        this.stsfl = stsfl;
        this.lzzfhl = lzzfhl;
        this.slll = slll;
        this.jcdx = jcdx;
        this.xbwysf = xbwysf;
        this.ssy = ssy;
        this.szy = szy;
        this.score = score;
        this.rank = rank;
    }
}
