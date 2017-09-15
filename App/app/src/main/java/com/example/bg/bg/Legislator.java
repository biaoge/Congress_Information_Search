package com.example.bg.bg;

import com.android.volley.toolbox.StringRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bbbia on 11/24/2016.
 */

public class Legislator {
    private String bioguide_id;
    private boolean in_office;
    private String thomas_id;
    private String govtrack_id;
    private String crp_id;
    private String[] fec_ids;
    private String first_name;
    private String nickname;
    private String last_name;
    private String middle_name;
    private String name_suffix;
    private String gender;
    private String birthday;
    private String leadership_role;
    private String term_start;
    private String term_end;
    private String state;
    private String state_name;
    private String party;
    private String title;
    private String chamber;
    private String phone;
    private String fax;
    private String website;
    private String office;
    private String contact_form;
    private int votesmart_id;
    private int district;
    private String oc_email;
    private String twitter_id;
    private String youtube_id;
    private String facebook_id;
    private String ocd_id;

    public String getBioguide_id() {
        return bioguide_id;
    }

    public void setBioguide_id(String bioguide_id) {
        this.bioguide_id = bioguide_id;
    }

    public boolean isIn_office() {
        return in_office;
    }

    public void setIn_office(boolean in_office) {
        this.in_office = in_office;
    }

    public String getThomas_id() {
        return thomas_id;
    }

    public void setThomas_id(String thomas_id) {
        this.thomas_id = thomas_id;
    }

    public String getGovtrack_id() {
        return govtrack_id;
    }

    public void setGovtrack_id(String govtrack_id) {
        this.govtrack_id = govtrack_id;
    }

    public String getCrp_id() {
        return crp_id;
    }

    public void setCrp_id(String crp_id) {
        this.crp_id = crp_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String[] getFec_ids() {
        return fec_ids;
    }

    public void setFec_ids(String[] fec_ids) {
        this.fec_ids = fec_ids;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName_suffix() {
        return name_suffix;
    }

    public void setName_suffix(String name_suffix) {
        this.name_suffix = name_suffix;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLeadership_role() {
        return leadership_role;
    }

    public void setLeadership_role(String leadership_role) {
        this.leadership_role = leadership_role;
    }

    public String getTerm_start() {
        return term_start;
    }

    public void setTerm_start(String term_start) {
        this.term_start = term_start;
    }

    public String getTerm_end() {
        return term_end;
    }

    public void setTerm_end(String term_end) {
        this.term_end = term_end;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getParty() { return party; }

    public void setParty(String party) {
        this.party = party;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public int getVotesmart_id() {
        return votesmart_id;
    }

    public void setVotesmart_id(int votesmart_id) {
        this.votesmart_id = votesmart_id;
    }

    public String getContact_form() {
        return contact_form;
    }

    public void setContact_form(String contact_form) {
        this.contact_form = contact_form;
    }

    public int getDistrict() { return district; }

    public void setDistrict(int district) {
        this.district = district;
    }

    public String getOc_email() {
        return oc_email;
    }

    public void setOc_email(String oc_email) {
        this.oc_email = oc_email;
    }

    public String getOcd_id() {
        return ocd_id;
    }

    public void setOcd_id(String ocd_id) {
        this.ocd_id = ocd_id;
    }

    public String getTwitter_id() {
        return twitter_id;
    }

    public void setTwitter_id(String twitter_id) {
        this.twitter_id = twitter_id;
    }

    public String getYoutube_id() {
        return youtube_id;
    }

    public void setYoutube_id(String youtube_id) {
        this.youtube_id = youtube_id;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    @Override
    public String toString() {
//        return super.toString();
        return "ID: "+ getBioguide_id() + "," + "Name: " + getLast_name()+ "," + getFirst_name();
    }

    public String getFullname() {
        return getLast_name() + ", " + getFirst_name();
    }

    public String getSecline(){
        return "(" + getParty() + ")" + getState_name() + " - District" + getDistrict();
    }

//  获取legi头衔加名字
    public String getLegislatorName() {
        String title = getTitle();
        String lname = getLast_name();
        String fname = getFirst_name();
        return title + ". " + lname + ", " + fname;
    }

//   获取legislato生日
    public String getBirthdayDate() {
        String bday = getBirthday();
        String[] bdays = bday.split("-");
        String year = bdays[0];
        String month = bdays[1];
        String day = bdays[2];

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

        String birthdayDate = month + " "+  day + ", " + year;
        return birthdayDate;
    }

//   获取legislator的start term
    public String getStartTerm () {
        String start = getTerm_start();
        String[] starts = start.split("-");
        String year = starts[0];
        String month = starts[1];
        String day = starts[2];

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

        String startterm = month + " "+  day + ", " + year;
        return startterm;
    }

//  获取legislator的end term
    public String getEndTerm () {
        String end = getTerm_end();
        String[] ends = end.split("-");
        String year = ends[0];
        String month = ends[1];
        String day = ends[2];


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

        String endterm = month + " "+  day + ", " + year;
        return endterm;
    }

    public String getTerm () {
        int term = 0;
        long StartTerm = 0;
        long EndTerm = 0;

        long datenow  = new Date().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        try {
             StartTerm = sdf.parse(getTerm_start()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            EndTerm = sdf.parse(getTerm_end()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        term = (int)((float)(datenow - StartTerm) / (EndTerm - StartTerm) * 100);
        String temp =  String.valueOf(term);
        String term_in_percent = temp + "%";
        return term_in_percent;
    }

    public int getTermInt () {
        int term = 0;
        long StartTerm = 0;
        long EndTerm = 0;

        long datenow  = new Date().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        try {
            StartTerm = sdf.parse(getTerm_start()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            EndTerm = sdf.parse(getTerm_end()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        term = (int)((float)(datenow - StartTerm) / (EndTerm - StartTerm) * 100);
        return  term;
    }

    public String getLegislatorChamber () {
        if (getChamber().equals("house"))
            return "House";
        else if (getChamber().equals("senate"))
            return "Senate";
        else
            return "No House and Senate...";
    }

    public String getPartyName () {
        if (getParty().equals("D"))
            return "Democrat";
        else if (getParty().equals("R"))
            return "Republican";
        else
            return "No Party...";
    }

}
