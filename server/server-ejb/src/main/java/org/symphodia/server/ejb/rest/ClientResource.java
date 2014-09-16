package org.symphodia.server.ejb.rest;


import org.symphodia.server.domain.Client;
import org.symphodia.server.ejb.service.ClientService;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
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
}
