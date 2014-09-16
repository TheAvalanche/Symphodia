package org.symphodia.server.ejb.rest;

import org.apache.log4j.Logger;
import org.symphodia.server.domain.Property;
import org.symphodia.server.ejb.service.PropertyService;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;


@Stateless
@LocalBean
@Path("/property")
public class PropertyResource {

    @Inject
    private Logger logger;

    @Inject
    private PropertyService propertyService;

    @GET
    @Path("/all")
    public List<Property> getAll() {
        return propertyService.getAllProperties();
    }

    @POST
    @Path("/save")
    public void save(Property property) {
        propertyService.saveProperty(property);
    }
}
