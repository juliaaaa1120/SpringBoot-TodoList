package com.afs.restapi.dto;


public class TodoResponse {
    private String id;
    private String text;
    private Boolean done;

    public TodoResponse() {
    }

    public String getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public Boolean getDone() {
        return this.done;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
