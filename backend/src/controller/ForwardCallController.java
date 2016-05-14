package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.json.OperationJson;
import controller.json.OperationsJson;
import controller.json.ReportJson;
import controller.json.StatusResponseJson;
import domain.EmergencyUnit;
import domain.Operation;
import domain.Report;
import service.OperationDAO;
import service.ReportDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by rfischer on 13.05.16.
 */
@WebServlet(name = "ForwardCallController", urlPatterns = {"/forwardCallController"})
public class ForwardCallController extends BaseServlet {
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        boolean success = false;
        Gson gson = new GsonBuilder().create();
        if (getUser() != null && (getUser() instanceof EmergencyUnit)) {
            try {
                switch (getRequestType()) {
                    case POST: {
                        Operation op = gson.fromJson(getBody(), OperationJson.class).toOperation();
                        op.setAssignedUnit((EmergencyUnit)getUser());
                        OperationDAO.getInstance().persist(op);
                        success = true;
                        break;
                    }
                    case PUT: {
                        Operation op = gson.fromJson(getBody(), OperationJson.class).toOperation();

                        Report report = new Report("", new Date(), op.getAssignedUnit(), null);
                        op.setReport(report);

                        ReportDAO.getInstance().persist(report);
                        OperationDAO.getInstance().persist(op);

                        response(gson.toJson(new ReportJson(report), ReportJson.class));
                        return;
                    }
                    case GET: {
                        List<Operation> operations = OperationDAO.getInstance().findOperationsAvailableForUnit((EmergencyUnit) getUser());
                        Collections.sort(operations, new Comparator<Operation>() {
                            @Override
                            public int compare(Operation o1, Operation o2) {
                                return o1.getPriority().getNum() - o2.getPriority().getNum();
                            }
                        });
                        List<OperationJson> jsonOperations = new ArrayList<OperationJson>();
                        for (Operation op : operations) {
                            jsonOperations.add(new OperationJson(op));
                        }
                        response(gson.toJson(new OperationsJson(jsonOperations), OperationsJson.class));
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