package controller.json;

import domain.*;
import service.OperationDAO;
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

    public Operation toOperation() {
        OperationType opType;
        OperationPriority opPrio;
        Address address;
        Dispatcher dispatcher;

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

        return new Operation(opType, opPrio, address, who, what, additionalInfo, dispatcher);
    }
}
