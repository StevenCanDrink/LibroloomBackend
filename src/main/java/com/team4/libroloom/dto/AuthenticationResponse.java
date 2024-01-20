package com.team4.libroloom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {
    private String token;
    private MemberResDto memberResDto;

    public AuthenticationResponse(String token, MemberResDto memberResDto) {
        this.token = token;
        this.memberResDto = memberResDto;
    }
}
