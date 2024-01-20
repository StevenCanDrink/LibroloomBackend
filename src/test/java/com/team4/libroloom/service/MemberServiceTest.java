package com.team4.libroloom.service;

import com.team4.libroloom.domain.Gender;
import com.team4.libroloom.domain.Member;
import com.team4.libroloom.dto.MemberResDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setEmail("kimhoo@gmail.com");
        member.setPassword("123333");
        member.setGender(Gender.MALE);
        member.setUsername("kimhoo");

        //when
        MemberResDto memberResDto = memberService.join(member);
        Optional<MemberResDto> memberResDto1 = memberService.findOne(memberResDto.getId());

        //then
        Assertions.assertThat(memberResDto.getId()).isEqualTo(memberResDto1.get().getId());
    }

    @Test
    void findOne_existingMember_returnsMemberDTO() {
        Long memberId = 1L;

        Member member = new Member();
        member.setEmail("kimhood@gmail.com");
        member.setPassword("123333");
        member.setGender(Gender.MALE);
        member.setUsername("kimhood");

        memberService.join(member);

        Optional<MemberResDto> result = memberService.findOne(memberId);

        assertTrue(result.isPresent());
        // Assert expected DTO content based on persisted member
    }

    @Test
    void findOne_nonExistingMember_returnsEmptyOptional() {
        Long memberId = 2L;

        Optional<MemberResDto> result = memberService.findOne(memberId);

        assertTrue(result.isEmpty());
    }

    @Test
    void checkValidLogin_validCredentials_returnsMemberDTO() {
        String emailOrUsername = "valid@email.com";
        String password = "correctpassword";
        Member member = new Member();
        member.setPassword(password);
        member.setEmail(emailOrUsername);
        member.setUsername(emailOrUsername);
        member.setGender(Gender.MALE);
        memberService.join(member);

        Optional<MemberResDto> result = memberService.checkValidLogin(emailOrUsername, password);

        assertTrue(result.isPresent());
        // Assert expected DTO content based on persisted member
    }

    @Test
    void checkInValidLogin_validCredentials_returnsMemberDTO() {
        String emailOrUsername = "valid@email.com";
        String password = "correctpassword";
        Member member = new Member();
        member.setPassword(password);
        member.setEmail(emailOrUsername);
        member.setUsername(emailOrUsername);
        member.setGender(Gender.MALE);
        memberService.join(member);

        Optional<MemberResDto> result = memberService.checkValidLogin(emailOrUsername, "wrongpassword");

        assertTrue(result.isEmpty());
        // Assert expected DTO content based on persisted member
    }

    @Test
    void findByAll() {
        String emailOrUsername = "valid@email.com";
        String password = "correctpassword";

        Member member1 = new Member();
        member1.setPassword(password);
        member1.setEmail(emailOrUsername);
        member1.setUsername(emailOrUsername);
        member1.setGender(Gender.MALE);

        Member member2 = new Member();
        member2.setPassword(password);
        member2.setEmail(emailOrUsername + "(1)");
        member2.setUsername(emailOrUsername);
        member2.setGender(Gender.MALE);

        memberService.join(member1);
        memberService.join(member2);

        List<MemberResDto> result = memberService.findByAll();

        assertEquals(2, result.size());
        // Assert expected DTO content based on persisted members
    }
}