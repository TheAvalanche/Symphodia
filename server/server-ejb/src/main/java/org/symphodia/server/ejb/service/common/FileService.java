package org.symphodia.server.ejb.service.common;


import org.symphodia.server.commons.image.ImageProcessor;
import org.symphodia.server.domain.common.PropertyKey;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Stateless
public class FileService {

    @Inject
    private PropertyService propertyService;

    public void saveAndMinimizeImage(InputStream content, String fileName) throws IOException {
        String uploadsPath = propertyService.get(PropertyKey.UPLOADS_PATH);
        String pathToFile =  uploadsPath + fileName + ".png";

        ImageProcessor imageProcessor = new ImageProcessor(content);
        writeFile(imageProcessor.toInputStream(), pathToFile);

        imageProcessor.resizeProportionally(180);
        imageProcessor.cropToSquare(180);
        writeFile(imageProcessor.toInputStream(), uploadsPath + fileName + "_s.png");
    }

    public void removeImage(String fileName) throws IOException {
        String uploadsPath = propertyService.get(PropertyKey.UPLOADS_PATH);
        deleteFile(uploadsPath + fileName + ".png");
        deleteFile(uploadsPath + fileName + "_s.png");
    }

    public void writeFile(InputStream content, String path) throws IOException {
        Files.copy(content, Paths.get(path));
    }

    public void deleteFile(String path) throws IOException {
        Files.delete(Paths.get(path));
    }
}
