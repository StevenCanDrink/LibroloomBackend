package com.team4.libroloom.mapper;

import com.team4.libroloom.domain.Note;
import com.team4.libroloom.domain.Video;
import com.team4.libroloom.dto.NoteDTO;
import com.team4.libroloom.dto.VideoResDto;

import java.util.Optional;

public class NoteMapper {
    public static NoteDTO mapToNoteDTO(Note note){
        return new NoteDTO(
                note.getId(),
                note.getTimeNoted(),
                note.getTextNoted(),
                Optional.ofNullable(note.getVideo())
                        .map(VideoMapper::mapToVideoDto)
                        .orElse(null),
                note.getCreateAt()
        );
    }

    public static Note mapToNote (NoteDTO noteDTO){
        Note note = new Note();
        note.setId(noteDTO.getId());
        note.setTimeNoted(noteDTO.getTimeNoted());
        note.setTextNoted(noteDTO.getTextNoted());
        note.setVideo(VideoMapper.mapToVideo(noteDTO.getVideoResDto()));
        note.setCreateAt(noteDTO.getCreateAt());
        return note;
    }
}
