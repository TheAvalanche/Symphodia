package org.symphodia.server.ejb.rest.client;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.symphodia.server.domain.client.Client;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;

public class ClientResourseTest extends Arquillian {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage("org.symphodia.server.domain.band")
                .addPackage("org.symphodia.server.domain.client")
                .addPackage("org.symphodia.server.domain")
                .addPackage("org.symphodia.server.commons.date")
                .addPackage("org.symphodia.server.commons.database")
                .addPackage("org.symphodia.server.ejb.rest.client")
                .addPackage("org.symphodia.server.ejb.service.client")
                .addAsResource("META-INF/persistence.xml");
    }

    @Inject
    private ClientResource clientResourse;

    @Test
    public void memberWorkflowIntegrationTest() throws Exception {
        testSaveClient();
        testUpdateClient();
        testRemoveClient();
    }

    public void testSaveClient() throws Exception {

        clientResourse.saveClient(createTestClient());

        Client client = clientResourse.getClient("test@test.com");
        Assert.assertEquals(client.getUsername(), "test@test.com");
        Assert.assertEquals(client.getPassword(), "test");
        Assert.assertEquals(client.getRole(), "test");
        Assert.assertEquals(client.getRoleGroup(), "test");
    }

    public void testUpdateClient() throws Exception {

        Client client = clientResourse.getClient("test@test.com");
        client.setUsername("testUpdated@test.com");
        client.setPassword("testUpdated");
        client.setRole("testUpdated");
        client.setRoleGroup("testUpdated");

        clientResourse.saveClient(client);

        client = clientResourse.getClient("testUpdated@test.com");

        Assert.assertNotNull(client);
        Assert.assertEquals(client.getUsername(), "testUpdated@test.com");
        Assert.assertEquals(client.getPassword(), "testUpdated");
        Assert.assertEquals(client.getRole(), "testUpdated");
        Assert.assertEquals(client.getRoleGroup(), "testUpdated");
    }

    public void testRemoveClient() {
        Client client = clientResourse.getClient("testUpdated@test.com");

        clientResourse.removeClient(client);

        Assert.assertNull(clientResourse.getClient("testUpdated@test.com"));
    }

    private Client createTestClient() {
        Client client = new Client();
        client.setUsername("test@test.com");
        client.setPassword("test");
        client.setRole("test");
        client.setRoleGroup("test");
        return client;
    }
}
