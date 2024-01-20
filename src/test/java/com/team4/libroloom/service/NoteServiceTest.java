package com.team4.libroloom.service;

import com.team4.libroloom.domain.Gender;
import com.team4.libroloom.domain.Member;
import com.team4.libroloom.domain.Note;
import com.team4.libroloom.domain.Video;
import com.team4.libroloom.dto.MemberResDto;
import com.team4.libroloom.dto.NoteDTO;
import com.team4.libroloom.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class NoteServiceTest {

    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private VideoService videoService;

    @Test
    void saveNote_savesAndReturnsNoteDTO() {
        Member member = new Member();
        member.setEmail("kimhoo@gmail.com");
        member.setPassword("123333");
        member.setGender(Gender.MALE);
        member.setUsername("kimhoo");

        MemberResDto memberResDto = memberService.join(member);

        Video video1 = new Video();
        video1.setMember(member);
        videoService.saveVideo(video1);

        Note note = new Note();
        note.setVideo(video1);

        NoteDTO result = noteService.saveNote(note);

        // Assert expected DTO content based on persisted note
        assertNotNull(result.getId()); // Ensure ID is assigned
        assertNotNull(result.getCreateAt()); // Ensure createAt is set
        // ... (assert other fields)
    }

    @Test
    void findNotes_returnsNoteDTOsForVideo() {
        Member member = new Member();
        member.setEmail("kimhoo@gmail.com");
        member.setPassword("123333");
        member.setGender(Gender.MALE);
        member.setUsername("kimhoo");

        MemberResDto memberResDto = memberService.join(member);

        Video video = new Video();
        video.setMember(member);
        videoService.saveVideo(video);

        Note note = new Note();
        note.setVideo(video);

        Note note1 = new Note();
        note1.setVideo(video);

        noteRepository.save(note);
        noteRepository.save(note1);

        List<NoteDTO> result = noteService.findNotes(video.getId());

        assertEquals(2, result.size());
        // Assert expected DTO content based on persisted notes
        assertEquals(note1.getTextNoted(), result.get(0).getTextNoted());
        // ... (assert other fields)
    }

    @Test
    void findNotes_handlesEmptyList() {
        Member member = new Member();
        member.setEmail("kimhoo@gmail.com");
        member.setPassword("123333");
        member.setGender(Gender.MALE);
        member.setUsername("kimhoo");

        MemberResDto memberResDto = memberService.join(member);

        Video video = new Video();
        video.setMember(member);
        videoService.saveVideo(video);

        List<NoteDTO> result = noteService.findNotes(video.getId());

        assertTrue(result.isEmpty());
    }
}