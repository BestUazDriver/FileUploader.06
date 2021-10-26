package com.sabitov.repositories;

import com.sabitov.models.File;

import java.util.Optional;

public interface FileRep {
    void save (File file);
    Optional<File> findByName(String name);
}
