package com.sparta.post.exception;

public class DuplicateUsernameException extends RuntimeException{
    public DuplicateUsernameException(String msg){
        super(msg);
    }
}
