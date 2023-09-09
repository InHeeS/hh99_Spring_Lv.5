package com.sparta.post.entity;

import lombok.Data;

@Data
public class Message {

    private int statusCode;
    private String msg;

    public Message(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }

}
