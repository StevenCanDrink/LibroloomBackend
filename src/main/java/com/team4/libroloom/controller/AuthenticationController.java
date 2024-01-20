package com.team4.libroloom.controller;

import com.team4.libroloom.domain.Member;
import com.team4.libroloom.dto.AuthenticationRequest;
import com.team4.libroloom.dto.AuthenticationResponse;
import com.team4.libroloom.dto.MemberRequestDto;
import com.team4.libroloom.service.AuthenticationService;
import com.team4.libroloom.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private final MemberService memberService;
    @Autowired
    @Lazy
    private final AuthenticationService authenticationService;

    public AuthenticationController(MemberService memberService, AuthenticationService authenticationService) {
        this.memberService = memberService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<String> register(@RequestBody MemberRequestDto member) {
        try {
            Member getData = new Member();
            getData.setUsername(member.getUsername());
            getData.setPassword(member.getPassword());
            getData.setEmail(member.getEmail());
            getData.setGender(member.getGender());
            memberService.join(getData);
            return ResponseEntity.ok("Register Success");
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }


    @PostMapping("/api/auth/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody
                                                        AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

}
