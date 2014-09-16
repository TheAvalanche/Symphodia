package org.symphodia.server.ejb.rest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.symphodia.server.commons.date.DateUtil;
import org.symphodia.server.domain.band.Band;
import org.symphodia.server.domain.band.Instrument;
import org.symphodia.server.domain.band.Member;
import org.symphodia.server.ejb.service.BandService;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.List;

public class MemberResourceTest extends Arquillian {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage("org.symphodia.common.domain.band")
                .addPackage("org.symphodia.common.domain")
                .addPackage("org.symphodia.common.date")
                .addPackage("org.symphodia.band.rest")
                .addPackage("org.symphodia.band.service")
                .addAsResource("META-INF/persistence.xml");
    }

    @Inject
    private MemberResource memberResource;

    @Inject
    private BandService bandService;

    @Test
    public void memberWorkflowIntegrationTest() throws Exception {
        Band band = createBand();
        testSaveMember(band);
        testUpdateMember(band);
        testCountMember(band);
        testGetMemberPart(band);
        testRemoveMember(band);
        tearDown(band);
    }

    public Band createBand() {
        Band band = createTestBand();
        bandService.saveBand(band);

        return bandService.getAllBands().get(0);
    }

    public void testSaveMember(Band band) throws Exception {

        memberResource.saveMemberToBand(band.getId(), createTestMember());

        Member member = getOneFromDB(band);
        Assert.assertEquals(member.getName(), "Test name");
        Assert.assertEquals(member.getSurname(), "Test surname");
        Assert.assertEquals(member.getDescription(), "Test description");
        Assert.assertEquals(member.getDateOfBirth(), DateUtil.toDate(2014, 1, 1));
        Assert.assertEquals(member.getInstrument(), Instrument.GUITAR);
        Assert.assertEquals(member.getImage(), "image1");

    }

    public void testUpdateMember(Band band) throws Exception {

        Member member = getOneFromDB(band);
        member.setName("Test name updated");
        member.setSurname("Test surname updated");
        member.setDescription("Test description updated");
        member.setDateOfBirth(DateUtil.toDate(2014, 1, 2));
        member.setInstrument(Instrument.BASS);
        member.setImage("image2");

        memberResource.saveMemberToBand(band.getId(), member);

        member = getOneFromDB(band);
        Assert.assertEquals(member.getName(), "Test name updated");
        Assert.assertEquals(member.getSurname(), "Test surname updated");
        Assert.assertEquals(member.getDescription(), "Test description updated");
        Assert.assertEquals(member.getDateOfBirth(), DateUtil.toDate(2014, 1, 2));
        Assert.assertEquals(member.getInstrument(), Instrument.BASS);
        Assert.assertEquals(member.getImage(), "image2");

    }

    public void testCountMember(Band band) throws Exception {
        Long count = memberResource.getMembersCountByBand(band.getId());
        Assert.assertEquals(count, new Long(1));
    }

    public void testGetMemberPart(Band band) {
        memberResource.saveMemberToBand(band.getId(), createTestMember());
        memberResource.saveMemberToBand(band.getId(), createTestMember());

        List<Member> memberList = memberResource.getMembersPartByBand(band.getId(), 1, 2);
        Assert.assertEquals(memberList.size(), 2);
    }

    public void testRemoveMember(Band band) {
        List<Member> memberList = memberResource.getAllMembersByBand(band.getId());

        memberList.stream().forEach(n -> memberResource.removeMemberFromBand(band.getId(), n));

        Assert.assertEquals(memberResource.getAllMembersByBand(band.getId()).size(), 0);
    }

    public void tearDown(Band band) {
        bandService.removeBand(band);
    }

    private Member createTestMember() {
        Member member = new Member();
        member.setName("Test name");
        member.setSurname("Test surname");
        member.setDescription("Test description");
        member.setDateOfBirth(DateUtil.toDate(2014, 1, 1));
        member.setInstrument(Instrument.GUITAR);
        member.setImage("image1");
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