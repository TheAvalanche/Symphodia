package org.symphodia.admin.service;

import javax.ejb.Stateless;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Paths;

@Stateless
public class FileService {

    private static final String PATH = "C:\\Development\\wildfly-8.1.0.Final\\standalone\\deployments\\uploaded.war\\";

    public void saveAndMinimizeImage(InputStream content, String fileName) throws IOException {
        String pathToFile =  PATH + fileName + ".png";

        ImageProcessor imageProcessor = new ImageProcessor(content);
        writeFile(imageProcessor.toInputStream(), pathToFile);

        imageProcessor.resize(180, 180);
        writeFile(imageProcessor.toInputStream(), PATH + fileName + "_s.png");
    }

    public void removeImage(String fileName) throws IOException {
        deleteFile(PATH + fileName + ".png");
        deleteFile(PATH + fileName + "_s.png");
    }

    public void writeFile(InputStream content, String path) throws IOException {
        Files.copy(content, Paths.get(path));
    }

    public void deleteFile(String path) throws IOException {
        Files.delete(Paths.get(path));
    }
}
