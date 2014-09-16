package org.symphodia.server.ejb.service;


import org.symphodia.server.domain.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class ClientService {

    @PersistenceContext(unitName = "SymphodiaUnit")
    protected EntityManager entityManager;

    public Client getClient(String username) {
        TypedQuery<Client> query = entityManager.createNamedQuery("Client.byUsername", Client.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
