package com.pppanda;

/**
 * Created by Administrator on 2017/5/8.
 */

public class DictRelation {
    int relation_code;
    String relation_desc;
    int is_del;

    public DictRelation(int relation_code, String relation_desc, int is_del) {
        this.relation_code = relation_code;
        this.relation_desc = relation_desc;
        this.is_del = is_del;
    }

    public int getRelation_code() {
        return relation_code;
    }

    public void setRelation_code(int relation_code) {
        this.relation_code = relation_code;
    }

    public String getRelation_desc() {
        return relation_desc;
    }

    public void setRelation_desc(String relation_desc) {
        this.relation_desc = relation_desc;
    }

    public int getIs_del() {
        return is_del;
    }

    public void setIs_del(int is_del) {
        this.is_del = is_del;
    }

    @Override
    public String toString() {
        return "DictRelation{" +
                "relation_code=" + relation_code +
                ", relation_desc='" + relation_desc + '\'' +
                ", is_del=" + is_del +
                '}';
    }
}
