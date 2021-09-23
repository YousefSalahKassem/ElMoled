package com.elmoledmol.www;

public class NotificationModel {
    String Body;
    String Time;

    public NotificationModel(String body, String time) {
        Body = body;
        Time = time;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
