package org.symphodia.server.ejb.rest.band;

import org.symphodia.server.domain.band.Album;
import org.symphodia.server.domain.band.AlbumType;
import org.symphodia.server.domain.band.Instrument;
import org.symphodia.server.ejb.service.band.AlbumService;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;


@Stateless
@LocalBean
@Path("/album")
public class AlbumResource {

    @Inject
    private AlbumService service;

    @GET
    @Path("/{bandId}/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Album> getAllAlbumsByBand(@NotNull @PathParam("bandId") Long bandId) {
        return service.getAllAlbumsByBand(bandId);
    }

    @POST
    @Path("/{bandId}/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveAlbumToBand(@NotNull @PathParam("bandId") Long bandId, @NotNull Album album) {
        service.saveAlbumToBand(bandId, album);
    }

    @POST
    @Path("/{bandId}/remove")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeAlbumFromBand(@NotNull @PathParam("bandId") Long bandId, @NotNull Album album) {
        service.removeAlbumFromBand(bandId, album);
    }

    @GET
    @Path("/albumTypes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AlbumType> getAlbumTypes() {
        return Arrays.asList(AlbumType.values());
    }
}
