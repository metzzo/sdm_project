package controller.json;

import com.google.gson.JsonElement;
import domain.EmergencyUnit;
import domain.Log;
import domain.Operation;
import domain.OperationType;
import service.UserDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rfischer on 14.05.16.
 */
public class LogsJson {
    public static class Workload {
        private UserJson user;
        private int amount;

        public Workload(UserJson user, int amount) {
            this.user = user;
            this.amount = amount;
        }
    }

    private boolean success = true;
    private List<LogJson> logs;

    private int countOfCallsFire;
    private int countOfCallsMedical;
    private int countOfCallsPolice;
    private int countOfCallsTotal;

    private List<Workload> workload;

    public LogsJson(List<LogJson> jsonLogs) {
        this.logs = jsonLogs;

        workload = new ArrayList<Workload>();

        countOfCallsTotal = jsonLogs.size();

        countOfCallsFire = 0;
        countOfCallsMedical = 0;
        countOfCallsPolice = 0;

        Map<EmergencyUnit, Integer> counts = new HashMap<EmergencyUnit, Integer>();

        for (LogJson jsonLog : jsonLogs) {
            Operation operation = jsonLog.toLog().getOperation();
            if (operation != null) {
                switch (operation.getType()) {
                    case FireEmergency:
                        countOfCallsFire++;
                        break;
                    case MedicalEmergency:
                        countOfCallsMedical++;
                        break;
                    case PoliceEmergency:
                        countOfCallsPolice++;
                        break;
                }

                if (operation.getAssignedUnit() != null) {
                    if (!counts.containsKey(operation.getAssignedUnit())) {
                        counts.put(operation.getAssignedUnit(), 0);
                    }
                    counts.put(operation.getAssignedUnit(), 1 + counts.get(operation.getAssignedUnit()));
                }
            }
        }

        for (EmergencyUnit unit : counts.keySet()) {
            Integer count = counts.get(unit);
            workload.add(new Workload(new UserJson(unit), count));
        }
    }
}
