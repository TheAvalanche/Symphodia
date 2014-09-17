package org.symphodia.server.ejb.rest.band;


import org.symphodia.server.domain.band.Band;
import org.symphodia.server.ejb.service.band.BandService;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@LocalBean
@Path("/band")
public class BandResource {

    @Inject
    private BandService service;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Band> getAllBands() {
        return service.getAllBands();
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveBand(@NotNull Band band) {
        service.saveBand(band);
    }

    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeBand(@NotNull Band band) {
        service.removeBand(band);
    }
}
