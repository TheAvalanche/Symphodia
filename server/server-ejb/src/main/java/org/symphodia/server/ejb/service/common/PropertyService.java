package org.symphodia.server.ejb.service.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import org.symphodia.server.domain.common.Property;
import org.symphodia.server.domain.common.PropertyKey;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Startup
@SuppressWarnings("unchecked")
public class PropertyService {

    @PersistenceContext(unitName = "SymphodiaUnit")
    protected EntityManager entityManager;

    private LoadingCache<PropertyKey,Property> cache;

    @PostConstruct
    public void init() {
        cache = CacheBuilder
                .newBuilder()
                .build(new CacheLoader<PropertyKey, Property>() {
                    @Override
                    public Property load(PropertyKey propertyKey) throws Exception {
                        Property property = loadPropertyFromDatabase(propertyKey);

                        if(property == null) {
                            property = createAndPersistProperty(propertyKey);
                        }

                        return property;
                    }
                });

        for (PropertyKey propertyKey : PropertyKey.values()) {
            cache.getUnchecked(propertyKey);
        }
    }

    private Property createAndPersistProperty(PropertyKey propertyKey) {
        Property property = new Property(propertyKey, propertyKey.getDefaultValue());
        entityManager.persist(property);
        return property;
    }

    private Property loadPropertyFromDatabase(PropertyKey propertyKey) {
        Query query = entityManager.createNamedQuery("Property.getProperty");
        query.setParameter("propertyKey", propertyKey);

        List<Property> results = (List<Property>) query.getResultList();
        return Iterables.getFirst(results, null);
    }

    public List<Property> getAllProperties() {
        return new ArrayList<>(cache.asMap().values());
    }

    public void saveProperty(Property property) {
        entityManager.merge(property);
        cache.refresh(property.getPropertyKey());
    }

    public String get(PropertyKey propertyKey) {
        return getProperty(propertyKey).getValue();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Property getProperty(PropertyKey propertyKey) {
        return cache.getUnchecked(propertyKey);
    }

}
