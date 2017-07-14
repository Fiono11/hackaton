package server.model.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.model.User;
import server.persistence.TransactionException;
import server.persistence.hibernate.HibernateSessionManager;

/**
 * Created by Cyrille on 13/07/17.
 */
public class UserDao implements Dao<User> {

    @Override
    public void create(User data) {

        try {

            Session session = getSession();
            session.save(data);

        } catch (HibernateException e) {
            throw new TransactionException(e.getMessage());
        }
    }

    @Override
    public User read(long id) {
        User user;

        try {
            Session session = getSession();
            user = (User) session.createCriteria(User.class).add(Restrictions.
                    like("id", id)).uniqueResult();


        } catch (HibernateException e) {
            throw new TransactionException("Was impossible to find by id");
        }
        return user;
    }

    @Override
    public void update(User data) {

        try {
            Session session = getSession();
            session.update(data);

        } catch (HibernateException e) {
            throw new TransactionException("Was impossible update");
        }
    }

    @Override
    public void delete(User data) {

        try {
            Session session = getSession();
            session.delete(data);

        } catch (HibernateException e) {
            throw new TransactionException("Was impossible to delete");
        }
    }

    public User findByName(String username) throws TransactionException {
        User user;

        try {
            Session session = getSession();
            user = (User) session.createCriteria(User.class).add(Restrictions.
                    like("username", username)).uniqueResult();

        } catch (HibernateException e) {
            throw new TransactionException("Was impossible to find by id");
        }
        return user;
    }

    @Override
    public User findById(Long id) throws TransactionException {
        User user;

        try {
            Session session = getSession();
            user = (User)session.createCriteria(User.class).add(Restrictions.
                    like("id", id)).uniqueResult();


        } catch (HibernateException e) {
            throw new TransactionException("Was impossible to find by id");
        }
        return user;
    }

    private Session getSession() {
        return HibernateSessionManager.getInstance().getSession();
    }
}
