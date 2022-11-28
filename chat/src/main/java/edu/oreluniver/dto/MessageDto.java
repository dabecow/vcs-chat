package edu.oreluniver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
public class MessageDto {

    @Getter
    private UUID id;

    @Setter
    @Getter
    private String text;

    @Setter
    @Getter
    private String author;

    public MessageDto(String author) {
        this.author = author;
    }

    public MessageDto(UUID id, String text, String author) {
        this.id = id;
        this.text = text;
        this.author = author;
    }
}
