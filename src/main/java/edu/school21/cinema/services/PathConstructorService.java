package edu.school21.cinema.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

@Service
public class PathConstructorService {

    @Value("${storage.path}")
    private String storagePath;

    public String constructPathString(String file) {
        StringBuilder sb = new StringBuilder(storagePath);
        if (!storagePath.endsWith(File.separator)) {
            sb.append(File.separator);
        }
        sb.append(file);
        return sb.toString();
    }

    public String constructPathString(UUID uuid) {
        return constructPathString(uuid.toString());
    }

    public String getStoragePath() {
        return storagePath;
    }
}
