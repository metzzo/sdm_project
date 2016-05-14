package service;

import domain.EmergencyUnit;
import domain.Log;
import domain.User;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by rfischer on 14.05.16.
 */
@Stateless
public class LogDAO {
    @PersistenceContext(unitName = "dbaccess")
    EntityManager em;

    public static LogDAO getInstance() {
        try {
            return (LogDAO) new InitialContext().lookup("java:module/LogDAO");
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public LogDAO() {
    }

    public void persist(Log log) {
        if (log.getId() == null) {
            em.persist(log);

        } else {
            em.merge(log);
        }
        em.flush();
    }

    public Log findLogById(Long id) {
        return em.find(Log.class, id);
    }

    public List<Log> findLogsForUser(User user) {
        if (user instanceof EmergencyUnit) {
            return em
                    .createQuery("select l from Log l where l.operation.assignedUnit = :unit")
                    .setParameter("unit", user)
                    .getResultList();
        } else {
            return em
                    .createQuery("select l from Log l")
                    .getResultList();
        }
    }
}
