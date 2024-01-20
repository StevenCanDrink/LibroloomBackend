package com.team4.libroloom.service;

import com.team4.libroloom.domain.Member;
import com.team4.libroloom.dto.AuthenticationRequest;
import com.team4.libroloom.dto.AuthenticationResponse;
import com.team4.libroloom.dto.MemberResDto;
import com.team4.libroloom.mapper.MemberMapper;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final MemberService memberService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = memberService.loadUserByUsername(request.getUsername());
        var jwtToken = jwtUtil.generateToken(user);
        MemberResDto memberResDto = MemberMapper.mapToMemberDTO(user.getMember());
        return new AuthenticationResponse(jwtToken, memberResDto);

    }
}