package controller.json;

import domain.Dispatcher;
import domain.EmergencyUnit;
import domain.User;

/**
 * Created by rfischer on 10.05.16.
 */
public class UserJson {
    private Long id;
    private String username;
    private String pw;
    private String email;
    private boolean success;
    private String type;
    private String unitType;

    public UserJson(User user) {
        this.id = user.getId();
        this.username = user.getUname();
        this.pw = user.getPw();
        this.email = user.getEmail();
        this.success = true;
        this.type = user.getClass().getSimpleName();
        this.unitType = (user instanceof EmergencyUnit) ? ((EmergencyUnit)user).getUnitType().toString() : "";
    }

    public User toUser() {
        if (Dispatcher.class.getSimpleName().equals(this.type)) {
            return new Dispatcher(username, pw, email, id);
        } else {
            return new User(username, pw, email, id);
        }
    }
}
