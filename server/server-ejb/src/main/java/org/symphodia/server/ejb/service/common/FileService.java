package org.symphodia.server.ejb.service.common;


import org.apache.commons.lang3.RandomStringUtils;
import org.symphodia.server.commons.image.Extension;
import org.symphodia.server.commons.image.ImageProcessor;
import org.symphodia.server.domain.common.PropertyKey;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Stateless
public class FileService {

    @Inject
    private PropertyService propertyService;

    public String saveAndMinimizeImage(InputStream content, Long bandPath) throws IOException {
        String fileName = generateFileName();

        ImageProcessor imageProcessor = new ImageProcessor(content);
        BufferedImage originalImage = imageProcessor.getImage();
        writeFile(imageProcessor.toInputStream(), generatePath(fileName, bandPath, Extension.PNG));

        imageProcessor = new ImageProcessor(originalImage);
        imageProcessor.resizeProportionally(360);
        imageProcessor.cropToSquare(360);
        writeFile(imageProcessor.toInputStream(), generatePath(fileName, bandPath, Extension.PNG_MEDIUM));

        imageProcessor = new ImageProcessor(originalImage);
        imageProcessor.resizeProportionally(180);
        imageProcessor.cropToSquare(180);
        writeFile(imageProcessor.toInputStream(), generatePath(fileName, bandPath, Extension.PNG_SMALL));

        imageProcessor = new ImageProcessor(originalImage);
        imageProcessor.resizeProportionally(360);
        imageProcessor.cropTo(360, 200);
        writeFile(imageProcessor.toInputStream(), generatePath(fileName, bandPath, Extension.PNG_MEDIUM_WIDE));

        imageProcessor = new ImageProcessor(originalImage);
        imageProcessor.resizeProportionally(180);
        imageProcessor.cropTo(180, 100);
        writeFile(imageProcessor.toInputStream(), generatePath(fileName, bandPath, Extension.PNG_SMALL_WIDE));
        return fileName;
    }

    public String saveAndMinimizeLogo(InputStream content, Long bandPath) throws IOException {
        String fileName = generateFileName();

        ImageProcessor imageProcessor = new ImageProcessor(content);
        BufferedImage originalImage = imageProcessor.getImage();
        writeFile(imageProcessor.toInputStream(), generatePath(fileName, bandPath, Extension.PNG));

        imageProcessor = new ImageProcessor(originalImage);
        imageProcessor.resizeProportionally(360);
        writeFile(imageProcessor.toInputStream(), generatePath(fileName, bandPath, Extension.PNG_MEDIUM));

        imageProcessor = new ImageProcessor(originalImage);
        imageProcessor.resizeProportionally(180);
        writeFile(imageProcessor.toInputStream(), generatePath(fileName, bandPath, Extension.PNG_SMALL));

        return fileName;
    }

    public String saveMusic(InputStream content, Long bandPath) throws IOException {
        String fileName = generateFileName();

        writeFile(content, generatePath(fileName, bandPath, Extension.MP3));
        return fileName;
    }

    public void removeImage(String fileName, Long bandPath) throws IOException {
        deleteFile(generatePath(fileName, bandPath, Extension.PNG));
        deleteFile(generatePath(fileName, bandPath, Extension.PNG_SMALL));
        deleteFile(generatePath(fileName, bandPath, Extension.PNG_SMALL_WIDE));
        deleteFile(generatePath(fileName, bandPath, Extension.PNG_MEDIUM));
        deleteFile(generatePath(fileName, bandPath, Extension.PNG_MEDIUM_WIDE));
    }

    public void removeLogo(String fileName, Long bandPath) throws IOException {
        deleteFile(generatePath(fileName, bandPath, Extension.PNG));
        deleteFile(generatePath(fileName, bandPath, Extension.PNG_SMALL));
        deleteFile(generatePath(fileName, bandPath, Extension.PNG_MEDIUM));
    }

    public void removeMusic(String fileName, Long bandPath) throws IOException {
        deleteFile(generatePath(fileName, bandPath, Extension.MP3));
    }

    public String generateFileName() {
        return RandomStringUtils.randomAlphanumeric(32);
    }

    public String generatePath(String fileName, Long bandPath, Extension extension) {
        return propertyService.get(PropertyKey.UPLOADS_PATH) + "/" + bandPath + "/" + fileName + extension.getExtension();
    }

    public void writeFile(InputStream content, String path) throws IOException {
        Path pathToFile = Paths.get(path);
        Files.createDirectories(pathToFile.getParent());
        Files.copy(content, Paths.get(path));
    }

    public void deleteFile(String path) throws IOException {
        System.out.println(path);
        Files.delete(Paths.get(path));
    }
}
