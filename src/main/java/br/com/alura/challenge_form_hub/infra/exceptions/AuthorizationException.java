package com.aluraone.forumHub.infra.exceptions;

public class AuthorizationException extends RuntimeException{
    public AuthorizationException(String message){
        super(message);
    }

}