package controllers;

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

    public Id postId(Id id) {
        // create json from id
        // call server, get json result Or error
        // result json to Id obj
        return null;
    }

    public Id putId(Id id) {
        return null;
    }
 
}