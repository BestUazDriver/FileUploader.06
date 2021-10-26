package com.sabitov.servlets;

import com.sabitov.models.File;
import com.sabitov.repositories.FileRep;
import com.sabitov.services.FilesService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/file")
public class FilesServlet extends HttpServlet {

    private FilesService filesService;
    private FileRep fileRep;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.filesService = applicationContext.getBean(FilesService.class);
        this.fileRep=applicationContext.getBean(FileRep.class);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName=req.getParameter("fileName");
        File file = fileRep.findByName(fileName).get();
        resp.setContentType(file.getMimeType());
        resp.setContentLengthLong(file.getSize());
        resp.setHeader("Content-Disposition", "filename=\""+file.getOriginalFileName()+"\"");
        filesService.download(fileName, resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
