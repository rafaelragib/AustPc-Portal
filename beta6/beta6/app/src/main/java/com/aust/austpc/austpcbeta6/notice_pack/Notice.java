package com.aust.austpc.austpcbeta6.notice_pack;



public class Notice {
    private String Date, Details;
    public Notice ()
    {

    }
    public Notice(String date,String details )
    {
        this.Date =date;
        this.Details =details;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        this.Details = details;
    }
}
