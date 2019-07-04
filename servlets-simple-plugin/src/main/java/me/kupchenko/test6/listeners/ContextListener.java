package me.kupchenko.test6.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        String someParam = servletContext.getInitParameter("someParam");
        System.out.println(someParam);
        //START SCHEDULERS/JOBS
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }


}
