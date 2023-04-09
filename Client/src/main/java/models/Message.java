package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * POJO for an Message object
 *
 *   {
    "sequence": "-",
    "timestamp": "_",
    "fromid": "xt0fer",
    "toid": "kristofer",
    "message": "Hello, Kristofer!"
  },

*
 */
public class Message implements Comparable {

    @JsonCreator
    public Message(
            @JsonProperty("sequence") String seqId,
            @JsonProperty("timestamp") String timestamp,
            @JsonProperty("fromid") String fromId,
            @JsonProperty("toid") String toid,
            @JsonProperty("message") String message)
    {
        setMessage(message);
        setToId(toid);
        setFromId(fromId);
        this.timestamp = timestamp;
        this.seqId = seqId;
    }



    private String message = "";
    private String toId = "";
    private String fromId = "";
    private String timestamp = "";
    private String seqId = "";

    public Message (String message, String fromId, String toId) {
        this.message = message;
        this.fromId = fromId;
        this.toId = toId;
    }

    public Message (String message, String fromId) {
        this.message = message;
        this.fromId = fromId;
        this.toId = "";
    }

    @Override
    public String toString() {
        return "to: " + this.toId + "\nfrom: "+ this.fromId + "\n" + this.message + "\n----\n";
    }

    public int compareTo(Object o) {
        return this.seqId.compareTo(((Message) o).getSeqId());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        if(toId.equals("")){
            this.toId = "Everyone";
            return;
        }
        this.toId = toId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSeqId() {
        return seqId;
    }
}