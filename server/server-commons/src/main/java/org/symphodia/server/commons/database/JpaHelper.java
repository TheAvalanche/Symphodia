package org.symphodia.server.commons.database;

import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaHelper {

    public static <T> T getSingleResultOrNull(TypedQuery<T> query) {
        List<T> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() == 1) {
            return results.get(0);
        } else {
            throw new NonUniqueResultException();
        }
    }

}
