package com.sec02.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sec02.entity.Member;
import com.sec02.repository.MemberRepository;

import lombok.RequiredArgsConstructor;



@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
	
	
	private final MemberRepository memberRepository;
	
    @Transactional
    public Member createMember(String username, String email) {
        Member member = new Member();
        member.setUsername(username);
        member.setEmail(email);
        return memberRepository.save(member);
    }
}
