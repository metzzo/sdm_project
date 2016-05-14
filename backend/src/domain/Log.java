package domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rfischer on 14.05.16.
 */
@Entity
@Table(name="sdm_log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date callingTime;

    private Date callingEndTime;

    @OneToOne
    private Operation operation;

    public Log(Date callingTime, Date callingEndTime, Operation operation, Long id) {
        this.callingTime = callingTime;
        this.callingEndTime = callingEndTime;
        this.operation = operation;
        this.id = id;
    }

    public Log() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCallingTime() {
        return callingTime;
    }

    public void setCallingTime(Date callingTime) {
        this.callingTime = callingTime;
    }

    public Date getCallingEndTime() {
        return callingEndTime;
    }

    public void setCallingEndTime(Date callingEndTime) {
        this.callingEndTime = callingEndTime;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
