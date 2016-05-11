package service;

import domain.Dispatcher;
import domain.User;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by rfischer on 25.04.16.
 */
@Stateless
public class UserDAO {
    @PersistenceContext(unitName = "dbaccess")
    EntityManager em;

    public static UserDAO getInstance() {
        try {
            return (UserDAO) new InitialContext().lookup("java:module/UserDAO");
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserDAO() {

    }

    public User register(User user) {
        try {
            if (doesUsernameExist(user.getUname())) {
                return null;
            }

            em.persist(user);
            em.flush();

            return user;
        } catch (Exception ex) {
            System.err.println(ex);
            return null;
        }
    }

    public User login(User temporaryUser) {
        try {
            User u = (User)em
                    .createQuery("select u from User u where u.uname = :uname and u.pw = :pw")
                    .setParameter("uname", temporaryUser.getUname())
                    .setParameter("pw", temporaryUser.getPw())
                    .getSingleResult();
            return u;
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean doesUsernameExist(String uname) {
        try {
            int count =
                    ((Number)em
                            .createQuery("select count(u) from User u where u.uname = :uname")
                            .setParameter("uname", uname)
                            .getSingleResult()).intValue();

            return count > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void deleteAll() {
        try {
            em.createQuery("delete from User u").executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Dispatcher findDispatcherById(long id) {
        return em.find(Dispatcher.class, id);
    }
}
