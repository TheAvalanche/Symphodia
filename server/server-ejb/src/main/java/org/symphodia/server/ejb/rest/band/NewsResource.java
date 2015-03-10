package org.symphodia.server.ejb.rest.band;


import org.symphodia.server.domain.band.News;
import org.symphodia.server.ejb.service.band.NewsService;

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
@Path("/news")
public class NewsResource {

    @Inject
    private NewsService service;

    @GET
    @Path("/{bandId}/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> getAllNewsByBand(@NotNull @PathParam("bandId") Long bandId) {
        return service.getAllNewsByBand(bandId);
    }

    @GET
    @Path("/{bandId}/{hot}/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> getAllNewsByBandAndHot(@NotNull @PathParam("bandId") Long bandId, @NotNull @PathParam("hot") Boolean hot) {
        return service.getNewsByBandAndHot(bandId, hot);
    }


    @GET
    @Path("/{bandId}/part/{offset}/{max}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> getNewsPartByBand(@NotNull @PathParam("bandId") Long bandId,
                                        @NotNull @PathParam("offset") int offset,
                                        @NotNull @PathParam("max") int max) {
        return service.getNewsPartByBand(bandId, offset, max);
    }

    @GET
    @Path("/{bandId}/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Long getNewsCountByBand(@NotNull @PathParam("bandId") Long bandId) {
        return service.getNewsCountByBand(bandId);
    }

    @POST
    @Path("/{bandId}/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveNewsToBand(@NotNull @PathParam("bandId") Long bandId, @NotNull News news) {
        service.saveNewsToBand(bandId, news);
    }

    @POST
    @Path("/{bandId}/remove")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeNewsFromBand(@NotNull @PathParam("bandId") Long bandId, @NotNull News news) {
        service.removeNewsFromBand(bandId, news);
    }
}
