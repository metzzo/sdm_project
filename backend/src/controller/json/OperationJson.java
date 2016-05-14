package controller.json;

import domain.*;
import service.LogDAO;
import service.OperationDAO;
import service.ReportDAO;
import service.UserDAO;

/**
 * Created by rfischer on 11.05.16.
 */
public class OperationJson {
    private Long id;
    private Integer type;
    private Integer priority;
    private String street;
    private String nr;
    private String country;
    private String postalcode;
    private String city;
    private String who;
    private String what;
    private String additionalInfo;
    private Long dispatcherId;
    private Long emergencyUnitId;
    private Long reportId;
    private Long logId;

    public OperationJson(Operation op) {
        this.id = op.getId();
        this.type = op.getType().getNum();
        this.priority = op.getPriority().getNum();
        this.street = op.getAddress().getStreet();
        this.nr = op.getAddress().getNr();
        this.country = op.getAddress().getCountry();
        this.postalcode = op.getAddress().getPostalcode();
        this.city = op.getAddress().getCity();
        this.who = op.getWho();
        this.what = op.getWhat();
        this.additionalInfo = op.getAdditionalInfo();
        this.dispatcherId = op.getInitializer().getId();
        this.emergencyUnitId = op.getAssignedUnit() != null ? op.getAssignedUnit().getId() : null;
        this.reportId = op.getReport() != null ? op.getReport().getId() : null;
        this.logId = op.getLog() != null ? op.getLog().getId() : null;
    }

    public Operation toOperation() {
        OperationType opType;
        OperationPriority opPrio;
        Address address;
        Dispatcher dispatcher;
        EmergencyUnit unit;
        Report report;
        Log log;

        switch (priority) {
            case 0: opPrio = OperationPriority.Low; break;
            case 1: opPrio = OperationPriority.Medium; break;
            default: opPrio = OperationPriority.High; break;
        }

        switch (type) {
            case 0: opType = OperationType.FireEmergency; break;
            case 1: opType = OperationType.MedicalEmergency; break;
            default: opType = OperationType.PoliceEmergency; break;
        }

        address = new Address(street, nr, country, postalcode, city, null);

        dispatcher = UserDAO.getInstance().findDispatcherById(dispatcherId);

        unit = emergencyUnitId != null ? UserDAO.getInstance().findEmergencyUnitById(emergencyUnitId) : null;

        report = reportId != null ? ReportDAO.getInstance().findReportById(reportId) : null;

        log = logId != null ? LogDAO.getInstance().findLogById(logId) : null;

        return new Operation(opType, opPrio, address, who, what, additionalInfo, dispatcher, unit, report, log, id);
    }
}
