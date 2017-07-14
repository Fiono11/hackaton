package server.persistence.hibernate;

/**
 * Created by Cyrille on 13/07/17.
 */
public class HibernateTransactionManager {

    private HibernateSessionManager hibernateSessionManager;

    public HibernateTransactionManager() {
        hibernateSessionManager = HibernateSessionManager.getInstance();
    }

    public void transaction() {
        hibernateSessionManager.getSession().beginTransaction();
    }

    public void commit() {
        hibernateSessionManager.getSession().getTransaction().commit();
    }

    public void rollback() {
        hibernateSessionManager.getSession().getTransaction().rollback();
    }
}
