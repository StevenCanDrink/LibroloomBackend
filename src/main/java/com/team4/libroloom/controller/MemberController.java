package com.team4.libroloom.controller;

import com.team4.libroloom.domain.Member;
import com.team4.libroloom.dto.MemberResDto;
import com.team4.libroloom.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin()
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/")
    public MemberResDto createMember(@RequestBody Member member) {
        return memberService.join(member);
    }

    @GetMapping("/")
    public List<MemberResDto> getAll() {
        return memberService.findByAll();
    }

    @GetMapping("/{id}")
    public Optional<MemberResDto> getOne(@PathVariable("id") Long id) {
        return memberService.findOne(id);
    }

    @PostMapping("/detail")
    public Optional<MemberResDto> getMember(@RequestBody Member member) {

        return memberService.findByNameEmail(member.getUsername());
    }


    @PostMapping("/login")
    public Optional<MemberResDto> login(@RequestBody Member member) {
        return memberService.checkValidLogin(member.getUsername(), member.getPassword());
    }
}
