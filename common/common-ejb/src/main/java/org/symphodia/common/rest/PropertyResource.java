package org.symphodia.common.rest;

import org.symphodia.common.domain.Property;
import org.symphodia.common.service.PropertyService;

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
    private PropertyService propertyService;

    @GET
    @Path("/all")
    public List<Property> getAll() {
        return propertyService.getAllProperties();
    }

    @POST
    @Path("/save")
    public void save(Property property) {

    }
}
