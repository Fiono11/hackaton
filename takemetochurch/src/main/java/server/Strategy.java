package server;

import server.model.User;
import server.model.dao.UserDao;
import server.service.HibernateUserService;
import shared.MessageType;
import shared.Values;

import java.util.HashMap;

/**
 * Created by codecadet on 13/07/17.
 */
public class Strategy {

    private HibernateUserService userService;
    private MessageType type;

    public Strategy(MessageType type, HibernateUserService hibernateUserService) {
        this.type = type;
        this.userService = hibernateUserService;
    }

    void action(HashMap<String,String> map){

        switch (type){
            case LOGIN:
                userService.authenticate(UserDao.class, map.get(Values.USERNAME), map.get(Values.PASSWORD));
                break;
            case REGISTRY:
                String username = map.get(Values.USERNAME);
                String password = map.get(Values.PASSWORD);
                String firstName = map.get(Values.FIRST_NAME);
                String lastName = map.get(Values.LAST_NAME);
                String email = map.get(Values.EMAIL);

                User user = new User(username, firstName,lastName, password, email);
                userService.addData(Values.USERDAO,user);
                break;
            //TODO add the other cases
        }

    }
}
