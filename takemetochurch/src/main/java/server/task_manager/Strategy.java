package server.task_manager;

import server.model.Death;
import server.model.Life;
import server.model.User;
import server.service.HibernateUserService;
import shared.Communication;
import shared.Message;
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

    void action(HashMap<String, String> map, Communication communication) {


        System.out.println(map);

        switch (type) {
            case LOGIN:
                boolean b = userService.authenticate(Values.USERDAO, map.get(Values.USERNAME), map.get(Values.PASSWORD));

                HashMap<String, String> map1 = new HashMap<>();
                if (b == true) {
                    map1.put(Values.RESPONSE, Values.OK);
                    communication.setName(map.get(Values.USERNAME));
                } else {
                    map1.put(Values.RESPONSE, Values.NOT_OK);
                }
                Message message = new Message(type, map1);
                communication.write(message);
                break;

            case REGISTRY:
                String username = map.get(Values.USERNAME);
                String password = map.get(Values.PASSWORD);
                String firstName = map.get(Values.FIRST_NAME);
                String lastName = map.get(Values.LAST_NAME);
                String email = map.get(Values.EMAIL);
                String phone = map.get(Values.PHONE);

                User user = new User(username, firstName, lastName, phone, password, email);
                userService.addData(Values.USERDAO, user);

                boolean a = userService.authenticate(Values.USERDAO, map.get(Values.USERNAME), map.get(Values.PASSWORD));
                HashMap<String, String> map2 = new HashMap<>();
                if (a == true) {
                    map2.put(Values.RESPONSE, Values.OK);
                } else {
                    map2.put(Values.RESPONSE, Values.NOT_OK);
                }
                Message message1 = new Message(type, map2);
                communication.write(message1);
                break;
            case LIFE_C:
                Life life = creatLife(map);
                userService.addData(Values.LIFEDAO,life);
                String name = communication.getName();
                User user1 = userService.findByName(name);
                user1.setLife(life);
                userService.updateData(Values.USERDAO, user1);
                break;
            case LIFE_R:
                Life life1 = userService.getLife(communication.getName());
                Message message2 = createLifeMessage(life1);
                communication.write(message2);
                break;
            case LIFE_U:
                Life life2 = creatLife(map);
                userService.updateData(Values.LIFEDAO,life2);
                break;
            case LIFE_D:
                Life life3 = userService.getLife(communication.getName());
                userService.removeData(Values.LIFEDAO,life3);
                break;
            case DEATH_C:
                Death death = creatDeath(map);
                userService.addData(Values.DEATHDAO,death);
                String name1 = communication.getName();
                User user2 = userService.findByName(name1);
                user2.setDeath(death);
                userService.updateData(Values.USERDAO, user2);
                System.out.println("id dos mortos: " + death.getId());
                break;
            case DEATH_R:
                Death death1 = userService.getDeath(communication.getName());
                Message message3 = createDeathMessage(death1);
                communication.write(message3);
                break;
            case DEATH_U:
                System.out.println("death_u");
                Death death2 = creatDeath(map);
                System.out.println("abc");
                System.out.println("death " + death2.toString());
                userService.updateData(Values.DEATHDAO,death2);
                break;
            case DEATH_D:
                Death death3 = userService.getDeath(communication.getName());
                userService.removeData(Values.DEATHDAO,death3);
                System.out.println(userService.findByName(communication.getName()).getDeath());
                break;
        }

    }

    private Message createDeathMessage(Death death1) {
        HashMap<String,String> map = new HashMap<>();

        if(death1 != null){


        map.put(Values.BODYTREAMENT,death1.getBodyTreatment());
        map.put(Values.RELIGION,death1.getReligion());
        map.put(Values.FOOD,death1.getFood());
        map.put(Values.MUSIC,death1.getMusic());
        map.put(Values.NUMBER_OF_GUESTS,death1.getNumberOfGuests());
        map.put(Values.SPECIAL_REQUEST, death1.getSpecialRequest());
        map.put(Values.BUDGET, death1.getBudget());
        }

        return new Message(type,map);
    }

    private Death creatDeath(HashMap<String, String> map) {
        String[] prop = new String[map.size()];
        int i = 0;

        for (String s:map.keySet()) {
            prop[i] = map.get(s);
            i++;
        }

        String bodyTreatment = map.get(Values.BODYTREAMENT);
        String religion = map.get(Values.RELIGION);
        String food = map.get(Values.FOOD);
        String music = map.get(Values.MUSIC);
        String numberOfGuests = map.get(Values.NUMBER_OF_GUESTS);
        String specialRequest = map.get(Values.SPECIAL_REQUEST);
        String budget = map.get(Values.BUDGET);

        Death death = new Death(bodyTreatment,religion,food,music,numberOfGuests,specialRequest,budget);
        return death;

    }

    private Life creatLife(HashMap<String, String> map) {
        String[] prop = new String[map.size()];
        int i = 0;

        for (String s:map.keySet()) {
            prop[i] = map.get(s);
            i++;
        }

        Life life = new Life(prop[0], prop[1], prop[2], prop[3], prop[4], prop[5], prop[6], prop[7], prop[8] );
        return life;
    }

    private Message createLifeMessage(Life life1) {

        HashMap<String,String> map = new HashMap<>();
        map.put(Values.P1,life1.getProp1());
        map.put(Values.P2,life1.getProp2());
        map.put(Values.P3,life1.getProp3());
        map.put(Values.P4,life1.getProp4());
        map.put(Values.P5,life1.getProp5());
        map.put(Values.P6,life1.getProp6());
        map.put(Values.P7,life1.getProp7());
        map.put(Values.P8,life1.getProp8());
        map.put(Values.P9,life1.getProp9());

        return new Message(type,map);

    }
}
