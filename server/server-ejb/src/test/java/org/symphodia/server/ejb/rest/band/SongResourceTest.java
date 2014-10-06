package org.symphodia.server.ejb.rest.band;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.symphodia.server.domain.band.Album;
import org.symphodia.server.domain.band.AlbumType;
import org.symphodia.server.domain.band.Band;
import org.symphodia.server.domain.band.Song;
import org.symphodia.server.ejb.service.band.AlbumService;
import org.symphodia.server.ejb.service.band.BandService;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SongResourceTest extends Arquillian {

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
    private SongResource songResource;

    @Inject
    private BandService bandService;

    @Inject
    private AlbumService albumService;

    private Band band;

    private Album album;

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

        albumService.saveAlbumToBand(band.getId(), createTestAlbum());

        album = albumService.getAllAlbumsByBand(band.getId()).get(0);
    }

    public void testSaveAlbum() throws Exception {
        songResource.saveSongToAlbum(album.getId(), createTestSong());

        Song song = getOneFromDB(album);
        Assert.assertEquals(song.getOrderNumber(), new Long(1L));
        Assert.assertEquals(song.getTitle(), "Test title");
        Assert.assertEquals(song.getMusicAuthor(), "Test music author");
        Assert.assertEquals(song.getWordsAuthor(), "Test words author");
        Assert.assertEquals(song.getText(), "Test text");
        Assert.assertEquals(song.getMusicList().size(), 3);

    }

    public void testUpdateAlbum() throws Exception {

        Song song = getOneFromDB(album);
        song.setOrderNumber(2L);
        song.setTitle("Test title updated");
        song.setMusicAuthor("Test music author updated");
        song.setWordsAuthor("Test words author updated");
        song.setText("Test text updated");
        song.getMusicList().add("music4");
        song.getMusicList().remove("music3");

        songResource.saveSongToAlbum(album.getId(), song);

        song = getOneFromDB(album);
        Assert.assertEquals(song.getOrderNumber(), new Long(2L));
        Assert.assertEquals(song.getTitle(), "Test title updated");
        Assert.assertEquals(song.getMusicAuthor(), "Test music author updated");
        Assert.assertEquals(song.getWordsAuthor(), "Test words author updated");
        Assert.assertEquals(song.getText(), "Test text updated");
        Assert.assertTrue(song.getMusicList().contains("music1"));
        Assert.assertTrue(song.getMusicList().contains("music2"));
        Assert.assertTrue(song.getMusicList().contains("music4"));

    }

    public void testRemoveAlbum() {
        List<Song> songList = songResource.getAllSongsByAlbum(album.getId());

        songList.stream().forEach(s -> songResource.removeSongFromAlbum(album.getId(), s));

        Assert.assertEquals(songResource.getAllSongsByAlbum(album.getId()).size(), 0);
    }

    public void tearDown() {
        albumService.removeAlbumFromBand(0L, album);
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

    private Song createTestSong() {
        Song song = new Song();
        song.setOrderNumber(1L);
        song.setTitle("Test title");
        song.setMusicAuthor("Test music author");
        song.setWordsAuthor("Test words author");
        song.setText("Test text");
        song.setMusicList(Arrays.asList("music1", "music2", "music3"));
        return song;
    }

    private Band createTestBand() {
        Band band = new Band();
        band.setName("Test name");
        band.setDescription("Test description");
        return band;
    }

    private Song getOneFromDB(Album album) {
        List<Song> songList = songResource.getAllSongsByAlbum(album.getId());
        Assert.assertEquals(songList.size(), 1);
        return songList.get(0);
    }
}
