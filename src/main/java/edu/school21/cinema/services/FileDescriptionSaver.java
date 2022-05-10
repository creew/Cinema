package edu.school21.cinema.services;

import edu.school21.cinema.models.FileDescription;
import edu.school21.cinema.repositories.FileDescriptionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class FileDescriptionSaver {

    private final FileDescriptionRepository fileDescriptionRepository;

    private final String storagePath;

    public FileDescriptionSaver(FileDescriptionRepository fileDescriptionRepository,
                                @Value("${storage.path}") String storagePath) {
        this.fileDescriptionRepository = fileDescriptionRepository;
        this.storagePath = storagePath;
    }

    public Path getFilePath(UUID uuid) {
        return Paths.get(storagePath + File.separator + uuid);
    }

    public FileDescription writeImage(UUID image, OutputStream outputStream) throws IOException {
        FileDescription fileDescription = fileDescriptionRepository.findById(image);
        if (fileDescription != null) {
            Path path = getFilePath(image);
            Files.copy(path, outputStream);
            return fileDescription;
        }
        throw new EntityNotFoundException("Image with id: " + image + " not found");
    }

    public FileDescription saveFile(String filename, String contentType, InputStream is) {
        UUID uuid = UUID.randomUUID();
        long size = saveFile(is, uuid);
        FileDescription fileDescription = new FileDescription();
        fileDescription.setId(uuid);
        fileDescription.setContentType(contentType);
        fileDescription.setFilename(filename);
        fileDescription.setSize(size);
        fileDescriptionRepository.save(fileDescription);
        return fileDescription;
    }

    private long saveFile(InputStream is, UUID name) {
        File file = new File(storagePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            return Files.copy(is, getFilePath(name), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
