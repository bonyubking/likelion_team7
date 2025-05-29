package com.test01.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

import com.test01.entity.Member;
import com.test01.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor  // 1. 생성자값 주입 명시 2. NotNull 처리 @ 선언시
public class CustomUserDetailsService implements UserDetailsService { 
    
	@Autowired
	private final MemberRepository memberRepository; 
 
    @Override 
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
        Member member = memberRepository.findByUsername(username) 
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다.")); 
 
        return User.builder() 
                .username(member.getUsername()) 
                .password(member.getPassword()) 
                .roles(member.getRole().replace("ROLE_", "")) 
                .build(); 
    } 
} 
