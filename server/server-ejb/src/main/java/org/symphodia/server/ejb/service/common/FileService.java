package org.symphodia.server.ejb.service.common;


import org.apache.commons.lang3.RandomStringUtils;
import org.symphodia.server.commons.image.Extension;
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

    public String saveAndMinimizeImage(InputStream content, String bandPath) throws IOException {
        String fileName = generateFileName();

        ImageProcessor imageProcessor = new ImageProcessor(content);
        writeFile(imageProcessor.toInputStream(), generatePath(fileName, bandPath, Extension.PNG));

        imageProcessor.resizeProportionally(180);
        imageProcessor.cropToSquare(180);
        writeFile(imageProcessor.toInputStream(), generatePath(fileName, bandPath, Extension.PNG_SMALL));
        return fileName;
    }

    public String saveMusic(InputStream content, String bandPath) throws IOException {
        String fileName = generateFileName();

        writeFile(content, generatePath(fileName, bandPath, Extension.MP3));
        return fileName;
    }

    public void removeImage(String fileName, String bandPath) throws IOException {
        deleteFile(generatePath(fileName, bandPath, Extension.PNG));
        deleteFile(generatePath(fileName, bandPath, Extension.PNG_SMALL));
    }

    public void removeMusic(String fileName, String bandPath) throws IOException {
        deleteFile(generatePath(fileName, bandPath, Extension.MP3));
    }

    public String generateFileName() {
        return RandomStringUtils.randomAlphanumeric(32);
    }

    public String generatePath(String fileName, String bandPath, Extension extension) {
        return propertyService.get(PropertyKey.UPLOADS_PATH) + "/" + bandPath + "/" + fileName + extension.getExtension();
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
