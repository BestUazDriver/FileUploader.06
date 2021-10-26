package com.sabitov.services;

import java.io.InputStream;
import java.io.OutputStream;

public interface FilesService {
    void upload (String fileName, InputStream stream);
    void download (String fileName, OutputStream outputStream);
}
