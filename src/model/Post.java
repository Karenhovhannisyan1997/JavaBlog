package model;

import java.util.Date;
import java.util.Objects;

public class Post {

    private String title;
    private String text;
    private String category;
    private Date createdDate;
    private User user;

    public Post(String title, String text, String category, Date createdDate, User user) {
        this.title = title;
        this.text = text;
        this.category = category;
        this.createdDate = createdDate;
        this.user = user;
    }

    public Post() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(title, post.title) &&
                Objects.equals(text, post.text) &&
                Objects.equals(category, post.category) &&
                Objects.equals(createdDate, post.createdDate) &&
                Objects.equals(user, post.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text, category, createdDate, user);
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", category='" + category + '\'' +
                ", createdDate=" + createdDate +
                ", user=" + user.getName() +
                '}';
    }
}