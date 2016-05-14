package controller.json;

import domain.EmergencyUnit;
import domain.Report;
import service.UserDAO;

import java.util.Date;

/**
 * Created by rfischer on 14.05.16.
 */
public class ReportJson {
    private boolean success = true;
    private Long id;
    private String reportText;
    private Date lastModification;
    private Long lastUnitId;
    private String userName;

    public ReportJson(Report report) {
        this.id = report.getId();
        this.reportText = report.getText();
        this.lastModification = report.getLastModification();
        try {
            this.lastUnitId = report.getLastUnit().getId();
            this.userName = report.getLastUnit().getUname();
        } catch (Exception ex) {

        }
    }

    public Report toReport() {
        return new Report(reportText, lastModification, lastUnitId != null ? UserDAO.getInstance().findEmergencyUnitById(lastUnitId) : null, id);
    }
}
