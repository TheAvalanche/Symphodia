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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Stateless
@LocalBean
@Path("/client")
public class ClientResource {

    @Resource
    private SessionContext context;

    @Inject
    private ClientService clientService;

    @GET
    @Path("/client")
    public Client getClient() {
        return clientService.getClient(context.getCallerPrincipal().getName());
    }

    @GET
    @Path("/client/{username}")
    public Client getClient(@NotNull @PathParam("username") String username) {
        return clientService.getClient(username);
    }
    
    @POST
    @Path("/save")
    public void saveClient(@NotNull Client client) {
        clientService.saveClient(client);
    }

    @POST
    @Path("/remove")
    public void removeClient(@NotNull Client client) {
        clientService.removeClient(client);
    }
}
