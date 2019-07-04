package me.kupchenko;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class AnnotationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        req.getSession();
        req.getCookies();
        req.getParameter("");
        req.getAttribute("");
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, response);
    }
}
