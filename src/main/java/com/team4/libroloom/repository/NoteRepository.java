package com.team4.libroloom.repository;

import com.team4.libroloom.domain.Note;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoteRepository {

    private final EntityManager em;

    // save
    public void save(Note note) {
        if (note.getId() == null) {
            em.persist(note);
        } else {
            em.merge(note);
        }
    }

    // findOne
    public Note findOne(Long id) {
        return em.find(Note.class, id);
    }

    // findAllByMember
    public List<Note> findAllByVideo(Long videoId) {
        return em.createQuery("select n from Note n where n.video.id = :videoId", Note.class)
                .setParameter("videoId", videoId)
                .getResultList();
    }

    //delete
    public void deleteNote(Long noteId) {
        Note note = findOne(noteId);
        em.remove(note);
    }
}
