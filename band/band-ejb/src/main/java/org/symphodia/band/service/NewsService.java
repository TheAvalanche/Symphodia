package org.symphodia.band.service;

import org.symphodia.common.band.domain.News;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class NewsService {

    @PersistenceContext(unitName = "SymphodiaBandUnit")
    private EntityManager entityManager;

    public List<News> getAllNews() {
        return entityManager.createNamedQuery("News.getAll").getResultList();
    }

    public void saveNews(News news) {
        entityManager.merge(news);
    }
}
