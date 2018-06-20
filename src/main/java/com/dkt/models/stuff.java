package com.dkt.models;

public class stuff {
    private int id;
    private String title;
    private String startingDate;
    private String endingDate;
    private String comment;

    public stuff(int id, String title, String startingDate, String endingDate, String comment) {
        this.id = id;
        this.title = title;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
