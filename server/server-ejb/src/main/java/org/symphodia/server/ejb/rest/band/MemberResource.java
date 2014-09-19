package org.symphodia.server.ejb.rest.band;


import org.symphodia.server.domain.band.Instrument;
import org.symphodia.server.domain.band.Member;
import org.symphodia.server.ejb.service.band.MemberService;

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
@Path("/member")
public class MemberResource {

    @Inject
    private MemberService service;

    @GET
    @Path("/{bandId}/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Member> getAllMembersByBand(@NotNull @PathParam("bandId") Long bandId) {
        return service.getAllMembersByBand(bandId);
    }

    @GET
    @Path("/{bandId}/part/{offset}/{max}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Member> getMembersPartByBand(@NotNull @PathParam("bandId") Long bandId,
                                             @NotNull @PathParam("offset") int offset,
                                             @NotNull @PathParam("max") int max) {
        return service.getMembersPartByBand(bandId, offset, max);
    }

    @GET
    @Path("/{bandId}/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Long getMembersCountByBand(@NotNull @PathParam("bandId") Long bandId) {
        return service.getMembersCountByBand(bandId);
    }

    @POST
    @Path("/{bandId}/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveMemberToBand(@NotNull @PathParam("bandId") Long bandId, @NotNull Member member) {
        service.saveMemberByBand(bandId, member);
    }

    @POST
    @Path("/{bandId}/remove")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeMemberFromBand(@NotNull @PathParam("bandId") Long bandId, @NotNull Member member) {
        service.removeMemberFromBand(bandId, member);
    }

    @GET
    @Path("/instruments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Instrument> getInstruments() {
        return Arrays.asList(Instrument.values());
    }
}
