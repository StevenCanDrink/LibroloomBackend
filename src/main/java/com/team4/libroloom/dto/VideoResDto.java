package com.team4.libroloom.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team4.libroloom.domain.Member;
import com.team4.libroloom.domain.Note;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoResDto {

    private Long id;
    private String name;

    private URL url;

    private URL image;

    private LocalDateTime uploadedDate = LocalDateTime.now();

    private int Length;

    private MemberResDto memberResDto;
}
