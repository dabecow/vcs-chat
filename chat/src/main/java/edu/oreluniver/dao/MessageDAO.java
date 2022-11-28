package edu.oreluniver.dao;

import edu.oreluniver.model.Message;

import java.util.List;

public interface MessageDAO {

    List<Message> getMessages();
    void addMessage(Message message);

}
