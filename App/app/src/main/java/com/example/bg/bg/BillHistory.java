package com.example.bg.bg;

/**
 * Created by bbbia on 11/25/2016.
 */

public class BillHistory {

    private boolean active;
    private String active_at;
    private boolean awaiting_signature;
    private boolean enacted;
    private String senate_passage_result;
    private String senate_passage_result_at;
    private boolean voted;

//    private String active;
//    private String awaiting_signature;
//    private boolean enacted;
//    private boolean voted;


    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getActive_at() {
        return active_at;
    }

    public void setActive_at(String active_at) {
        this.active_at = active_at;
    }

    public boolean isAwaiting_signature() {
        return awaiting_signature;
    }

    public void setAwaiting_signature(boolean awaiting_signature) {
        this.awaiting_signature = awaiting_signature;
    }

    public boolean isEnacted() {
        return enacted;
    }

    public void setEnacted(boolean enacted) {
        this.enacted = enacted;
    }

    public String getSenate_passage_result() {
        return senate_passage_result;
    }

    public void setSenate_passage_result(String senate_passage_result) {
        this.senate_passage_result = senate_passage_result;
    }

    public String getSenate_passage_result_at() {
        return senate_passage_result_at;
    }

    public void setSenate_passage_result_at(String senate_passage_result_at) {
        this.senate_passage_result_at = senate_passage_result_at;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }
}
