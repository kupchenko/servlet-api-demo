package me.kupchenko.test2.attributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/attributes")
public class AttributesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name");
        req.setAttribute("username", (username == null) ? "mister" : username);
        req.setAttribute("parameters", req.getParameterNames());
        req.getRequestDispatcher("/WEB-INF/attributes.jsp").forward(req, resp);
    }
}
