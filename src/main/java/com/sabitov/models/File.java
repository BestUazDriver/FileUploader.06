package com.sabitov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class File {
    private int id;
    private int size;
    private String originalFileName;
    private String fileName;
    private String mimeType;
}
