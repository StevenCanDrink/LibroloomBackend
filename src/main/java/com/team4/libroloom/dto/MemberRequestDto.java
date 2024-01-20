package com.team4.libroloom.dto;

import com.team4.libroloom.domain.Gender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MemberRequestDto {
    private String username;
    private String password;
    private String email;
    private Gender gender;
}
