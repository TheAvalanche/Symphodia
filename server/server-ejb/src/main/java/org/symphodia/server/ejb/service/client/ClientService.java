package org.symphodia.server.ejb.service.client;


import org.symphodia.server.commons.database.JpaHelper;
import org.symphodia.server.domain.client.Client;

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

        return JpaHelper.getSingleResultOrNull(query);
    }

    public void saveClient(Client client) {
        entityManager.merge(client);
    }

    public void removeClient(Client client) {
        client = entityManager.find(Client.class, client.getId());
        entityManager.remove(client);
    }
}
