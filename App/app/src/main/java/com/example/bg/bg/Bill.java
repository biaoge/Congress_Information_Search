package com.example.bg.bg;

import java.util.Map;

/**
 * Created by bbbia on 11/25/2016.
 */

public class Bill {

//    private String bill_id;
//    private String bill_type;
//    private int number;
//    private int congress;
//    private String chamber;
//    private String short_title;
//    private String official_title;
//    private String popular_title;
//    private Map<String, String>sponsor;
//    private String sponsor_id;
//    private int cosponsors_count;
//    private int withdrawn_cosponsors_count;
//    private String  introduced_on;
//    private BillHistory history;
//    private String enacted_as;
//    private String last_action_at;
//    private String last_vote_at;
//    private String[] committee_ids;
//    private String[] related_bill_ids;
//    private Map<String, String>urls;
//    private String last_version_on;
//    private BillLastVersion last_version;

    private String bill_id;
    private String bill_type;
    private String chamber;
    private String[] committee_ids;
    private int congress;
    private int cosponsors_count;
//    private String enacted_as;
    private BillEnactedAs enacted_as;
    private BillHistory history;
    private String introduced_on;
    private String last_action_at;
    private BillLastVersion last_version;
    private String last_version_on;
    private String last_vote_at;
    private int number;
    private String official_title;
    private String popular_title;
    private String[] related_bill_ids;
    private String short_title;
    private Map<String, String>sponsor;
    private String sponsor_id;
    private Map<String, String> urls;
    private int withdrawn_cosponsors_count;


    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getBill_type() {
        return bill_type;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCongress() {
        return congress;
    }

    public void setCongress(int congress) {
        this.congress = congress;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public String getOfficial_title() {
        return official_title;
    }

    public void setOfficial_title(String official_title) {
        this.official_title = official_title;
    }

    public String getPopular_title() {
        return popular_title;
    }

    public void setPopular_title(String popular_title) {
        this.popular_title = popular_title;
    }

    public Map<String, String> getSponsor() {
        return sponsor;
    }

    public void setSponsor(Map<String, String> sponsor) {
        this.sponsor = sponsor;
    }

    public String getSponsor_id() {
        return sponsor_id;
    }

    public void setSponsor_id(String sponsor_id) {
        this.sponsor_id = sponsor_id;
    }

    public int getCosponsors_count() {
        return cosponsors_count;
    }

    public void setCosponsors_count(int cosponsors_count) {
        this.cosponsors_count = cosponsors_count;
    }

    public int getWithdrawn_cosponsors_count() {
        return withdrawn_cosponsors_count;
    }

    public void setWithdrawn_cosponsors_count(int withdrawn_cosponsors_count) {
        this.withdrawn_cosponsors_count = withdrawn_cosponsors_count;
    }

    public String getIntroduced_on() {
        return introduced_on;
    }

    public void setIntroduced_on(String introduced_on) {
        this.introduced_on = introduced_on;
    }

//    public String getEnacted_as() {
//        return enacted_as;
//    }
//
//    public void setEnacted_as(String enacted_as) {
//        this.enacted_as = enacted_as;
//    }


    public BillEnactedAs getEnacted_as() {
        return enacted_as;
    }

    public void setEnacted_as(BillEnactedAs enacted_as) {
        this.enacted_as = enacted_as;
    }

    public BillHistory getHistory() {
        return history;
    }

    public void setHistory(BillHistory history) {
        this.history = history;
    }

    public String getLast_action_at() {
        return last_action_at;
    }

    public void setLast_action_at(String last_action_at) {
        this.last_action_at = last_action_at;
    }

    public String getLast_vote_at() {
        return last_vote_at;
    }

    public void setLast_vote_at(String last_vote_at) {
        this.last_vote_at = last_vote_at;
    }

    public String[] getCommittee_ids() {
        return committee_ids;
    }

    public void setCommittee_ids(String[] committee_ids) {
        this.committee_ids = committee_ids;
    }

    public String[] getRelated_bill_ids() {
        return related_bill_ids;
    }

    public void setRelated_bill_ids(String[] related_bill_ids) {
        this.related_bill_ids = related_bill_ids;
    }

    public Map<String, String> getUrls() {
        return urls;
    }

    public void setUrls(Map<String, String> urls) {
        this.urls = urls;
    }

    public String getLast_version_on() {
        return last_version_on;
    }

    public void setLast_version_on(String last_version_on) {
        this.last_version_on = last_version_on;
    }

    public BillLastVersion getLast_version() {
        return last_version;
    }

    public void setLast_version(BillLastVersion last_version) {
        this.last_version = last_version;
    }

//  获取转换后的introduced on时间
    public String getIntroducedonDate()
    {
        String dateRaw = getIntroduced_on();
        String[] dateRawParts = dateRaw.split("-");
        String month = dateRawParts[1];
        String year = dateRawParts[0];
        String day = dateRawParts[2];

        if (month.equals("01"))
            month = "Jan";
        else if (month.equals("02"))
            month = "Feb";
        else if (month.equals("03"))
            month = "Mar";
        else if (month.equals("04"))
            month = "Apr";
        else if (month.equals("05"))
            month = "May";
        else if (month.equals("06"))
            month = "Jun";
        else if (month.equals("07"))
            month = "Jul";
        else if (month.equals("08"))
            month = "Aug";
        else if (month.equals("09"))
            month = "Sep";
        else if (month.equals("10"))
            month = "Oct";
        else if (month.equals("11"))
            month = "Nov";
        else
            month = "Dec";

        String introducedDate = month + " "+  day + ", " + year;
        return introducedDate;
    }
//  获取bill title
    public String getBilltitle()
    {
        String short_title = getShort_title();
        String long_title = getOfficial_title();
        if (short_title == null)
            return long_title;
        else
            return short_title;
    }
//  获取sponsor的名字
    public String getSponsorNmae(){
        String title = sponsor.get("title");
        String sponsor_fname = sponsor.get("first_name");
        String sponsor_lname = sponsor.get("last_name");

        return title + ". " + sponsor_lname + ", " + sponsor_fname;
    }

    public String getVersionstatus() {
        String versionStatus = getLast_version().getVersion_name();
        return versionStatus;
    }

    public String getCongressUrl() {
        String congressUrl = urls.get("congress");
        return congressUrl;
    }

    public boolean getBillStatus() {
        boolean  status = getHistory().getActive();
        return status;
    }

    public String getBillUrl() {
        String billUrl = getLast_version().getUrls().get("pdf");
        return billUrl;
    }

}
