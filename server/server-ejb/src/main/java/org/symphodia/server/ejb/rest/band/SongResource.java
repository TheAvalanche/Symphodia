package org.symphodia.server.ejb.rest.band;

import org.symphodia.server.domain.band.Song;
import org.symphodia.server.ejb.service.band.SongService;

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
import java.util.List;

@Stateless
@LocalBean
@Path("/song")
public class SongResource {

    @Inject
    private SongService service;

    @GET
    @Path("/{albumId}/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Song> getAllSongsByAlbum(@NotNull @PathParam("albumId") Long albumId) {
        return service.getAllSongsByAlbum(albumId);
    }

    @POST
    @Path("/{albumId}/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveSongToAlbum(@NotNull @PathParam("albumId") Long albumId, @NotNull Song song) {
        service.saveSongToAlbum(albumId, song);
    }

    @POST
    @Path("/{albumId}/remove")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeSongFromAlbum(@NotNull @PathParam("albumId") Long albumId, @NotNull Song song) {
        service.removeSongFromAlbum(albumId, song);
    }
}
