package com.pppanda.entity;

/**
 * Created by Administrator on 2017/5/9.
 */

public class HccDataRankEntity {
    float score;
    int rank;
    int rank_up;
    int user_id;
    int last_time;

    public HccDataRankEntity(float score, int rank, int rank_up, int user_id, int last_time) {
        this.score = score;
        this.rank = rank;
        this.rank_up = rank_up;
        this.user_id = user_id;
        this.last_time = last_time;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank_up() {
        return rank_up;
    }

    public void setRank_up(int rank_up) {
        this.rank_up = rank_up;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLast_time() {
        return last_time;
    }

    public void setLast_time(int last_time) {
        this.last_time = last_time;
    }

    @Override
    public String toString() {
        return "HccDataRankEntity{" +
                "score=" + score +
                ", rank=" + rank +
                ", rank_up=" + rank_up +
                ", user_id=" + user_id +
                ", last_time=" + last_time +
                '}';
    }
}
