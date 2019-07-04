package me.kupchenko.test1.parameters;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/parameters")
public class ParametersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param1 = req.getParameter("param1");
        System.out.println("Param1 == " + param1);
        String[] param2s = req.getParameterValues("param2");
        System.out.printf("Param2 == %s", Arrays.toString(param2s));

        //Do smth with params

        req.getRequestDispatcher("/WEB-INF/parameters.jsp").forward(req, resp);
    }
}
