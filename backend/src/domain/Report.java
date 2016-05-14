package domain;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by rfischer on 14.05.16.
 */
@Entity
@Table(name = "sdm_report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private Date lastModification;

    @ManyToOne
    private EmergencyUnit lastUnit;

    public Report() {
    }

    public Report(String text, Date lastModification, EmergencyUnit lastUnit, Long id) {
        this.text = text;
        this.lastModification = lastModification;
        this.lastUnit = lastUnit;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getLastModification() {
        return lastModification;
    }

    public void setLastModification(Date lastModification) {
        this.lastModification = lastModification;
    }

    public EmergencyUnit getLastUnit() {
        return lastUnit;
    }

    public void setLastUnit(EmergencyUnit lastUnit) {
        this.lastUnit = lastUnit;
    }
}
