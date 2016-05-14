package service;

import domain.Dispatcher;
import domain.EmergencyUnit;
import domain.Report;
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
public class ReportDAO {
    @PersistenceContext(unitName = "dbaccess")
    EntityManager em;

    public static ReportDAO getInstance() {
        try {
            return (ReportDAO) new InitialContext().lookup("java:module/ReportDAO");
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ReportDAO() {
    }

    public void persist(Report report) {
        if (report.getId() == null) {
            em.persist(report);

        } else {
            em.merge(report);
        }
        em.flush();
    }

    public List<Report> findReportsForUser(User user) {
        if (user instanceof EmergencyUnit) {
            return em
                    .createQuery("select o.report from Operation o where o.assignedUnit = :unit")
                    .setParameter("unit", user)
                    .getResultList();
        } else {
            return em.createQuery("select o.report from Operation o").getResultList();
        }
    }

    public Report findReportById(Long reportId) {
        return em.find(Report.class, reportId);
    }
}