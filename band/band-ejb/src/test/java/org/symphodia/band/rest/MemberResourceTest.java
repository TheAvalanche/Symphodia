package org.symphodia.band.rest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.symphodia.band.service.MemberService;
import org.symphodia.common.band.domain.Instrument;
import org.symphodia.common.band.domain.Member;
import org.symphodia.common.domain.AbstractDomainObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.List;

public class MemberResourceTest extends Arquillian {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(AbstractDomainObject.class,
                            Member.class,
                            Instrument.class,
                            MemberResource.class,
                            MemberService.class)
                .addAsResource("META-INF/persistence.xml");
    }

    @Inject
    private MemberResource memberResource;

    @Test
    public void testSaveMember() throws Exception {

        memberResource.saveMember(createTestMember());

        Member member = getOneFromDB();
        Assert.assertEquals(member.getName(), "Test name");
        Assert.assertEquals(member.getSurname(), "Test surname");
        Assert.assertEquals(member.getDescription(), "Test description");
        Assert.assertEquals(member.getInstrument(), Instrument.GUITAR);
        Assert.assertEquals(member.getImage(), "image1");

    }

    @Test(dependsOnMethods = {"testSaveMember"})
    public void testUpdateMember() throws Exception {

        Member member = getOneFromDB();
        member.setName("Test name updated");
        member.setSurname("Test surname updated");
        member.setDescription("Test description updated");
        member.setInstrument(Instrument.BASS);
        member.setImage("image2");

        memberResource.saveMember(member);

        member = getOneFromDB();
        Assert.assertEquals(member.getName(), "Test name updated");
        Assert.assertEquals(member.getSurname(), "Test surname updated");
        Assert.assertEquals(member.getDescription(), "Test description updated");
        Assert.assertEquals(member.getInstrument(), Instrument.BASS);
        Assert.assertEquals(member.getImage(), "image2");

    }

    @Test(dependsOnMethods = {"testUpdateMember"})
    public void testCountMember() throws Exception {
        Long count = memberResource.getMemberCount();
        Assert.assertEquals(count, new Long(1));
    }

    @Test(dependsOnMethods = {"testCountMember"})
    public void testGetMemberPart() {
        memberResource.saveMember(createTestMember());
        memberResource.saveMember(createTestMember());

        List<Member> memberList = memberResource.getMemberPart(1, 2);
        Assert.assertEquals(memberList.size(), 2);
    }

    @Test(dependsOnMethods = {"testGetMemberPart"})
    public void testRemoveMember() {
        List<Member> memberList = memberResource.getAllMember();

        memberList.stream().forEach(memberResource::removeMember);

        Assert.assertEquals(memberResource.getAllMember().size(), 0);
    }

    @Test(expectedExceptions = Exception.class)
    public void testValidation() {
        Member member = new Member();

        memberResource.saveMember(member);
    }

    private Member createTestMember() {
        Member member = new Member();
        member.setName("Test name");
        member.setSurname("Test surname");
        member.setDescription("Test description");
        member.setInstrument(Instrument.GUITAR);
        member.setImage("image1");
        return member;
    }

    private Member getOneFromDB() {
        List<Member> memberList = memberResource.getAllMember();
        Assert.assertEquals(memberList.size(), 1);
        return memberList.get(0);
    }

}