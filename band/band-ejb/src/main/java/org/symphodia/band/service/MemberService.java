package org.symphodia.band.service;

import org.symphodia.common.band.domain.Member;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class MemberService {

    @PersistenceContext(unitName = "SymphodiaUnit")
    private EntityManager entityManager;

    public List<Member> getAllMember() {
        TypedQuery<Member> query = entityManager.createNamedQuery("Member.all", Member.class);
        return query.getResultList();
    }

    public List<Member> getMemberPart(int offset, int max) {
        TypedQuery<Member> query = entityManager.createNamedQuery("Member.all", Member.class);
        query.setFirstResult(offset);
        query.setMaxResults(max);
        return query.getResultList();
    }

    public Long getMemberCount() {
        TypedQuery<Long> query = entityManager.createNamedQuery("Member.count", Long.class);
        return query.getSingleResult();
    }

    public void saveMember(Member member) {
        entityManager.merge(member);
    }

    public void removeMember(Member member) {
        member = entityManager.find(Member.class, member.getId());
        entityManager.remove(member);
    }
}
