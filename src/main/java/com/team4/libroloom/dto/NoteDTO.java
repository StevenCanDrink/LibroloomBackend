package com.team4.libroloom.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team4.libroloom.domain.Video;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {
    private Long id;
    private int timeNoted;
    private String textNoted;
    private VideoResDto videoResDto;
    private LocalDateTime createAt;
}
