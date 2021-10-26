package com.sabitov;

import com.sabitov.conf.ApplicationConfig;
import com.sabitov.models.File;
import com.sabitov.repositories.FileRep;
import com.sabitov.repositories.FileRepImpl;
import com.sabitov.services.FilesService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContext;

public class Main {
    public static void main(String[] args) {


        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        FileRepImpl fileRep=context.getBean(FileRepImpl.class);
        File file = fileRep.findByName("i.jpg").get();
        System.out.println(file);
    }
}
