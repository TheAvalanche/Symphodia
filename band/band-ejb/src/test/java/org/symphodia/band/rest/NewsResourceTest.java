package org.symphodia.band.rest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.symphodia.common.band.domain.News;
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
                .addPackage("org.symphodia.common.band.domain")
                .addPackage("org.symphodia.common.domain")
                .addPackage("org.symphodia.band.rest")
                .addPackage("org.symphodia.band.service")
                .addAsResource("META-INF/persistence.xml");
    }

    @Inject
    private NewsResource newsResource;

    @Test
    public void testSaveNews() throws Exception {

        newsResource.saveNews(createTestNews());

        News news = getOneFromDB();
        Assert.assertEquals(news.getTitle(), "Test title");
        Assert.assertEquals(news.getContent(), "Test content");
        Assert.assertEquals(news.getImageList().size(), 3);

    }

    @Test(dependsOnMethods = {"testSaveNews"})
    public void testUpdateNews() throws Exception {

        News news = getOneFromDB();
        news.setTitle("Test title updated");
        news.setContent("Test content updated");
        news.getImageList().add("image4");
        news.getImageList().remove("image3");

        newsResource.saveNews(news);

        news = getOneFromDB();
        Assert.assertEquals(news.getTitle(), "Test title updated");
        Assert.assertEquals(news.getContent(), "Test content updated");
        Assert.assertEquals(news.getImageList().size(), 3);
        Assert.assertTrue(news.getImageList().contains("image1"));
        Assert.assertTrue(news.getImageList().contains("image2"));
        Assert.assertTrue(news.getImageList().contains("image4"));

    }

    @Test(dependsOnMethods = {"testUpdateNews"})
    public void testCountNews() throws Exception {
        Long count = newsResource.getNewsCount();
        Assert.assertEquals(count, new Long(1));
    }

    @Test(dependsOnMethods = {"testCountNews"})
    public void testGetNewsPart() {
        newsResource.saveNews(createTestNews());
        newsResource.saveNews(createTestNews());

        List<News> newsList = newsResource.getNewsPart(1, 2);
        Assert.assertEquals(newsList.size(), 2);
    }

    @Test(dependsOnMethods = {"testGetNewsPart"})
    public void testRemoveNews() {
        List<News> newsList = newsResource.getAllNews();

        newsList.stream().forEach(newsResource::removeNews);

        Assert.assertEquals(newsResource.getAllNews().size(), 0);
    }

    @Test(expectedExceptions = Exception.class)
    public void testValidation() {
        News news = new News();

        newsResource.saveNews(news);
    }

    private News createTestNews() {
        News news = new News();
        news.setCreationDate(new Date());
        news.setTitle("Test title");
        news.setContent("Test content");
        news.setImageList(Arrays.asList("image1", "image2", "image3"));
        return news;
    }

    private News getOneFromDB() {
        List<News> newsList = newsResource.getAllNews();
        Assert.assertEquals(newsList.size(), 1);
        return newsList.get(0);
    }

}