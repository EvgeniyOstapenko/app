package com.example.app.domain.util;

import com.example.app.domain.User;

public class MessageHelper {
    public static String getAuthorName(User author){
        return author != null ? author.getUsername() : "<none>";
    }
}
