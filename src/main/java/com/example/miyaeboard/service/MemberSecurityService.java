package com.example.miyaeboard.service;

import com.example.miyaeboard.data.entity.Member;
import com.example.miyaeboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String nickname) {
        Optional<Member> _member = this.memberRepository.findBynickname(nickname);
        if (_member.isEmpty()) {
            // 사용자를 찾을 수 없을 때 null 반환
            return null;
        }
        Member member = _member.get();

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        return new org.springframework.security.core.userdetails.User(member.getNickname(), member.getPassword(), authorities);
    }
}
