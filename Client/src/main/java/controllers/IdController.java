package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import youareell.YouAreEll;

import javax.json.JsonString;

import static youareell.YouAreEll.post_ids;
import static youareell.YouAreEll.put_ids;

public class IdController {
    private HashMap<String, Id> allIds;

    Id myId;

    ObjectMapper mapper = new ObjectMapper();

    public ArrayList<Id> getIds(String ids) {
        try {
            ArrayList<Id> idsList = mapper.readValue(ids, new TypeReference<ArrayList<Id>>(){});
            return idsList;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Id postId(Id id) throws IOException {
        // create json from id
        try {
            System.out.println("Checkpoint 1");
            String jsonString =  mapper.writeValueAsString(id);
            System.out.println("Checkpoint 2");
            post_ids("/ids", jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        // call server, get json result Or error
        // result json to Id obj

        return null;
    }

    public Id putId(Id id) throws IOException {
        try {
            String jsonString =  mapper.writeValueAsString(id);
            put_ids("/ids", jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
 
}