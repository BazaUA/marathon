package com.bazalytskyi.coursework.dto;

public class PostDTO {
    private long id;
    private String text;
    private long marathon_id;
    private long user_id;
    private Long created_date;
    private String marathon_name;

    public String getMarathon_name() {
        return marathon_name;
    }

    public void setMarathon_name(String marathon_name) {
        this.marathon_name = marathon_name;
    }

    public Long getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Long created_date) {
        this.created_date = created_date;
    }

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
