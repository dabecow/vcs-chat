package edu.oreluniver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class MessageDto {

    @Setter
    @Getter
    private String text;

    @Setter
    @Getter
    private String author;

    public MessageDto(String author) {
        this.author = author;
    }
}
