package controller;

import domain.Dispatcher;
import domain.User;
import service.UserDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by rfischer on 11.05.16.
 */
@WebServlet(name = "TestDataController", urlPatterns = {"/testDataController"})
public class TestDataController extends BaseServlet {
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        UserDAO.getInstance().deleteAll();

        User u = new Dispatcher("robert", "86f7e437faa5a7fce15d1ddcb9eaeaea377667b8", "asdf@asdf.com", null);
        UserDAO.getInstance().register(u);


    }
}
