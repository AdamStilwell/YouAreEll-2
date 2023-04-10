package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;

import static youareell.YouAreEll.post_ids;
import static youareell.YouAreEll.post_messages;

public class MessageController {

    private HashSet<Message> messagesSeen;
    // why a HashSet??

    ObjectMapper mapper = new ObjectMapper();

    public ArrayList<Message> getMessages(String messages) {
        try {
            ArrayList<Message> messagesList = mapper.readValue(messages, new TypeReference<ArrayList<Message>>(){});
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

    public Message postMessage(String myId, String toId, String msg, String url) {
        Message message = new Message("-", "2023-04-09T23:33:02.189509098Z", myId, toId, msg);
        try {
            String jsonString =  mapper.writeValueAsString(message);
            System.out.println(jsonString);
            post_messages(url, jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
 
}