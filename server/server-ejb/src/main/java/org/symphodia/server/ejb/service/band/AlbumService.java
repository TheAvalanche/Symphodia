package org.symphodia.server.ejb.service.band;

import org.symphodia.server.domain.band.Album;
import org.symphodia.server.domain.band.Band;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class AlbumService {

    @PersistenceContext(unitName = "SymphodiaUnit")
    private EntityManager entityManager;

    public List<Album> getAllAlbumsByBand(Long bandId) {
        TypedQuery<Album> query = entityManager.createNamedQuery("Album.allByBand", Album.class);
        query.setParameter("bandId", bandId);
        return query.getResultList();
    }

    public void saveAlbumToBand(Long bandId, Album album) {
        Band band = entityManager.find(Band.class, bandId);
        album.setBand(band);
        entityManager.merge(album);
    }

    public void removeAlbumFromBand(Long bandId, Album album) {
        album = entityManager.find(Album.class, album.getId());
        entityManager.remove(album);
    }
}
