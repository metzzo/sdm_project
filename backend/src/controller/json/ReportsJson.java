package controller.json;

import domain.Report;

import java.util.List;

/**
 * Created by rfischer on 14.05.16.
 */
public class ReportsJson {
    private List<ReportJson> reports;
    private boolean success = true;


    public ReportsJson(List<ReportJson> reports) {
        this.reports = reports;
    }
}
