package com.aust.austpc.austpcbeta6.Event;

/**
 * Created by USER on 2/4/2018.
 */

public class EventDesc {
    private String  title,date,venue,about;
    private int id;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;

    }

    public String getDate() {
        return date;
    }

    public String getVenue() {
        return venue;
    }

    public String getAbout() {
        return about;
    }

    public EventDesc(int id, String title, String date, String venue, String about) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.venue = venue;
        this.about = about;
    }
    public EventDesc(String title, String date, String venue, String about) {
        this.title = title;
        this.date = date;
        this.venue = venue;
        this.about = about;
    }
}
