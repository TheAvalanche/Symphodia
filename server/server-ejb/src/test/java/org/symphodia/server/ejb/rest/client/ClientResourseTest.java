package org.symphodia.server.ejb.rest.client;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.symphodia.server.domain.band.Band;
import org.symphodia.server.domain.client.Client;
import org.symphodia.server.ejb.rest.band.BandResource;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.List;

public class ClientResourseTest extends Arquillian {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage("org.symphodia.server.domain.band")
                .addPackage("org.symphodia.server.domain.client")
                .addPackage("org.symphodia.server.domain")
                .addPackage("org.symphodia.server.commons.date")
                .addPackage("org.symphodia.server.commons.database")
                .addPackage("org.symphodia.server.ejb.rest.band")
                .addPackage("org.symphodia.server.ejb.rest.client")
                .addPackage("org.symphodia.server.ejb.service.band")
                .addPackage("org.symphodia.server.ejb.service.client")
                .addAsResource("META-INF/persistence.xml");
    }

    @Inject
    private ClientResource clientResource;

    @Inject
    private BandResource bandResource;

    @Test
    public void clientWorkflowIntegrationTest() throws Exception {
        testSaveClient();
        testUpdateClient();
        testRemoveClient();
    }

    @Test
    public void clientBandLinkIntegrationTest() throws Exception {
        testSaveClient();
        testSaveBand();
        testLinkClientAndBand();
        testRemoveClient();
        testRemoveBand();
    }

    public void testSaveClient() throws Exception {

        clientResource.saveClient(createTestClient());

        Client client = clientResource.getClient("test@test.com");
        Assert.assertEquals(client.getUsername(), "test@test.com");
        Assert.assertEquals(client.getPassword(), "test");
        Assert.assertEquals(client.getRole(), "test");
        Assert.assertEquals(client.getRoleGroup(), "test");
    }

    public void testUpdateClient() throws Exception {

        Client client = getOneClientFromDB();
        client.setUsername("testUpdated@test.com");
        client.setPassword("testUpdated");
        client.setRole("testUpdated");
        client.setRoleGroup("testUpdated");

        clientResource.saveClient(client);

        client = getOneClientFromDB();

        Assert.assertNotNull(client);
        Assert.assertEquals(client.getUsername(), "testUpdated@test.com");
        Assert.assertEquals(client.getPassword(), "testUpdated");
        Assert.assertEquals(client.getRole(), "testUpdated");
        Assert.assertEquals(client.getRoleGroup(), "testUpdated");
    }

    public void testRemoveClient() {
        List<Client> clientList = clientResource.getAllClients();

        clientList.stream().forEach(clientResource::removeClient);

        Assert.assertEquals(clientResource.getAllClients().size(), 0);
    }

    public void testLinkClientAndBand() {
        Client client = getOneClientFromDB();
        Band band = getOneBandFromDB();
        clientResource.linkClientAndBand(client.getId(), band.getId());

        client = getOneClientFromDB();
        Assert.assertEquals(client.getBands().size(), 1);
    }

    public void testSaveBand() throws Exception {

        bandResource.saveBand(createTestBand());

        Band band = getOneBandFromDB();
        Assert.assertEquals(band.getName(), "Test name");
        Assert.assertEquals(band.getDescription(), "Test description");
    }

    public void testRemoveBand() {
        List<Band> bandList = bandResource.getAllBands();

        bandList.stream().forEach(bandResource::removeBand);

        Assert.assertEquals(bandResource.getAllBands().size(), 0);
    }


    private Client createTestClient() {
        Client client = new Client();
        client.setUsername("test@test.com");
        client.setPassword("test");
        client.setRole("test");
        client.setRoleGroup("test");
        return client;
    }

    private Band createTestBand() {
        Band band = new Band();
        band.setName("Test name");
        band.setDescription("Test description");
        return band;
    }

    private Band getOneBandFromDB() {
        List<Band> bandList = bandResource.getAllBands();
        Assert.assertEquals(bandList.size(), 1);
        return bandList.get(0);
    }

    private Client getOneClientFromDB() {
        List<Client> clientList = clientResource.getAllClients();
        Assert.assertEquals(clientList.size(), 1);
        return clientList.get(0);
    }
}
