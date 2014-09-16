package org.symphodia.server.ejb.service;

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

    public List<Member> getMembersPartByBand(Long bandId, int offset, int max) {
        TypedQuery<Member> query = entityManager.createNamedQuery("Member.allByBand", Member.class);
        query.setParameter("bandId", bandId);
        query.setFirstResult(offset);
        query.setMaxResults(max);
        return query.getResultList();
    }

    public Long getMembersCountByBand(Long bandId) {
        TypedQuery<Long> query = entityManager.createNamedQuery("Member.countByBand", Long.class);
        query.setParameter("bandId", bandId);
        return query.getSingleResult();
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
