package client;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by codecadet on 13/07/17.
 */
public class Message implements Serializable{

    private MessageType type;
    private HashMap<String, String> mapContent;

    public Message(MessageType type, HashMap<String, String> mapContent) {
        this.type = type;
        this.mapContent = mapContent;
    }

    public MessageType getType() {
        return type;
    }

    public HashMap<String, String> getMapContent() {
        return mapContent;
    }
}
