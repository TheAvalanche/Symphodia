package org.symphodia.server.ejb.service.band;

import org.symphodia.server.domain.band.Band;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class BandService {

    @PersistenceContext(unitName = "SymphodiaUnit")
    private EntityManager entityManager;

    public List<Band> getAllBands() {
        TypedQuery<Band> query = entityManager.createNamedQuery("Band.all", Band.class);
        return query.getResultList();
    }

    public void saveBand(Band band) {
        entityManager.merge(band);
    }

    public void removeBand(Band band) {
        band = entityManager.find(Band.class, band.getId());
        entityManager.remove(band);
    }

    public Band getBandById(Long bandId) {
        TypedQuery<Band> query = entityManager.createNamedQuery("Band.byId", Band.class);
        query.setParameter("id", bandId);
        return query.getResultList().get(0);
    }
}
