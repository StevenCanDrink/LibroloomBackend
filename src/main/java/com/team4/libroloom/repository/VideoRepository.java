package com.team4.libroloom.repository;

import com.team4.libroloom.domain.Video;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class VideoRepository {

    private final EntityManager em;

    // save
    public void save(Video video) {
        if (video.getId() == null) {
            em.persist(video);
        } else {
            em.merge(video);
        }
    }

    // findOne
    public Optional<Video> findOne(Long id) {
        return Optional.ofNullable(em.find(Video.class, id));
    }

    // findAllByMember
    public List<Video> findAllByMember(Long memberId) {
        return em.createQuery("select v from Video v where v.member.id = :memberId", Video.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
