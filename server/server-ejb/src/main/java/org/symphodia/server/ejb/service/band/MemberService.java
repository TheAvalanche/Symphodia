package org.symphodia.server.ejb.service.band;

import org.symphodia.server.domain.band.Band;
import org.symphodia.server.domain.band.Member;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class MemberService {

    @PersistenceContext(unitName = "SymphodiaUnit")
    private EntityManager entityManager;

    public List<Member> getAllMembersByBand(Long bandId) {
        TypedQuery<Member> query = entityManager.createNamedQuery("Member.allByBand", Member.class);
        query.setParameter("bandId", bandId);
        return query.getResultList();
    }


    public void saveMemberByBand(Long bandId, Member member) {
        Band band = entityManager.find(Band.class, bandId);
        member.setBand(band);
        entityManager.merge(member);
    }

    public void removeMemberFromBand(Long bandId, Member member) {
        member = entityManager.find(Member.class, member.getId());
        entityManager.remove(member);
    }
}
