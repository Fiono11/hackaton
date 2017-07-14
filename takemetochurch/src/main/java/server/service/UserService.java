package server.service;

import server.model.Data;
import server.model.User;
import server.model.dao.Dao;

/**
 * Created by Cyrille on 13/07/17.
 */
public interface UserService {

    boolean authenticate(Class<? extends Dao> T, String username, String password);

    <E extends Data> void addUser(Class<? extends Dao> T, User user);

    User findByName(String username);

    <E extends Data> E findById(int number);

    <E extends Data> void removeUSer(Class<? extends Dao> T, E data);
}
