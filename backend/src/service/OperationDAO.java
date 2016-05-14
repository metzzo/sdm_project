package service;

import domain.EmergencyUnit;
import domain.Operation;
import domain.User;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by rfischer on 11.05.16.
 */
@Stateless
public class OperationDAO {
    @PersistenceContext(unitName = "dbaccess")
    EntityManager em;

    public static OperationDAO getInstance() {
        try {
            return (OperationDAO) new InitialContext().lookup("java:module/OperationDAO");
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public OperationDAO() {

    }

    public void persist(Operation op) {
        if (op.getId() == null) {
            em.persist(op);

        } else {
            em.merge(op);
        }
        em.flush();
    }

    public List<Operation> findOperationsAvailableForUnit(EmergencyUnit user) {
        return em
                .createQuery("select o from Operation o where o.type = :type and o.report = null")
                .setParameter("type", user.getUnitType())
                .getResultList();
    }

    public Operation findOperationById(Long operationId) {
        return em.find(Operation.class, operationId);
    }
}
