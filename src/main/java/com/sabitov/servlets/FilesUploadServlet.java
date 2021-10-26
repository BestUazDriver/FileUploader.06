package com.sabitov.servlets;

import com.sabitov.models.File;
import com.sabitov.repositories.FileRep;
import com.sabitov.services.FilesService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/fileUploader")
@MultipartConfig
public class FilesUploadServlet extends HttpServlet {
    private FileRep fileRep;
    private FilesService filesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.filesService = applicationContext.getBean(FilesService.class);
        this.fileRep = applicationContext.getBean(FileRep.class);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/fileUploader.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part descriptionPart = req.getPart("file");
        File file = File.builder()
                .size((int) descriptionPart.getSize())
                .mimeType(descriptionPart.getContentType())
                .originalFileName(descriptionPart.getSubmittedFileName())
                .fileName(descriptionPart.getName())
                .build();
        filesService.upload(descriptionPart.getSubmittedFileName(), descriptionPart.getInputStream());
        fileRep.save(file);

    }
}
