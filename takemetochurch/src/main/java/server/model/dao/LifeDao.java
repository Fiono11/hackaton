package server.model.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import server.model.Life;
import server.persistence.TransactionException;
import server.persistence.hibernate.HibernateSessionManager;

/**
 * Created by Cyrille on 13/07/17.
 */
public class LifeDao implements Dao<Life> {

    @Override
    public void create(Life data) {

        try {

            Session session = getSession();
            session.save(data);

        } catch (HibernateException e) {
            throw new TransactionException(e.getMessage());
        }
    }

    @Override
    public Life read(long id) {
        Life life;

        try {
            Session session = getSession();
            life = (Life) session.createCriteria(Life.class).add(Restrictions.
                    like("id", id)).uniqueResult();


        } catch (HibernateException e) {
            throw new TransactionException("Was impossible to find by id");
        }
        return life;
    }

    @Override
    public void update(Life data) {

        try {
            Session session = getSession();
            session.update(data);

        } catch (HibernateException e) {
            throw new TransactionException("Was impossible update");
        }
    }

    @Override
    public void delete(Life data) {

        try {
            Session session = getSession();
            session.delete(data);

        } catch (HibernateException e) {
            throw new TransactionException("Was impossible to delete");
        }
    }

    @Override
    public Life findById(Long id) throws TransactionException {
        Life life;

        try {
            Session session = getSession();
            life = (Life) session.createCriteria(Life.class).add(Restrictions.
                    like("id", id)).uniqueResult();


        } catch (HibernateException e) {
            throw new TransactionException("Was impossible to find by id");
        }
        return life;
    }

    private Session getSession() {
        return HibernateSessionManager.getInstance().getSession();
    }
}
