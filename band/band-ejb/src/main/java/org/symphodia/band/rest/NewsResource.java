package org.symphodia.band.rest;

import org.symphodia.band.service.NewsService;
import org.symphodia.common.band.domain.News;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@LocalBean
@Path("/news/")
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
    public void saveNews(News news) {
        service.saveNews(news);
    }

}
