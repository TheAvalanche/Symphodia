package org.symphodia.band.rest;

import org.symphodia.band.service.NewsService;
import org.symphodia.common.band.domain.News;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@LocalBean
@Path("/news")
public class NewsResource {

    @Inject
    private NewsService service;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> getAllNews() {
        return service.getAllNews();
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveNews(News news) {
        service.saveNews(news);
    }

    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeNews(News news) {
        service.removeNews(news);
    }

}
