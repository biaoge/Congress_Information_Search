package com.example.bg.bg;

/**
 * Created by bbbia on 11/25/2016.
 */

public class Committee {
//    private String committee_id;
//    private String name;
//    private String phone;
//    private String office;
//    private String chamber;
//    private String subcommittee;

    private String chamber;
    private String committee_id;
    private String name;
    private String office;
    private String phone;
    private String parent_committee_id;
    private String subcommittee;

    public String getCommittee_id() {
        return committee_id;
    }

    public void setCommittee_id(String committee_id) {
        this.committee_id = committee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chambeer) {
        this.chamber = chambeer;
    }

    public String getSubcommittee() {
        return subcommittee;
    }

    public void setSubcommittee(String subcommittee) {
        this.subcommittee = subcommittee;
    }

    public String getParent_committee_id() {
        return parent_committee_id;
    }

    public void setParent_committee_id(String parent_committee_id) {
        this.parent_committee_id = parent_committee_id;
    }

    public String getchamberCapt(){
        if (getChamber().equals("house"))
            return "House";
        else if (getChamber().equals("senate"))
            return "Senate";
        else
            return "Joint";

    }

}
