package domain;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by rfischer on 25.04.16.
 */

@Entity
@Table(name = "sdm_user")
public class User {
    private String uname;
    private String pw;
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User(String uname, String pw, String email, Long id) {
        this.uname = uname;
        this.pw = pw;
        this.email = email;
        this.id = id;
    }

    public User() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (pw != null ? !pw.equals(user.pw) : user.pw != null) return false;
        if (uname != null ? !uname.equals(user.uname) : user.uname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uname != null ? uname.hashCode() : 0;
        result = 31 * result + (pw != null ? pw.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
