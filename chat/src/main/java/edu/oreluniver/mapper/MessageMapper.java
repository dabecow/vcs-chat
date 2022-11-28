package edu.oreluniver.mapper;

import edu.oreluniver.dto.MessageDto;
import edu.oreluniver.model.Message;
import edu.oreluniver.vcs.model.Entry;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapper {

    public MessageDto toDto(Entry<Message> entry) {
        Message message = entry.getData();
        return new MessageDto(entry.getUuid(), message.getText(), message.getAuthor());
    }

    public List<MessageDto> toDtoList(List<Entry<Message>> messages) {
        return messages.stream().map(this::toDto).collect(Collectors.toList());
    }
}
