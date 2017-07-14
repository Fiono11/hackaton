package server;

import server.service.UserService;
import shared.MessageType;

import java.util.HashMap;

/**
 * Created by codecadet on 13/07/17.
 */
public class Task {

    private Strategy strategy;
    private HashMap<String,String> content;

    public Task(HashMap<String, String> content, MessageType type, UserService userService) {
        strategy = new Strategy(type,userService);
        this.content = content;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public HashMap<String, String> getContent() {
        return content;
    }

    public void setUpStrategy(MessageType type) {



    }
}
