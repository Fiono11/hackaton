package server.persistence.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Cyrille on 13/07/17.
 */
public class HibernateSessionManager {

    private static HibernateSessionManager instance;

    private SessionFactory sessionFactory;

    private HibernateSessionManager() {
        try {

            // Hold services needed by Hibernate
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml") // Load settings from hibernate.cfg.xml
                    .build();

            sessionFactory = new MetadataSources(serviceRegistry)
                    .buildMetadata() // Tell Hibernate about sources of metadata (database mappings)
                    .buildSessionFactory();

        } catch (HibernateException ex) {
            throw new ExceptionInInitializerError("Error creating hibernate session factory: " + ex.getMessage());
        }
    }

    public static HibernateSessionManager getInstance() {

        if (instance == null) {

            synchronized (HibernateSessionManager.class) {

                if (instance == null) {
                    instance = new HibernateSessionManager();
                }
            }
        }

        return instance;
    }

    // Hibernate will automatically open a new session if needed
    // Closing the session is not required
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    // Required to stop hibernate and allow the application to terminate
    public void close() {
        sessionFactory.close();
    }
}
