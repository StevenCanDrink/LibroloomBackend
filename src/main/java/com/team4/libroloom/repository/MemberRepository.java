package com.team4.libroloom.repository;

import com.team4.libroloom.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    // save
    public void save(Member member) {
        em.persist(member);
    }

    // findOne
    public Optional<Member> findOne(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    public List<Member> findByEmail(String email) {
        return em.createQuery("select m from member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
    }
    public Optional<Member> findByEmailOrUsername(String username) {
        return em.createQuery("select m from Member m where m.email = :username or m.username = :username", Member.class)
                .setParameter("username", username)
                .getResultStream().findAny();
    }
    public Optional<Member> findByEmailOrUsername(String username,String email) {
        return em.createQuery("select m from Member m where m.email = :email or m.username = :username", Member.class)
                .setParameter("username", username).setParameter("email",email)
                .getResultStream().findAny();
    }

    public List<Member> findByAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
