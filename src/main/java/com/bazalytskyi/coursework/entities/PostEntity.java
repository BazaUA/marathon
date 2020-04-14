package com.bazalytskyi.coursework.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Post")
public class PostEntity {
    @Id
    @GeneratedValue
    private long id;
    private String text;
    private Long createdDate;
    @ManyToOne
    private MarathonEntity marathon;
    @ManyToOne
    private UserEntity user;

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

    public MarathonEntity getMarathon() {
        return marathon;
    }

    public void setMarathon(MarathonEntity marathon) {
        this.marathon = marathon;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostEntity that = (PostEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
