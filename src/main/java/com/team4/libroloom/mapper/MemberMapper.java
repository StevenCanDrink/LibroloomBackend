package com.team4.libroloom.mapper;

import com.team4.libroloom.domain.Member;
import com.team4.libroloom.dto.MemberResDto;

public class MemberMapper {

    public static MemberResDto mapToMemberDTO(Member member) {
        return new MemberResDto(
                member.getId(), member.getEmail(),
                member.getUsername(),
                member.getGender()
        );
    }

    public static Member mapToMember(MemberResDto memberResDto) {
        Member member = new Member();
        member.setId(memberResDto.getId());
        member.setUsername(memberResDto.getUsername());
        member.setEmail(memberResDto.getEmail());
        member.setGender(memberResDto.getGender());
        return member;
    }
}
