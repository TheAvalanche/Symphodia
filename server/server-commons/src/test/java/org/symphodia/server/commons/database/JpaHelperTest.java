package org.symphodia.server.commons.database;

import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class JpaHelperTest {

    @Mock
    private TypedQuery query;

    @BeforeMethod
    public void setup() {
        initMocks(this);
    }

    @Test
    public void getSingleResultOrNullTestShouldReturnNull() {
        when(query.getResultList()).thenReturn(Collections.emptyList());

        Object o = JpaHelper.getSingleResultOrNull(query);

        Assert.assertNull(o);
    }

    @Test
    public void getSingleResultOrNullTestShouldReturnObject() {
        when(query.getResultList()).thenReturn(Collections.singletonList(new Object()));

        Object o = JpaHelper.getSingleResultOrNull(query);

        Assert.assertNotNull(o);
    }

    @Test(expectedExceptions = {NonUniqueResultException.class})
    public void getSingleResultOrNullTestShouldThrowException() {
        when(query.getResultList()).thenReturn(Arrays.asList(new Object(), new Object()));

        JpaHelper.getSingleResultOrNull(query);
    }

}
