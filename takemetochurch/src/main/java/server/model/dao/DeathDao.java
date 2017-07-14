package server.model.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.model.Death;
import server.persistence.TransactionException;
import server.persistence.hibernate.HibernateSessionManager;

/**
 * Created by Cyrille on 13/07/17.
 */
public class DeathDao implements Dao<Death> {

    @Override
    public void create(Death data) {

        try {

            Session session = getSession();
            session.save(data);

        } catch (HibernateException e) {
            throw new TransactionException(e.getMessage());
        }
    }

    @Override
    public Death read(long id) {
        Death death;

        try {
            Session session = getSession();
            death = (Death) session.createCriteria(Death.class).add(Restrictions.
                    like("id", id)).uniqueResult();


        } catch (HibernateException e) {
            throw new TransactionException("Was impossible to find by id");
        }
        return death;
    }

    @Override
    public void update(Death data) {

        try {
            Session session = getSession();
            session.update(data);

        } catch (HibernateException e) {
            throw new TransactionException("Was impossible update");
        }
    }

    @Override
    public void delete(Death data) {

        try {
            Session session = getSession();
            session.delete(data);

        } catch (HibernateException e) {
            throw new TransactionException("Was impossible to deleteById");
        }
    }

    @Override
    public Death findById(Long id) throws TransactionException {
        Death death;

        try {
            Session session = getSession();
            death = (Death) session.createCriteria(Death.class).add(Restrictions.
                    like("id", id)).uniqueResult();


        } catch (HibernateException e) {
            throw new TransactionException("Was impossible to find by id");
        }
        return death;
    }

    private Session getSession() {
        return HibernateSessionManager.getInstance().getSession();
    }
}
