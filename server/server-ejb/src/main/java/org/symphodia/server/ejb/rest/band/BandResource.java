package org.symphodia.server.ejb.rest.band;


import org.symphodia.server.domain.band.Band;
import org.symphodia.server.ejb.service.band.BandService;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("/band")
public class BandResource {

    @Inject
    private BandService service;

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
