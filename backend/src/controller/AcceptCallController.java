package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.json.OperationJson;
import controller.json.StatusResponseJson;
import domain.Operation;
import service.OperationDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by rfischer on 11.05.16.
 */
@WebServlet(name = "AcceptCallController", urlPatterns = {"/acceptCallController"})
public class AcceptCallController extends BaseServlet {
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        boolean success = false;
        try {
            Operation op = gson.fromJson(getBody(), OperationJson.class).toOperation();

            OperationDAO.getInstance().persist(op);

            success = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (!success) {
            response(gson.toJson(new StatusResponseJson(success), StatusResponseJson.class));
        }
    }
}
