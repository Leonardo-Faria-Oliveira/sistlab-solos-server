package com.example.sistlabsolos.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.file.Files;

public class FileToMultipartFile implements MultipartFile {

    private final File file;
    private final String originalFilename;
    private final String contentType;

    public FileToMultipartFile(File file) {
        this.file = file;
        this.originalFilename = file.getName();
        this.contentType = determineContentType(file);
    }

    private static String determineContentType(File file) {
        if (file == null) {
            System.out.println("File object is null.");
        } else {
            System.out.println("File object is not null. Path: " + file.getAbsolutePath());
        }
        try {
            return Files.probeContentType(file.toPath());
        } catch (IOException e) {
            System.err.println("Failed to determine content type for " + file.getAbsolutePath());
            e.printStackTrace();
            return "application/octet-stream";
        }
    }

    @Override
    public String getName() {
        return StringUtils.cleanPath(file.getName());
    }

    @Override
    public String getOriginalFilename() {
        return originalFilename;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return (this.file.length() == 0);
    }

    @Override
    public long getSize() {
        return file.length();
    }

    @Override
    public byte[] getBytes() throws IOException {
        return Files.readAllBytes(file.toPath());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        Files.copy(this.file.toPath(), dest.toPath());
    }

    // Implementação de outros métodos necessários pela interface, se aplicável.
}