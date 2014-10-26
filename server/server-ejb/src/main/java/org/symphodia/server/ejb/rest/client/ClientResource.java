package org.symphodia.server.ejb.rest.client;


import org.symphodia.server.domain.client.Client;
import org.symphodia.server.ejb.service.client.ClientService;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@LocalBean
@Path("/client")
public class ClientResource {

    @Resource
    private SessionContext context;

    @Inject
    private ClientService clientService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GET
    @Path("/client")
    @Produces(MediaType.APPLICATION_JSON)
    public Client getClient() {
        String name = !context.getCallerPrincipal().getName().equals("anonymous") ? context.getCallerPrincipal().getName() : "admin@test.com";
        return clientService.getClient(name);
    }

    @GET
    @Path("/client/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Client getClient(@NotNull @PathParam("username") String username) {
        return clientService.getClient(username);
    }

    @POST
    @Path("/link")
    @Consumes(MediaType.APPLICATION_JSON)
    public void linkClientAndBand(Long clientId, Long bandId) {
        clientService.linkClientAndBand(clientId, bandId);
    }
    
    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveClient(@NotNull Client client) {
        clientService.saveClient(client);
    }

    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeClient(@NotNull Client client) {
        clientService.removeClient(client);
    }
}
