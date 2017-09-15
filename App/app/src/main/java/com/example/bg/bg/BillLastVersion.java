package com.example.bg.bg;

import java.util.Map;

/**
 * Created by bbbia on 11/25/2016.
 */

public class BillLastVersion {
    private String version_code;
    private String issued_on;
    private String version_name;
    private String bill_version_id;
    private Map<String, String>urls;
    private String pages;

    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }

    public String getIssued_on() {
        return issued_on;
    }

    public void setIssued_on(String issued_on) {
        this.issued_on = issued_on;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public String getBill_version_id() {
        return bill_version_id;
    }

    public void setBill_version_id(String bill_version_id) {
        this.bill_version_id = bill_version_id;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public Map<String, String> getUrls() {
        return urls;
    }

    public void setUrls(Map<String, String> urls) {
        this.urls = urls;
    }
}
