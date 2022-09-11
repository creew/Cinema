package edu.school21.cinema.services;

import edu.school21.cinema.models.entity.FileDescription;
import edu.school21.cinema.repositories.FileDescriptionRepository;
import org.h2.util.IOUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileDescriptionService {

    private final FileDescriptionRepository fileDescriptionRepository;

    private final PathConstructorService pathConstructorService;

    public FileDescriptionService(FileDescriptionRepository fileDescriptionRepository,
                                  PathConstructorService pathConstructorService) {
        this.fileDescriptionRepository = fileDescriptionRepository;
        this.pathConstructorService = pathConstructorService;
    }

    public FileDescription findById(UUID id) {
        return fileDescriptionRepository.findById(id);
    }

    public Path getFilePath(UUID uuid) {
        return Paths.get(pathConstructorService.constructPathString(uuid));
    }

    public String writeImage(UUID image, OutputStream outputStream) throws IOException {
        FileDescription fileDescription = fileDescriptionRepository.findById(image);
        if (fileDescription != null) {
            Path path = getFilePath(image);
            try {
                Files.copy(path, outputStream);
                return fileDescription.getContentType();
            } catch (NoSuchFileException e) {
                try ( InputStream is = getClass().getClassLoader().getResourceAsStream("/images/" + image.toString()) ) {
                    if (is != null) {
                        if (!is.markSupported()) {
                            try ( BufferedInputStream bis = new BufferedInputStream(is) ) {
                                String mimeType= URLConnection.guessContentTypeFromStream(bis);
                                IOUtils.copy(bis, outputStream);
                                return mimeType;
                            }
                        } else {
                            String mimeType = URLConnection.guessContentTypeFromStream(is);
                            IOUtils.copy(is, outputStream);
                            return mimeType;
                        }
                    }
                }
            }
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
        File file = new File(pathConstructorService.getStoragePath());
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
