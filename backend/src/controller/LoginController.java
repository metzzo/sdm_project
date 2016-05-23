package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.json.StatusResponseJson;
import controller.json.UserJson;
import domain.User;
import service.UserDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by rfischer on 10.05.16.
 */
@WebServlet(name = "LoginController", urlPatterns = {"/loginController"})
public class LoginController extends BaseServlet {
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        boolean success = false;
        try {
            User user = gson.fromJson(getBody(), UserJson.class).toUser();

            user = UserDAO.getInstance().login(user);

            if (user != null) {
                request.getSession().setAttribute("user", user);
                success = true;
                response(gson.toJson(new UserJson(user), UserJson.class));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (!success) {
            response(gson.toJson(new StatusResponseJson(success), StatusResponseJson.class));
        }
    }
}
