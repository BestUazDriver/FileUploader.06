package com.sabitov.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FilesServiceImpl implements FilesService {
    @Value("${storage.path}")
    private String storagePath;


    @Override
    public void upload(String fileName, InputStream stream) {
        try {
            Files.copy(stream, Paths.get(storagePath + fileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void download(String fileName, OutputStream outputStream) {
        try {
            Files.copy(Paths.get(storagePath + fileName), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
