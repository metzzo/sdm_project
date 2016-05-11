package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 * Created by rfischer on 22.04.16.
 */
public class DBService {
    private static DBService instance;

    @PersistenceContext
    EntityManagerFactory emf;
    EntityManager em;

    private DBService() {
        emf = Persistence.createEntityManagerFactory("dbaccess");
        em = emf.createEntityManager();
    }

    public static DBService getInstance() {
        if (instance == null) {
            instance = new DBService();
        }
        return instance;
    }

    public EntityManager getManager() {
        return em;
    }
}
