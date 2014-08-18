package org.symphodia.common.rest;

import org.apache.log4j.Logger;
import org.symphodia.common.domain.Property;
import org.symphodia.common.service.PropertyService;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.text.MessageFormat;
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
        logger.info("getAll() is called");
        return propertyService.getAllProperties();
    }

    @POST
    @Path("/save")
    public void save(Property property) {
        logger.info(MessageFormat.format("save() is called for property {0}", property.getPropertyKey()));
        propertyService.saveProperty(property);
    }
}
