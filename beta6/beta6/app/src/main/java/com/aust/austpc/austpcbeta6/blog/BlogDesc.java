package com.aust.austpc.austpcbeta6.blog;


public class BlogDesc {
    private String Description,Title;

    public BlogDesc(String description, String title) {
        Description = description;
        Title = title;
    }

    public BlogDesc() {

    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
