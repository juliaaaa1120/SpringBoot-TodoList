package com.afs.restapi.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "todos")
public class Todo {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String text;
    private Boolean done;


    public Todo(String id, String text, Boolean done) {
        this.id = id;
        this.text = text;
        this.done = done;
    }

    public Todo() {
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

