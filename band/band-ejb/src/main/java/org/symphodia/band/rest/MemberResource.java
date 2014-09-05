package org.symphodia.band.rest;

import org.symphodia.band.service.MemberService;
import org.symphodia.common.band.domain.Instrument;
import org.symphodia.common.band.domain.Member;

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
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Member> getAllMembers() {
        return service.getAllMembers();
    }

    @GET
    @Path("/part/{offset}/{max}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Member> getMembersPart(@NotNull @PathParam("offset") int offset, @NotNull @PathParam("max") int max) {
        return service.getMembersPart(offset, max);
    }

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Long getMembersCount() {
        return service.getMembersCount();
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveMember(@NotNull Member member) {
        service.saveMember(member);
    }

    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeMember(@NotNull Member member) {
        service.removeMember(member);
    }

    @GET
    @Path("/instruments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Instrument> getInstruments() {
        return Arrays.asList(Instrument.values());
    }
}
