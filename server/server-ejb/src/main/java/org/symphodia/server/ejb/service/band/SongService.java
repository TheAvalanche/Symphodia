package org.symphodia.server.ejb.service.band;

import org.symphodia.server.domain.band.Album;
import org.symphodia.server.domain.band.Band;
import org.symphodia.server.domain.band.Song;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class SongService {
    @PersistenceContext(unitName = "SymphodiaUnit")
    private EntityManager entityManager;

    public List<Song> getAllSongsByAlbum(Long albumId) {
        TypedQuery<Song> query = entityManager.createNamedQuery("Song.allByAlbum", Song.class);
        query.setParameter("albumId", albumId);
        return query.getResultList();
    }

    public void saveSongToAlbum(Long albumId, Song song) {
        Album album = entityManager.find(Album.class, albumId);
        song.setAlbum(album);
        entityManager.merge(song);
    }

    public void removeSongFromAlbum(Long albumId, Song song) {
        song = entityManager.find(Song.class, song.getId());
        entityManager.remove(song);
    }
}
