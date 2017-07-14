package server.service;


import server.model.Data;
import server.model.Death;
import server.model.Life;
import server.model.User;
import server.model.dao.DeathDao;
import server.model.dao.LifeDao;
import server.model.dao.UserDao;
import server.persistence.TransactionException;
import server.persistence.hibernate.HibernateTransactionManager;
import shared.Values;

/**
 * Created by Cyrille on 13/07/17.
 */
public class HibernateUserService {

    private LifeDao lifeDao;
    private DeathDao deathDao;
    private UserDao userDao;
    private HibernateTransactionManager transactionManager;

    public HibernateUserService() {

        this.lifeDao = new LifeDao();
        this.userDao = new UserDao();
        this.deathDao = new DeathDao();
        this.transactionManager = new HibernateTransactionManager();
    }

    public boolean authenticate(String daoType, String username, String password) {

        User user = findByName(username);
        if (user != null && user.getPassword().equals(password)) {
            //System.out.println("estou auth");
            return true;
        }
        return false;
    }

    public <T extends Data> void addData(String daoName, T data) {

        System.out.println("im in userservice");
        System.out.println(data);
        try {
            transactionManager.transaction();

            switch (daoName) {
                case Values.USERDAO:
                    userDao.create((User) data);
                    transactionManager.commit();
                    break;

                case Values.DEATHDAO:
                    deathDao.create((Death) data);
                    transactionManager.commit();
                    break;

                case Values.LIFEDAO:
                    lifeDao.create((Life) data);
                    transactionManager.commit();
                    break;
            }

        } catch (TransactionException e) {
            transactionManager.rollback();
            e.printStackTrace();
        }
    }

    public User findByName(String username) {
        User user = null;
        try {
            transactionManager.transaction();
            user = userDao.findByName(username);
            transactionManager.commit();

        } catch (TransactionException e) {
            transactionManager.rollback();
            e.printStackTrace();
        }
        return user;
    }

    public <T extends Data> T findById(String daoName, int number) {
        Data data = null;

        try {
            transactionManager.transaction();
            switch (daoName){
                case Values.USERDAO:
                    data = userDao.findById((long) number);
                    break;
                case Values.DEATHDAO:
                    data = deathDao.findById((long) number);
                    break;

                case Values.LIFEDAO:
                    data = lifeDao.findById((long) number);
                    break;
            }
            transactionManager.commit();

        } catch (TransactionException e) {
            transactionManager.rollback();
            e.printStackTrace();
        }
        return (T) data;
    }

    public <T extends Data> void removeData(String daoName, T data) {

        try {
            transactionManager.transaction();
            switch (daoName) {
                case Values.USERDAO:
                    userDao.delete((User) data);
                    transactionManager.commit();
                    break;

                case Values.DEATHDAO:
                    deathDao.delete((Death) data);
                    transactionManager.commit();
                    break;

                case Values.LIFEDAO:
                    lifeDao.delete((Life) data);
                    transactionManager.commit();
                    break;
            }
        } catch (TransactionException e) {
            transactionManager.rollback();
            e.printStackTrace();
        }
    }

    public <T extends Data> void updateData(String daoName, T data) {

        try {
            transactionManager.transaction();
            switch (daoName) {
                case Values.USERDAO:
                    userDao.update((User) data);
                    transactionManager.commit();
                    break;

                case Values.DEATHDAO:
                    deathDao.update((Death) data);
                    transactionManager.commit();
                    break;

                case Values.LIFEDAO:
                    lifeDao.update((Life) data);
                    transactionManager.commit();
                    break;
            }
        } catch (TransactionException e) {
            transactionManager.rollback();
            e.printStackTrace();
        }
    }

    public Life getLife(String name) {
        Life life = null;
        try {

        transactionManager.transaction();

        User user = userDao.findByName(name);

        life = user.getLife();

        transactionManager.commit();
        }catch (TransactionException e){
            transactionManager.rollback();
        }

        return life;
    }

    public Death getDeath(String name) {
        Death death = null;
        try {

            transactionManager.transaction();

            User user = userDao.findByName(name);

            death = user.getDeath();

            transactionManager.commit();
        }catch (TransactionException e){
            transactionManager.rollback();
        }

        return death;
    }
}
