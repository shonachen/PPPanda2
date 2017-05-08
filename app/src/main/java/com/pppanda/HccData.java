package com.pppanda;

/**
 * Created by Administrator on 2017/5/8.
 */

public class HccData {
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

    public HccData(String msg_id, String device_id, int user_id, int detect_time,
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

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDetect_time() {
        return detect_time;
    }

    public void setDetect_time(int detect_time) {
        this.detect_time = detect_time;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(float heart_rate) {
        this.heart_rate = heart_rate;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getXybhd() {
        return xybhd;
    }

    public void setXybhd(float xybhd) {
        this.xybhd = xybhd;
    }

    public float getQztz() {
        return qztz;
    }

    public void setQztz(float qztz) {
        this.qztz = qztz;
    }

    public float getTgzs() {
        return tgzs;
    }

    public void setTgzs(float tgzs) {
        this.tgzs = tgzs;
    }

    public float getTzfl() {
        return tzfl;
    }

    public void setTzfl(float tzfl) {
        this.tzfl = tzfl;
    }

    public float getGgjl() {
        return ggjl;
    }

    public void setGgjl(float ggjl) {
        this.ggjl = ggjl;
    }

    public float getGkhl() {
        return gkhl;
    }

    public void setGkhl(float gkhl) {
        this.gkhl = gkhl;
    }

    public float getStsfl() {
        return stsfl;
    }

    public void setStsfl(float stsfl) {
        this.stsfl = stsfl;
    }

    public float getLzzfhl() {
        return lzzfhl;
    }

    public void setLzzfhl(float lzzfhl) {
        this.lzzfhl = lzzfhl;
    }

    public float getSlll() {
        return slll;
    }

    public void setSlll(float slll) {
        this.slll = slll;
    }

    public float getJcdx() {
        return jcdx;
    }

    public void setJcdx(float jcdx) {
        this.jcdx = jcdx;
    }

    public float getXbwysf() {
        return xbwysf;
    }

    public void setXbwysf(float xbwysf) {
        this.xbwysf = xbwysf;
    }

    public float getSsy() {
        return ssy;
    }

    public void setSsy(float ssy) {
        this.ssy = ssy;
    }

    public float getSzy() {
        return szy;
    }

    public void setSzy(float szy) {
        this.szy = szy;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getRank() {
        return rank;
    }

    public void setRank(float rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "HccData{" +
                "msg_id='" + msg_id + '\'' +
                ", device_id='" + device_id + '\'' +
                ", user_id=" + user_id +
                ", detect_time=" + detect_time +
                ", height=" + height +
                ", weight=" + weight +
                ", heart_rate=" + heart_rate +
                ", temp=" + temp +
                ", xybhd=" + xybhd +
                ", qztz=" + qztz +
                ", tgzs=" + tgzs +
                ", tzfl=" + tzfl +
                ", ggjl=" + ggjl +
                ", gkhl=" + gkhl +
                ", stsfl=" + stsfl +
                ", lzzfhl=" + lzzfhl +
                ", slll=" + slll +
                ", jcdx=" + jcdx +
                ", xbwysf=" + xbwysf +
                ", ssy=" + ssy +
                ", szy=" + szy +
                ", score=" + score +
                ", rank=" + rank +
                '}';
    }
}
