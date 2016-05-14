package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.json.ReportJson;
import controller.json.ReportsJson;
import controller.json.StatusResponseJson;
import domain.EmergencyUnit;
import domain.Report;
import service.ReportDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rfischer on 14.05.16.
 */
@WebServlet(name = "ManageReportController", urlPatterns = {"/manageReportController"})
public class ManageReportController extends BaseServlet {
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        boolean success = false;
        Gson gson = new GsonBuilder().create();
        if (getUser() != null) {
            try {
                switch (getRequestType()) {
                    case POST: {
                        if (getUser() instanceof EmergencyUnit) {
                            Report report = gson.fromJson(getBody(), ReportJson.class).toReport();
                            report.setLastModification(new Date());
                            report.setLastUnit((EmergencyUnit) getUser());
                            ReportDAO.getInstance().persist(report);
                            success = true;
                        }
                        break;
                    }
                    case PUT: {

                        break;
                    }
                    case GET: {
                        List<Report> reports = ReportDAO.getInstance().findReportsForUser(getUser());
                        List<ReportJson> jsonReports = new ArrayList<ReportJson>();
                        for (Report report : reports) {
                            jsonReports.add(new ReportJson(report));
                        }
                        response(gson.toJson(new ReportsJson(jsonReports), ReportsJson.class));
                        return;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        response(gson.toJson(new StatusResponseJson(success), StatusResponseJson.class));
    }
}
