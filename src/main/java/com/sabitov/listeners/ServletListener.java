package com.sabitov.listeners;

import com.sabitov.conf.ApplicationConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletListener implements ServletContextListener {
    private ApplicationContext springContext;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        springContext=new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ServletContext servletContext=servletContextEvent.getServletContext();
        servletContext.setAttribute("springContext", springContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        HikariDataSource hikariDataSource=springContext.getBean(HikariDataSource.class);
        hikariDataSource.close();
    }
}
