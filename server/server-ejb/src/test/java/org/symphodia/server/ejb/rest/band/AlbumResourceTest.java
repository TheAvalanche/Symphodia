package org.symphodia.server.ejb.rest.band;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.symphodia.server.domain.band.Album;
import org.symphodia.server.domain.band.AlbumType;
import org.symphodia.server.domain.band.Band;
import org.symphodia.server.ejb.service.band.BandService;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AlbumResourceTest extends Arquillian {

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
    private AlbumResource albumResource;

    @Inject
    private BandService bandService;

    private Band band;

    @Test
    public void albumWorkflowIntegrationTest() throws Exception {
        setUp();
        testSaveAlbum();
        testUpdateAlbum();
        testRemoveAlbum();
        tearDown();
    }

    public void setUp() {
        bandService.saveBand(createTestBand());

        band = bandService.getAllBands().get(0);
    }

    public void testSaveAlbum() throws Exception {
        albumResource.saveAlbumToBand(band.getId(), createTestAlbum());

        Album album = getOneFromDB(band);
        Assert.assertEquals(album.getTitle(), "Test title");
        Assert.assertEquals(album.getDescription(), "Test description");
        Assert.assertEquals(album.getImageList().size(), 3);

    }

    public void testUpdateAlbum() throws Exception {

        Album album = getOneFromDB(band);
        album.setTitle("Test title updated");
        album.setDescription("Test description updated");
        album.getImageList().add("image4");
        album.getImageList().remove("image3");

        albumResource.saveAlbumToBand(band.getId(), album);

        album = getOneFromDB(band);
        Assert.assertEquals(album.getTitle(), "Test title updated");
        Assert.assertEquals(album.getDescription(), "Test description updated");
        Assert.assertEquals(album.getImageList().size(), 3);
        Assert.assertTrue(album.getImageList().contains("image1"));
        Assert.assertTrue(album.getImageList().contains("image2"));
        Assert.assertTrue(album.getImageList().contains("image4"));

    }

    public void testRemoveAlbum() {
        List<Album> albumList = albumResource.getAllAlbumsByBand(band.getId());

        albumList.stream().forEach(a -> albumResource.removeAlbumFromBand(band.getId(), a));

        Assert.assertEquals(albumResource.getAllAlbumsByBand(band.getId()).size(), 0);
    }

    public void tearDown() {
        bandService.removeBand(band);
    }

    private Album createTestAlbum() {
        Album album = new Album();
        album.setCreationDate(new Date());
        album.setTitle("Test title");
        album.setDescription("Test description");
        album.setAlbumType(AlbumType.FULL);
        album.setImageList(Arrays.asList("image1", "image2", "image3"));
        return album;
    }

    private Band createTestBand() {
        Band band = new Band();
        band.setName("Test name");
        band.setDescription("Test description");
        return band;
    }

    private Album getOneFromDB(Band band) {
        List<Album> albumList = albumResource.getAllAlbumsByBand(band.getId());
        Assert.assertEquals(albumList.size(), 1);
        return albumList.get(0);
    }
}
