package org.symphodia.band.service;

import org.symphodia.common.band.domain.News;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class NewsService {

    @PersistenceContext(unitName = "SymphodiaUnit")
    private EntityManager entityManager;

    public List<News> getAllNews() {
        TypedQuery<News> query = entityManager.createNamedQuery("News.all", News.class);
        return query.getResultList();
    }

    public List<News> getNewsPart(int offset, int max) {
        TypedQuery<News> query = entityManager.createNamedQuery("News.all", News.class);
        query.setFirstResult(offset);
        query.setMaxResults(max);
        return query.getResultList();
    }

    public Long getNewsCount() {
        TypedQuery<Long> query = entityManager.createNamedQuery("News.count", Long.class);
        return query.getSingleResult();
    }

    public void saveNews(News news) {
        entityManager.merge(news);
    }

    public void removeNews(News news) {
        news = entityManager.find(News.class, news.getId());
        entityManager.remove(news);
    }
}
