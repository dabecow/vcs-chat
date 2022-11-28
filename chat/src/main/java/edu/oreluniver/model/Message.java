package edu.oreluniver.model;

import lombok.Getter;

import java.time.LocalDateTime;

public class Message {

    @Getter
    private final String text;

    @Getter
    private final String author;

    @Getter
    private final LocalDateTime localDateTime;

    public Message(String text, String author) {
        this.text = text;
        this.author = author;
        localDateTime = LocalDateTime.now();
    }
}
