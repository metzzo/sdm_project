package controller.json;

import domain.Log;
import domain.Operation;
import service.OperationDAO;

import java.util.Date;

/**
 * Created by rfischer on 14.05.16.
 */
public class LogJson {
    private boolean success = true;
    private Long id;
    private Date callingTime;
    private Date callingEndTime;
    private OperationJson operation;

    public LogJson(Log log) {
        this.id = log.getId();
        this.callingEndTime = log.getCallingEndTime();
        this.callingTime = log.getCallingTime();
        this.operation = log.getOperation() != null ? new OperationJson(log.getOperation()) : null;
    }

    public Log toLog() {
        return new Log(callingTime, callingEndTime, operation != null ? operation.toOperation() : null, id);
    }
}
