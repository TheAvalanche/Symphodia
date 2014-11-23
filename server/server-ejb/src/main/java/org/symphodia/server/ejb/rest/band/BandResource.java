package org.symphodia.server.ejb.rest.band;


import org.symphodia.server.domain.band.Band;
import org.symphodia.server.ejb.service.band.BandService;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
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

    @GET
    @Path("/id/{bandId}")
    public Band getBandById(@NotNull @PathParam("bandId") Long bandId) {
        return service.getBandById(bandId);
    }
}
