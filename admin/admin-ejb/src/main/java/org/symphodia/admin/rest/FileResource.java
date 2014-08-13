package org.symphodia.admin.rest;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.List;
import java.util.Map;


@Stateless
@LocalBean
@Path("/file")
public class FileResource {

    @POST
    @Path("save")
    public void save(MultipartFormDataInput input) {


        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("file");

        for (InputPart inputPart : inputParts) {
            try {
                MultivaluedMap<String, String> header = inputPart.getHeaders();
                String fileName = getFileName(header);

                //constructs upload file path
                fileName = "C:\\Development\\wildfly-8.1.0.Final\\standalone\\deployments\\uploaded.war\\" + fileName;

                writeFile(inputPart.getBody(InputStream.class, null),fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getFileName(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String entry : contentDisposition) {
            if ((entry.trim().startsWith("filename"))) {
                return entry.split("=")[1].trim().replaceAll("\"", "");
            }
        }
        return "unknown";
    }

    private void writeFile(InputStream content, String filename) throws IOException {
        Files.copy(content, Paths.get(filename));
    }

}
