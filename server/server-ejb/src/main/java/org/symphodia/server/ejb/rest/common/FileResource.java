package org.symphodia.server.ejb.rest.common;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.symphodia.server.ejb.service.common.FileService;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
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
    @Path("/{bandId}/saveImage")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void saveImageToBand(@NotNull @PathParam("bandId") Long bandId, MultipartFormDataInput input) throws IOException {

        for (Map.Entry<String, List<InputPart>> entry : input.getFormDataMap().entrySet()) {
            for (InputPart inputPart : entry.getValue()) {
                fileService.saveAndMinimizeImage(inputPart.getBody(InputStream.class, null), entry.getKey(), bandId + "/");
            }
        }
    }

    @POST
    @Path("/{bandId}/removeImage")
    public void removeImageFromBand(@NotNull @PathParam("bandId") Long bandId, String filename) throws IOException {
        fileService.removeImage(filename, bandId + "/");
    }

    @POST
    @Path("/{bandId}/saveSong")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void saveSongToBand(@NotNull @PathParam("bandId") Long bandId, MultipartFormDataInput input) throws IOException {

        for (Map.Entry<String, List<InputPart>> entry : input.getFormDataMap().entrySet()) {
            for (InputPart inputPart : entry.getValue()) {
                fileService.saveAndMinimizeImage(inputPart.getBody(InputStream.class, null), entry.getKey(), bandId + "/");
            }
        }
    }

    @POST
    @Path("/{bandId}/removeSong")
    public void removeSongFromBand(@NotNull @PathParam("bandId") Long bandId, String filename) throws IOException {
        fileService.removeSong(filename, bandId + "/");
    }
}
