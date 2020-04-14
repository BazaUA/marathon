package com.bazalytskyi.coursework.dto;

public class MarathonDTO {
    private long id;
    private long host_id;
    private long start_date;
    private long end_dat;
    private String name;
    private String description;
    private long price;
    private String categorу;
    private boolean finished;
    private String winner;
    private long attendancies_count;
    private long attendancies_max_count;
    private boolean archive;
    private boolean enrolled;

    public boolean isEnrolled() {
        return enrolled;
    }

    public void setEnrolled(boolean enrolled) {
        this.enrolled = enrolled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getHost_id() {
        return host_id;
    }

    public void setHost_id(long host_id) {
        this.host_id = host_id;
    }

    public long getStart_date() {
        return start_date;
    }

    public void setStart_date(long start_date) {
        this.start_date = start_date;
    }

    public long getEnd_dat() {
        return end_dat;
    }

    public void setEnd_dat(long end_dat) {
        this.end_dat = end_dat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getCategorу() {
        return categorу;
    }

    public void setCategorу(String categorу) {
        this.categorу = categorу;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public long getAttendancies_count() {
        return attendancies_count;
    }

    public void setAttendancies_count(long attendancies_count) {
        this.attendancies_count = attendancies_count;
    }

    public long getAttendancies_max_count() {
        return attendancies_max_count;
    }

    public void setAttendancies_max_count(long attendancies_max_count) {
        this.attendancies_max_count = attendancies_max_count;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }
}
