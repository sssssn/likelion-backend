package com.example.contents.exceptions;

public class UserNotFoundException extends Status400Exception {
    public UserNotFoundException() {
        super("target user not found");
    }
}
