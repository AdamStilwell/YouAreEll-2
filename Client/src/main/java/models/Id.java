package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * POJO for an Id object
 */
public class Id {
    @JsonCreator
    public Id(
            @JsonProperty("userid") String userid,
            @JsonProperty("name") String name,
            @JsonProperty("github") String github)
    {
        this.userid = userid;
        setName(name);
        setGithub(github);
    }


    private String userid = "";
    private String name = "";
    private String github = "";

    public Id (String name, String githubId) {}

    public String getUid() {
        return userid;
    }

//    public void setUid(String uid) {
//        this.uid = uid;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.github + ") ";
    }
}