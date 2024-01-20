package com.team4.libroloom.dto;

import com.team4.libroloom.domain.Gender;
import com.team4.libroloom.domain.Member;
import com.team4.libroloom.domain.Video;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResDto {
    private Long id;
    private String email;
    private String username;
    private Gender gender;
}
