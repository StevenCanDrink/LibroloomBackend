package com.team4.libroloom.controller;

import com.team4.libroloom.domain.Note;
import com.team4.libroloom.dto.NoteDTO;
import com.team4.libroloom.service.NoteService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/{videoId}")
    public List<NoteDTO> getNoteList(@PathVariable("videoId") Long videoId){
        return noteService.findNotes(videoId);
    }

    @GetMapping("/search/{id}")
    public NoteDTO getNote(@PathVariable("id") Long noteId){
        return noteService.findNote(noteId);
    }

    @PostMapping("/create")
    public Optional<NoteDTO> createNote(@RequestBody Note note){
        return Optional.ofNullable(noteService.saveNote(note));
    }

    @PutMapping("/{id}")
    public NoteDTO updateNote(@PathVariable("id") Long id, @RequestBody Note note) {
        return noteService.updateNote(id,note);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable("id") Long noteId){
        noteService.deleteNote(noteId);
    }
}
