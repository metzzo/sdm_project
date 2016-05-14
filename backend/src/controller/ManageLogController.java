package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.json.LogJson;
import controller.json.LogsJson;
import controller.json.OperationJson;
import controller.json.StatusResponseJson;
import domain.Dispatcher;
import domain.Log;
import domain.Operation;
import service.LogDAO;
import service.OperationDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rfischer on 14.05.16.
 */
@WebServlet(name = "ManageLogController", urlPatterns = {"/manageLogController"})
public class ManageLogController extends BaseServlet {
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        boolean success = false;
        Gson gson = new GsonBuilder().create();
        if (getUser() != null && (getUser() instanceof Dispatcher)) {
            try {
                switch (getRequestType()) {
                    case POST: {
                        Log log = new Log();
                        log.setCallingTime(new Date());
                        LogDAO.getInstance().persist(log);
                        response(gson.toJson(new LogJson(log), LogJson.class));
                        return;
                    }
                    case PUT: {

                        break;
                    }
                    case GET: {
                        List<Log> logs = LogDAO.getInstance().findLogsForUser(getUser());
                        List<LogJson> jsonLogs = new ArrayList<LogJson>();
                        for (Log l : logs) {
                            jsonLogs.add(new LogJson(l));
                        }
                        response(gson.toJson(new LogsJson(jsonLogs), LogsJson.class));
                        break;
                    }
                    default:
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        response(gson.toJson(new StatusResponseJson(success), StatusResponseJson.class));
    }
}
