package controller;

import domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by rfischer on 25.04.16.
 */
public abstract class BaseServlet extends javax.servlet.http.HttpServlet {
    private HttpServletRequest request;
    private HttpServletResponse response;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        myProcessRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        myProcessRequest(request, response);
    }

    private void myProcessRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpServletResponse tmpResponse = this.response;
        HttpServletRequest tmpRequest = this.request;

        this.response = response;
        this.request = request;

        response.setContentType("application/json;charset=UTF-8");

        processRequest(request, response);

        this.response = tmpResponse;
        this.request = tmpRequest;
    }

    protected void response(String text) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            out.print(text);
        } finally {
            out.close();
        }
    }

    protected String getBody() {
        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jb.toString();
    }

    /**
     * Returns User object from Session object
     * @return
     */
    public User getUser() {
        Object obj = request.getSession().getAttribute("user");
        return obj == null || !(obj instanceof User) ? null : (User)obj;
    }

    /**
     * Forwards current request to given URL
     * @param url
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public void forward(String url) {
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Called when servlet is opened
     * @param request
     * @param response
     */
    protected abstract void processRequest(HttpServletRequest request, HttpServletResponse response);
}
