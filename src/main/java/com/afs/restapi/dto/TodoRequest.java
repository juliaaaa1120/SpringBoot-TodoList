package com.afs.restapi.dto;

public class TodoRequest {
    private String text;
    private Boolean done;

    public TodoRequest() {
    }

    public String getText() {
        return this.text;
    }

    public Boolean getDone() {
        return this.done;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
