package com.example.app.domain;

import com.example.app.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<String, User> {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User convert(String source) {
        return userRepo.findById(Long.valueOf(source)).orElse(null);
    }
}
