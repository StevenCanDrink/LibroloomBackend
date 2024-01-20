package com.team4.libroloom.service;

import com.team4.libroloom.dto.MemberResDto;
import com.team4.libroloom.domain.Member;
import com.team4.libroloom.mapper.MemberMapper;
import com.team4.libroloom.model.SecurityMember;
import com.team4.libroloom.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * Join Member
     */
    @Transactional
    public MemberResDto join(Member member) {
        validateDuplicateMember(member);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
        return MemberMapper.mapToMemberDTO(member);
    }

    /**
     * Duplicate Member
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByEmailOrUsername(member.getUsername(), member.getEmail())
                .ifPresent(existingMember -> {
                    throw new IllegalStateException("Member with email " + existingMember.getEmail() + " already exists.");
                });
    }

    /**
     * Find Member
     */

    public Optional<MemberResDto> findOne(Long memberId) {
        return memberRepository.findOne(memberId).map(MemberMapper::mapToMemberDTO);
    }

    public Optional<MemberResDto> checkValidLogin(String emailOrUsername, String password) {
        return memberRepository.findByEmailOrUsername(emailOrUsername)
                .filter(member -> passwordEncoder.matches(password, member.getPassword()))
                .map(MemberMapper::mapToMemberDTO);
    }

    public List<MemberResDto> findByAll() {
        return memberRepository.findByAll().stream()
                .map(MemberMapper::mapToMemberDTO)
                .collect(Collectors.toList());
    }

    public Optional<MemberResDto> findByNameEmail(String username){
        return memberRepository.findByEmailOrUsername(username).map(MemberMapper::mapToMemberDTO);
    }

    @Override
    public SecurityMember loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByEmailOrUsername(username).map(SecurityMember::new).orElseThrow(()->new UsernameNotFoundException(":((((((("));
    }
}
