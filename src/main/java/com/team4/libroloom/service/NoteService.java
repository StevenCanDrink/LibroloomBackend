package com.team4.libroloom.service;

import com.team4.libroloom.domain.Note;
import com.team4.libroloom.dto.NoteDTO;
import com.team4.libroloom.exception.ResourceNotFoundException;
import com.team4.libroloom.mapper.NoteMapper;
import com.team4.libroloom.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    /**
     * Save note
     */
    @Transactional
    public NoteDTO saveNote(Note note) {
        note.setCreateAt(LocalDateTime.now());
        noteRepository.save(note);
        return NoteMapper.mapToNoteDTO(note);
    }

    /**
     * Update note
     */
    @Transactional
    public NoteDTO updateNote(Long id, Note note) {

        Note findNote = noteRepository.findOne(id);
        if (findNote != null) {
            findNote.setTextNoted(note.getTextNoted());
            findNote.setCreateAt(LocalDateTime.now());
            noteRepository.save(findNote);
        } else {
            throw new ResourceNotFoundException(findNote.getId() + "does not exist!");
        }

        return NoteMapper.mapToNoteDTO(findNote);

    }

    /**
     * Retrieve a note
     */

    public NoteDTO findNote(Long noteId) {
        Note note = noteRepository.findOne(noteId);
        if (note != null) return NoteMapper.mapToNoteDTO(note);
        else throw new ResourceNotFoundException(noteId + "does not exist!");

    }

    /**
     * Retrieve all note
     */

    public List<NoteDTO> findNotes(Long videoId) {
        List<Note> notes = noteRepository.findAllByVideo(videoId);

        return notes
                .stream()
                .map(NoteMapper::mapToNoteDTO)
                .collect(Collectors.toList());
    }

    /**
     * delete note
     */
    @Transactional
    public void deleteNote(Long noteId) {
        Note note = noteRepository.findOne(noteId);
        if (note != null) {
            noteRepository.deleteNote(noteId);
        } else throw new ResourceNotFoundException(noteId + "does not exist!");
    }
}
