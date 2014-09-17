package org.symphodia.server.ejb.rest.band;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.symphodia.server.domain.band.Band;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.List;

public class BandResourceTest extends Arquillian {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage("org.symphodia.server.domain.band")
                .addPackage("org.symphodia.server.domain")
                .addPackage("org.symphodia.server.commons.date")
                .addPackage("org.symphodia.server.ejb.rest.band")
                .addPackage("org.symphodia.server.ejb.service.band")
                .addAsResource("META-INF/persistence.xml");
    }

    @Inject
    private BandResource bandResource;

    @Test
    public void memberWorkflowIntegrationTest() throws Exception {
        testSaveBand();
        testUpdateBand();
        testRemoveBand();
    }

    public void testSaveBand() throws Exception {

        bandResource.saveBand(createTestBand());

        Band band = getOneFromDB();
        Assert.assertEquals(band.getName(), "Test name");
        Assert.assertEquals(band.getDescription(), "Test description");
    }

    public void testUpdateBand() throws Exception {

        Band band = getOneFromDB();
        band.setName("Test name updated");
        band.setDescription("Test description updated");

        bandResource.saveBand(band);

        band = getOneFromDB();
        Assert.assertEquals(band.getName(), "Test name updated");
        Assert.assertEquals(band.getDescription(), "Test description updated");

    }

    public void testRemoveBand() {
        List<Band> bandList = bandResource.getAllBands();

        bandList.stream().forEach(bandResource::removeBand);

        Assert.assertEquals(bandResource.getAllBands().size(), 0);
    }

    private Band createTestBand() {
        Band band = new Band();
        band.setName("Test name");
        band.setDescription("Test description");
        return band;
    }

    private Band getOneFromDB() {
        List<Band> bandList = bandResource.getAllBands();
        Assert.assertEquals(bandList.size(), 1);
        return bandList.get(0);
    }
}
