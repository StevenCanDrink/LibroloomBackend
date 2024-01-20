package com.team4.libroloom.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter@Setter
@RequiredArgsConstructor
public class AuthenticationRequest {

    private String username;
    private String password;
}
