package com.bazalytskyi.coursework.dto;

public class PostDTO {
    private long id;
    private String text;
    private long marathon_id;
    private long user_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getMarathon_id() {
        return marathon_id;
    }

    public void setMarathon_id(long marathon_id) {
        this.marathon_id = marathon_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
