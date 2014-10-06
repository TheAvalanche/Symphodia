package org.symphodia.server.ejb.service.common;


import org.symphodia.server.commons.image.ImageProcessor;
import org.symphodia.server.domain.common.PropertyKey;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Stateless
public class FileService {

    @Inject
    private PropertyService propertyService;

    public void saveAndMinimizeImage(InputStream content, String fileName, String bandPath) throws IOException {
        String uploadsPath = propertyService.get(PropertyKey.UPLOADS_PATH);
        String pathToFile =  uploadsPath + bandPath + fileName + ".png";

        ImageProcessor imageProcessor = new ImageProcessor(content);
        writeFile(imageProcessor.toInputStream(), pathToFile);

        imageProcessor.resizeProportionally(180);
        imageProcessor.cropToSquare(180);
        writeFile(imageProcessor.toInputStream(), uploadsPath + bandPath + fileName + "_s.png");
    }

    public void saveMusic(InputStream content, String fileName, String bandPath) throws IOException {
        String uploadsPath = propertyService.get(PropertyKey.UPLOADS_PATH);
        String pathToFile =  uploadsPath + bandPath + fileName + ".mp3";

        writeFile(content, pathToFile);
    }

    public void removeImage(String fileName, String bandPath) throws IOException {
        String uploadsPath = propertyService.get(PropertyKey.UPLOADS_PATH);
        deleteFile(uploadsPath + bandPath + fileName + ".png");
        deleteFile(uploadsPath + bandPath + fileName + "_s.png");
    }

    public void removeMusic(String fileName, String bandPath) throws IOException {
        String uploadsPath = propertyService.get(PropertyKey.UPLOADS_PATH);
        deleteFile(uploadsPath + bandPath + fileName + ".mp3");
    }

    public void writeFile(InputStream content, String path) throws IOException {
        Path pathToFile = Paths.get(path);
        Files.createDirectories(pathToFile.getParent());
        Files.copy(content, Paths.get(path));
    }

    public void deleteFile(String path) throws IOException {
        Files.delete(Paths.get(path));
    }
}
