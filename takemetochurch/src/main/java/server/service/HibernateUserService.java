package server.service;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.model.User;
import server.persistence.HibernateSessionManager;

import java.util.List;

/**
 * Created by Cyrille on 13/07/17.
 */
public class HibernateUserService implements UserService{

    private HibernateSessionManager sessionManager;

    public HibernateUserService() {
        sessionManager = HibernateSessionManager.getInstance();
    }

    @Override
    public boolean authenticate(String username, String password) {

        boolean authenticate = false;

        try {
            Session session = sessionManager.beginTransaction();

            Query query = session.createQuery("FROM User WHERE username = :username AND password = :password");

            query.setString("username", username);
            query.setString("password", password);

            User user = (User) query.uniqueResult();

            if (user != null) {
                authenticate = true;
            }

            sessionManager.commitTransaction();

        } catch (HibernateException e) {
            e.printStackTrace();
            sessionManager.rollbackTransaction();
        }
        return authenticate;
    }

    @Override
    public void addUser(User user) {

        String username = user.getUsername();

        if (findByName(username) != null) {
            return;
        }

        try {
            Session session = sessionManager.beginTransaction();

            session.save(user);
            sessionManager.commitTransaction();

        } catch (HibernateException e) {
            e.printStackTrace();
            sessionManager.rollbackTransaction();
        }
    }

    @Override
    public User findByName(String username) {

        try {

            Session session = sessionManager.beginTransaction();

            User user = (User) session.createCriteria(User.class).
                    add(Restrictions.like("username", username)).uniqueResult();

            if (user != null) {

                sessionManager.commitTransaction();
                return user;
            }

        } catch (HibernateException e) {
            e.printStackTrace();
            sessionManager.rollbackTransaction();
        }

        return null;
    }

    @Override
    public String getName() {
        return UserService.class.getSimpleName();
    }
}
