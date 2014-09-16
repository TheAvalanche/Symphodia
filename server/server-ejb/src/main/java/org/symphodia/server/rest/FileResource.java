package org.symphodia.server.rest;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.symphodia.server.service.FileService;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


@Stateless
@LocalBean
@Path("/file")
public class FileResource {

    @Inject
    private FileService fileService;

    @POST
    @Path("saveImage")
    public void saveImage(MultipartFormDataInput input) throws IOException {

        for (Map.Entry<String, List<InputPart>> entry : input.getFormDataMap().entrySet()) {
            for (InputPart inputPart : entry.getValue()) {
                fileService.saveAndMinimizeImage(inputPart.getBody(InputStream.class, null), entry.getKey());
            }
        }
    }

    @POST
    @Path("removeImage")
    public void removeImage(String filename) throws IOException {
        fileService.removeImage(filename);
    }
}
