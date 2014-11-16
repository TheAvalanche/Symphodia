package org.symphodia.server.ejb.rest.common;

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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;


@Stateless
@LocalBean
@Path("/file")
public class FileResource {

    @Inject
    private FileService fileService;

    @POST
    @Path("/{bandId}/saveImage")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveImageToBand(@NotNull @PathParam("bandId") Long bandId, MultipartFormDataInput input) throws IOException {

        InputStream inputStream = input.getFormDataMap().get("file").get(0).getBody(InputStream.class, null);

        return Response
                .status(Response.Status.OK)
                .entity(new ImageName(fileService.saveAndMinimizeImage(inputStream, bandId)))
                .build();
    }

    @POST
    @Path("/{bandId}/removeImage")
    public void removeImageFromBand(@NotNull @PathParam("bandId") Long bandId, String filename) throws IOException {
        fileService.removeImage(filename, bandId);
    }

    @POST
    @Path("/{bandId}/saveMusic")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveMusicToBand(@NotNull @PathParam("bandId") Long bandId, MultipartFormDataInput input) throws IOException {

        InputStream inputStream = input.getFormDataMap().get("file").get(0).getBody(InputStream.class, null);

        return Response
                .status(Response.Status.OK)
                .entity(new MusicName(fileService.saveMusic(inputStream, bandId)))
                .build();
    }

    @POST
    @Path("/{bandId}/removeMusic")
    public void removeMusicFromBand(@NotNull @PathParam("bandId") Long bandId, String filename) throws IOException {
        fileService.removeMusic(filename, bandId);
    }

    private class ImageName {

        String name;

        private ImageName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private class MusicName {

        String name;

        private MusicName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
