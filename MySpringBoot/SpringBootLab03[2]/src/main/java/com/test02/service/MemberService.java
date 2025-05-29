package com.test02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.test02.entity.Member;
import com.test02.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
public class MemberService { 
 
 @Autowired 
    private  MemberRepository memberRepository; 
 
     
    public void saveOrUpdateMember(OAuth2User oauth2User) { 
        
    	String email = oauth2User.getAttribute("email"); 
        String name = oauth2User.getAttribute("name");
        
        Member member = memberRepository.findByUsername(name) 
                .orElse(new Member()); 
        
        //member.setUsername(email); 
        
        member.setUsername(name); 
        
        if (member.getPassword() == null) { 
            member.setPassword("oauth2user");  
        } 
 
        memberRepository.save(member); 
        System.out.println("저장된 사용자: " + 
member.getUsername()); 
    } 
}        
    