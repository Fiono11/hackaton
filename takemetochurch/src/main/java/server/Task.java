package server;

import shared.MessageType;

import java.util.HashMap;

/**
 * Created by codecadet on 13/07/17.
 */
public class Task {

    private Strategy strategy;
    private HashMap<String,String> content;

    public Task(MessageType type, HashMap<String, String> content) {
        this.content = content;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public HashMap<String, String> getContent() {
        return content;
    }
}
