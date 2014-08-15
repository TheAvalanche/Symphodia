package org.symphodia.common.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.symphodia.common.domain.Property;
import org.symphodia.common.domain.PropertyKey;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class PropertyService {

    @PersistenceContext(unitName = "SymphodiaCommonUnit")
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
    }

    private Property createAndPersistProperty(PropertyKey propertyKey) {
        return null;
    }

    private Property loadPropertyFromDatabase(PropertyKey propertyName) {
        return null;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Property getProperty(PropertyKey propertyKey) {
        return cache.getUnchecked(propertyKey);
    }

}
