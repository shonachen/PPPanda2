package com.pppanda.entity;

/**
 * Created by Administrator on 2017/5/9.
 */

public class BaseInfoEntity {
    int user_id;
    int card_status;
    String card_id;
    String photo_url;
    String mob_phone;
    int birth_year;
    int birth_month;
    int birth_dat;
    int sex;
    String company_code;
    String like_name;
    String sign;
    int hcccard_status;
    int finger_status;
    String name;
    int longitude;
    int latitude;
    String site_name;
    String last_name;
    String first_name;

    public BaseInfoEntity(int user_id, int card_status, String card_id,
                          String photo_url, String mob_phone, int birth_year,
                          int birth_month, int birth_dat, int sex, String company_code,
                          String like_name, String sign, int hcccard_status, int finger_status,
                          String name, int longitude, int latitude, String site_name, String last_name,
                          String first_name) {
        this.user_id = user_id;
        this.card_status = card_status;
        this.card_id = card_id;
        this.photo_url = photo_url;
        this.mob_phone = mob_phone;
        this.birth_year = birth_year;
        this.birth_month = birth_month;
        this.birth_dat = birth_dat;
        this.sex = sex;
        this.company_code = company_code;
        this.like_name = like_name;
        this.sign = sign;
        this.hcccard_status = hcccard_status;
        this.finger_status = finger_status;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.site_name = site_name;
        this.last_name = last_name;
        this.first_name = first_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCard_status() {
        return card_status;
    }

    public void setCard_status(int card_status) {
        this.card_status = card_status;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getMob_phone() {
        return mob_phone;
    }

    public void setMob_phone(String mob_phone) {
        this.mob_phone = mob_phone;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }

    public int getBirth_month() {
        return birth_month;
    }

    public void setBirth_month(int birth_month) {
        this.birth_month = birth_month;
    }

    public int getBirth_dat() {
        return birth_dat;
    }

    public void setBirth_dat(int birth_dat) {
        this.birth_dat = birth_dat;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getLike_name() {
        return like_name;
    }

    public void setLike_name(String like_name) {
        this.like_name = like_name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getHcccard_status() {
        return hcccard_status;
    }

    public void setHcccard_status(int hcccard_status) {
        this.hcccard_status = hcccard_status;
    }

    public int getFinger_status() {
        return finger_status;
    }

    public void setFinger_status(int finger_status) {
        this.finger_status = finger_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Override
    public String toString() {
        return "BaseInfoEntity{" +
                "user_id=" + user_id +
                ", card_status=" + card_status +
                ", card_id='" + card_id + '\'' +
                ", photo_url='" + photo_url + '\'' +
                ", mob_phone='" + mob_phone + '\'' +
                ", birth_year=" + birth_year +
                ", birth_month=" + birth_month +
                ", birth_dat=" + birth_dat +
                ", sex=" + sex +
                ", company_code='" + company_code + '\'' +
                ", like_name='" + like_name + '\'' +
                ", sign='" + sign + '\'' +
                ", hcccard_status=" + hcccard_status +
                ", finger_status=" + finger_status +
                ", name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", site_name='" + site_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                '}';
    }
}
