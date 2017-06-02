package com.pppanda.entity;

/**
 * Created by Administrator on 2017/6/1.
 */

public class UserRelationEntity {
    int relation_user_id;
    int relation_code;
    int is_confirm;
    int create_time;
    String relation_mob_phone;
    int user_id;

    public UserRelationEntity(int relation_user_id, int relation_code, int is_confirm,
                              int create_time, String relation_mob_phone, int user_id) {
        this.relation_user_id = relation_user_id;
        this.relation_code = relation_code;
        this.is_confirm = is_confirm;
        this.create_time = create_time;
        this.relation_mob_phone = relation_mob_phone;
        this.user_id = user_id;
    }

    public int getRelation_user_id() {
        return relation_user_id;
    }

    public void setRelation_user_id(int relation_user_id) {
        this.relation_user_id = relation_user_id;
    }

    public int getRelation_code() {
        return relation_code;
    }

    public void setRelation_code(int relation_code) {
        this.relation_code = relation_code;
    }

    public int getIs_confirm() {
        return is_confirm;
    }

    public void setIs_confirm(int is_confirm) {
        this.is_confirm = is_confirm;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public String getRelation_mob_phone() {
        return relation_mob_phone;
    }

    public void setRelation_mob_phone(String relation_mob_phone) {
        this.relation_mob_phone = relation_mob_phone;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "UserRelationEntity{" +
                "relation_user_id=" + relation_user_id +
                ", relation_code=" + relation_code +
                ", is_confirm=" + is_confirm +
                ", create_time=" + create_time +
                ", relation_mob_phone='" + relation_mob_phone + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
