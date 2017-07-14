package server.service;


import server.model.Data;
import server.model.User;
import server.model.dao.Dao;
import server.model.dao.DeathDao;
import server.model.dao.LifeDao;
import server.model.dao.UserDao;
import server.persistence.TransactionException;
import server.persistence.hibernate.HibernateTransactionManager;

/**
 * Created by Cyrille on 13/07/17.
 */
public class HibernateUserService implements UserService{

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

    @Override
    public boolean authenticate(Class<? extends Dao> T, String username, String password) {

        User user = findByName(username);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public <E extends Data> void addUser(Class<? extends Dao> T, User user) {
        try {
            transactionManager.transaction();
            userDao.create(user);
            transactionManager.commit();

        } catch (TransactionException e) {
            transactionManager.rollback();
            e.printStackTrace();
        }
    }

    @Override
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

    @Override
    public <E extends Data> E findById(int number) {
        return null;
    }

    @Override
    public <E extends Data> void removeUSer(Class<? extends Dao> T, E data) {

    }


}
