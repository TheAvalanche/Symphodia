package org.symphodia.server.ejb.rest.band;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.symphodia.server.domain.band.Band;
import org.symphodia.server.domain.band.News;
import org.symphodia.server.ejb.rest.band.NewsResource;
import org.symphodia.server.ejb.service.band.BandService;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class NewsResourceTest extends Arquillian {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage("org.symphodia.server.domain.band")
                .addPackage("org.symphodia.server.domain")
                .addPackage("org.symphodia.server.commons.date")
                .addPackage("org.symphodia.server.ejb.rest.band")
                .addPackage("org.symphodia.server.ejb.service.band")
                .addAsResource("META-INF/persistence.xml");
    }

    @Inject
    private NewsResource newsResource;

    @Inject
    private BandService bandService;

    @Test
    public void newsWorkflowIntegrationTest() throws Exception {
        Band band = createBand();
        testSaveNews(band);
        testUpdateNews(band);
        testCountNews(band);
        testGetNewsPart(band);
        testRemoveNews(band);
        tearDown(band);
    }

    public Band createBand() {
        Band band = createTestBand();
        bandService.saveBand(band);
        
        return bandService.getAllBands().get(0);
    }

    public void testSaveNews(Band band) throws Exception {
        newsResource.saveNewsToBand(band.getId(), createTestNews());

        News news = getOneFromDB(band);
        Assert.assertEquals(news.getTitle(), "Test title");
        Assert.assertEquals(news.getContent(), "Test content");
        Assert.assertEquals(news.getImageList().size(), 3);

    }

    public void testUpdateNews(Band band) throws Exception {

        News news = getOneFromDB(band);
        news.setTitle("Test title updated");
        news.setContent("Test content updated");
        news.getImageList().add("image4");
        news.getImageList().remove("image3");

        newsResource.saveNewsToBand(band.getId(), news);

        news = getOneFromDB(band);
        Assert.assertEquals(news.getTitle(), "Test title updated");
        Assert.assertEquals(news.getContent(), "Test content updated");
        Assert.assertEquals(news.getImageList().size(), 3);
        Assert.assertTrue(news.getImageList().contains("image1"));
        Assert.assertTrue(news.getImageList().contains("image2"));
        Assert.assertTrue(news.getImageList().contains("image4"));

    }

    public void testCountNews(Band band) throws Exception {
        Long count = newsResource.getNewsCountByBand(band.getId());
        Assert.assertEquals(count, new Long(1));
    }

    public void testGetNewsPart(Band band) {
        newsResource.saveNewsToBand(band.getId(), createTestNews());
        newsResource.saveNewsToBand(band.getId(), createTestNews());

        List<News> newsList = newsResource.getNewsPartByBand(band.getId(), 1, 2);
        Assert.assertEquals(newsList.size(), 2);
    }

    public void testRemoveNews(Band band) {
        List<News> newsList = newsResource.getAllNewsByBand(band.getId());

        newsList.stream().forEach(n -> newsResource.removeNewsFromBand(band.getId(), n));

        Assert.assertEquals(newsResource.getAllNewsByBand(band.getId()).size(), 0);
    }

    public void tearDown(Band band) {
        bandService.removeBand(band);
    }

    private News createTestNews() {
        News news = new News();
        news.setCreationDate(new Date());
        news.setTitle("Test title");
        news.setContent("Test content");
        news.setImageList(Arrays.asList("image1", "image2", "image3"));
        return news;
    }

    private Band createTestBand() {
        Band band = new Band();
        band.setName("Test name");
        band.setDescription("Test description");
        return band;
    }

    private News getOneFromDB(Band band) {
        List<News> newsList = newsResource.getAllNewsByBand(band.getId());
        Assert.assertEquals(newsList.size(), 1);
        return newsList.get(0);
    }

}