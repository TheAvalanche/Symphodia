package org.symphodia.server.ejb.service.band;

import org.symphodia.server.domain.band.Band;
import org.symphodia.server.domain.band.News;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class NewsService {

    @PersistenceContext(unitName = "SymphodiaUnit")
    private EntityManager entityManager;

    public List<News> getAllNewsByBand(Long bandId) {
        TypedQuery<News> query = entityManager.createNamedQuery("News.allByBand", News.class);
        query.setParameter("bandId", bandId);
        return query.getResultList();
    }

    public List<News> getNewsPartByBand(Long bandId, int offset, int max) {
        TypedQuery<News> query = entityManager.createNamedQuery("News.allByBand", News.class);
        query.setParameter("bandId", bandId);
        query.setFirstResult(offset);
        query.setMaxResults(max);
        return query.getResultList();
    }

    public List<News> getNewsByBandAndHot(Long bandId, Boolean hot) {
        TypedQuery<News> query = entityManager.createNamedQuery("News.allByBandAndHot", News.class);
        query.setParameter("bandId", bandId);
        query.setParameter("hot", hot);
        return query.getResultList();
    }

    public Long getNewsCountByBand(Long bandId) {
        TypedQuery<Long> query = entityManager.createNamedQuery("News.countByBand", Long.class);
        query.setParameter("bandId", bandId);

        return query.getSingleResult();
    }

    public void saveNewsToBand(Long bandId, News news) {
        Band band = entityManager.find(Band.class, bandId);
        news.setBand(band);
        entityManager.merge(news);
    }

    public void removeNewsFromBand(Long bandId, News news) {
        news = entityManager.find(News.class, news.getId());
        entityManager.remove(news);
    }
}
