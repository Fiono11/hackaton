package server.task_manager;

import server.service.HibernateUserService;
import shared.Communication;
import shared.MessageType;

import java.util.HashMap;

/**
 * Created by codecadet on 13/07/17.
 */
public class Task implements Runnable{

    private Strategy strategy;
    private HashMap<String,String> content;
    private Communication communication;

    public Task(HashMap<String, String> content, MessageType type, HibernateUserService hibernateUserService, Communication communication) {
        this.communication = communication;
        strategy = new Strategy(type,hibernateUserService);
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

    @Override
    public void run() {
        strategy.action(content, communication);
    }

    @Override
    public String toString() {
        return "Task{" +
                "content=" + content +
                '}';
    }
}
