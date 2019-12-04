package com.bazalytskyi.coursework.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Marathon")
public class MarathonEntity {
    @Id
    @GeneratedValue
    private long id;
    private long hostId;
    private long startDate;
    private long endDate;
    private String name;
    private String description;
    private long price;
    private String category;
    private boolean finished;
    private String winner;
    private long attendancesMaxCount;
    private boolean archive;
    @ManyToMany
    private List<UserEntity> users = new ArrayList<>();
    @OneToMany
    private List<PostEntity> posts = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getHostId() {
        return hostId;
    }

    public void setHostId(long hostId) {
        this.hostId = hostId;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public long getAttendancesMaxCount() {
        return attendancesMaxCount;
    }

    public void setAttendancesMaxCount(long attendancesMaxCount) {
        this.attendancesMaxCount = attendancesMaxCount;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostEntity> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarathonEntity that = (MarathonEntity) o;
        return id == that.id &&
                hostId == that.hostId &&
                startDate == that.startDate &&
                endDate == that.endDate &&
                price == that.price &&
                finished == that.finished &&
                attendancesMaxCount == that.attendancesMaxCount &&
                archive == that.archive &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(category, that.category) &&
                Objects.equals(winner, that.winner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hostId, startDate, endDate, name, description, price, category, finished, winner, attendancesMaxCount, archive);
    }

    @Override
    public String toString() {
        return "MarathonEntity{" +
                "id=" + id +
                ", hostId=" + hostId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", finished=" + finished +
                ", winner='" + winner + '\'' +
                ", attendancesMaxCount=" + attendancesMaxCount +
                ", archive=" + archive +
                '}';
    }
}
