package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import models.Message;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private MessageController msgCtrl;
    private IdController idCtrl;

    ObjectMapper mapper = new ObjectMapper();

    public TransactionController(MessageController m, IdController j) {
        this.msgCtrl = m;
        this.idCtrl = j;
    }

    public List<Id> getIds() {
        return null;
    }
    public String postId(String idtoRegister, String githubName) throws IOException {
        Id tid = new Id("-",idtoRegister, githubName);
        tid = idCtrl.postId(tid);
        return ("Id registered.");
    }

    public String putId(String idtoRegister, String githubName) throws IOException {
        Id tid = new Id(idtoRegister, githubName);
        tid = idCtrl.putId(tid);
        return ("Id changed.");
    }

    public String makeCall(String s, String get, String s1) {
        return null;
    }
}
