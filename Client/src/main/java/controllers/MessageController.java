package controllers;

import java.util.ArrayList;
import java.util.HashSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;

public class MessageController {

    private HashSet<Message> messagesSeen;
    // why a HashSet??

    ObjectMapper mapper = new ObjectMapper();

    public ArrayList<Message> getMessages(String messages) {
        try {
            System.out.println("Checkpoint 1");
            ArrayList<Message> messagesList = mapper.readValue(messages, new TypeReference<ArrayList<Message>>(){});
            System.out.println("Checkpoint 2");
            return messagesList;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Message> getMessagesForId(Id Id) {
        return null;
    }
    public Message getMessageForSequence(String seq) {
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }
 
}