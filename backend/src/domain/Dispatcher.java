package domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by rfischer on 11.05.16.
 */

@Entity
@Table(name = "sdm_dispatcher")
public class Dispatcher extends User {
    public Dispatcher(String uname, String pw, String email, Long id) {
        super(uname, pw, email, id);
    }

    public Dispatcher() {
    }
}
