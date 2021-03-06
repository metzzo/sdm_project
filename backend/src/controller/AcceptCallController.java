package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.json.LogJson;
import controller.json.OperationJson;
import controller.json.ReportJson;
import controller.json.StatusResponseJson;
import domain.Dispatcher;
import domain.Log;
import domain.Operation;
import service.LogDAO;
import service.OperationDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by rfischer on 11.05.16.
 */
@WebServlet(name = "AcceptCallController", urlPatterns = {"/acceptCallController"})
public class AcceptCallController extends BaseServlet {
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        boolean success = false;
        Gson gson = new GsonBuilder().create();
        if (getUser() != null && (getUser() instanceof Dispatcher)) {
            try {
                switch (getRequestType()) {
                    case POST: {
                        Operation op = gson.fromJson(getBody(), OperationJson.class).toOperation();
                        op.getLog().setCallingEndTime(new Date());
                        op.getLog().setOperation(op);
                        OperationDAO.getInstance().persist(op);
                        LogDAO.getInstance().persist(op.getLog());
                        success = true;
                        break;
                    }
                    case PUT: {

                        break;
                    }
                    case GET: {

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
