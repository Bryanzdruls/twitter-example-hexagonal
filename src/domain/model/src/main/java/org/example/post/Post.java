package org.example.post;

import java.time.LocalTime;

public class Post {

    private LocalTime postHour;

    private String tagName;

    private String message;

    public Post(LocalTime postHour, String tagName, String message) {
        this.postHour = postHour;
        this.tagName = tagName;
        this.message = message;
    }

    public LocalTime getPostHour() {
        return postHour;
    }

    public void setPostHour(LocalTime postHour) {
        this.postHour = postHour;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
