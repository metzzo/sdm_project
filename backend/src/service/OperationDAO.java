package service;

import domain.Operation;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by rfischer on 11.05.16.
 */
@Stateless
public class OperationDAO {
    @PersistenceContext(unitName = "dbaccess")
    EntityManager em;

    public static OperationDAO getInstance() {
        try {
            return (OperationDAO) new InitialContext().lookup("java:module/DispatcherDAO");
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public OperationDAO() {

    }

    public void persist(Operation op) {
        em.persist(op);
        em.flush();
    }
}
