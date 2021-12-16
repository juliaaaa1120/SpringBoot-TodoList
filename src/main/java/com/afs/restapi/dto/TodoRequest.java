package com.afs.restapi.dto;

public class TodoRequest {
    private String text;

    public TodoRequest() {
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
