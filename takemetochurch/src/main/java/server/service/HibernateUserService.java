package server.service;


import server.model.User;
import server.model.dao.UserDao;
import server.persistence.TransactionException;
import server.persistence.hibernate.HibernateTransactionManager;

/**
 * Created by Cyrille on 13/07/17.
 */
public class HibernateUserService implements UserService{

    private UserDao userDao;
    private HibernateTransactionManager transactionManager;

    public HibernateUserService(UserDao userDao, HibernateTransactionManager transactionManager) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean authenticate(String username, String password) {

        User user = findByName(username);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public void addUser(User user) {

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
    public String getName() {
        return UserService.class.getSimpleName();
    }
}
