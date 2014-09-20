package org.symphodia.server.ejb.rest.band;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.symphodia.server.commons.date.DateUtil;
import org.symphodia.server.domain.band.Band;
import org.symphodia.server.domain.band.Instrument;
import org.symphodia.server.domain.band.Member;
import org.symphodia.server.ejb.service.band.BandService;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class MemberResourceTest extends Arquillian {

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
    private MemberResource memberResource;

    @Inject
    private BandService bandService;

    private Band band;

    @Test
    public void memberWorkflowIntegrationTest() throws Exception {
        setUp();
        testSaveMember();
        testUpdateMember();
        testCountMember();
        testGetMemberPart();
        testRemoveMember();
        tearDown();
    }

    public void setUp() {
        bandService.saveBand(createTestBand());

        band = bandService.getAllBands().get(0);
    }

    public void testSaveMember() throws Exception {

        memberResource.saveMemberToBand(band.getId(), createTestMember());

        Member member = getOneFromDB(band);
        Assert.assertEquals(member.getName(), "Test name");
        Assert.assertEquals(member.getSurname(), "Test surname");
        Assert.assertEquals(member.getDescription(), "Test description");
        Assert.assertEquals(member.getDateOfBirth(), DateUtil.toDate(2014, 1, 1));
        Assert.assertEquals(member.getInstrument(), Instrument.GUITAR);
        Assert.assertEquals(member.getImageList().size(), 3);

    }

    public void testUpdateMember() throws Exception {

        Member member = getOneFromDB(band);
        member.setName("Test name updated");
        member.setSurname("Test surname updated");
        member.setDescription("Test description updated");
        member.setDateOfBirth(DateUtil.toDate(2014, 1, 2));
        member.setInstrument(Instrument.BASS);
        member.getImageList().add("image4");
        member.getImageList().remove("image3");

        memberResource.saveMemberToBand(band.getId(), member);

        member = getOneFromDB(band);
        Assert.assertEquals(member.getName(), "Test name updated");
        Assert.assertEquals(member.getSurname(), "Test surname updated");
        Assert.assertEquals(member.getDescription(), "Test description updated");
        Assert.assertEquals(member.getDateOfBirth(), DateUtil.toDate(2014, 1, 2));
        Assert.assertEquals(member.getInstrument(), Instrument.BASS);
        Assert.assertTrue(member.getImageList().contains("image1"));
        Assert.assertTrue(member.getImageList().contains("image2"));
        Assert.assertTrue(member.getImageList().contains("image4"));

    }

    public void testCountMember() throws Exception {
        Long count = memberResource.getMembersCountByBand(band.getId());
        Assert.assertEquals(count, new Long(1));
    }

    public void testGetMemberPart() {
        memberResource.saveMemberToBand(band.getId(), createTestMember());
        memberResource.saveMemberToBand(band.getId(), createTestMember());

        List<Member> memberList = memberResource.getMembersPartByBand(band.getId(), 1, 2);
        Assert.assertEquals(memberList.size(), 2);
    }

    public void testRemoveMember() {
        List<Member> memberList = memberResource.getAllMembersByBand(band.getId());

        memberList.stream().forEach(n -> memberResource.removeMemberFromBand(band.getId(), n));

        Assert.assertEquals(memberResource.getAllMembersByBand(band.getId()).size(), 0);
    }

    public void tearDown() {
        bandService.removeBand(band);
    }

    private Member createTestMember() {
        Member member = new Member();
        member.setName("Test name");
        member.setSurname("Test surname");
        member.setDescription("Test description");
        member.setDateOfBirth(DateUtil.toDate(2014, 1, 1));
        member.setInstrument(Instrument.GUITAR);
        member.setImageList(Arrays.asList("image1", "image2", "image3"));
        return member;
    }

    private Band createTestBand() {
        Band band = new Band();
        band.setName("Test name");
        band.setDescription("Test description");
        return band;
    }

    private Member getOneFromDB(Band band) {
        List<Member> memberList = memberResource.getAllMembersByBand(band.getId());
        Assert.assertEquals(memberList.size(), 1);
        return memberList.get(0);
    }

}