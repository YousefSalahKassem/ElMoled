package com.elmoledmol.www;

public class reviewsinheret {
    String name,date,comment;
    int rate;

    public reviewsinheret(String name, String date, String comment, int rate) {
        this.name = name;
        this.date = date;
        this.comment = comment;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
