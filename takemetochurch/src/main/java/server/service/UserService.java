package server.service;

import server.model.User;

/**
 * Created by Cyrille on 13/07/17.
 */
public interface UserService {

    String getName();

    boolean authenticate(String username, String password);

    void addUser(User user);

    User findByName(String username);

    int count();
}
