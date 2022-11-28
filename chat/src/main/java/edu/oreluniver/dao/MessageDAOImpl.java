package edu.oreluniver.dao;

import edu.oreluniver.model.Message;

import java.util.LinkedList;
import java.util.List;

/**
 * Class description
 */
public class MessageDAOImpl implements MessageDAO {

//    private final LinkedHashMap<String, Message> messages = new LinkedHashMap<>();
    private final List<Message> messages = new LinkedList<>();
    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void addMessage(Message message) {
        messages.add(message);
    }
}
