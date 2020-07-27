package com.example.app.domain.converters;

import com.example.app.domain.Message;
import com.example.app.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MessageConverter implements Converter<String, Message> {

    @Autowired
    private MessageRepo messageRepo;

    @Override
    public Message convert(String id) {
        return messageRepo.findById(Long.valueOf(id)).orElse(null);
    }
}
