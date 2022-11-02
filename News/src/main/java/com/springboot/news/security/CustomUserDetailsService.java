package com.springboot.news.security;

import com.springboot.news.entity.Role;
import com.springboot.news.entity.User;
import com.springboot.news.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // 1. 사용자를 load 하는 로직을 짜야한다.
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {


        //4. 사용자 객체를 스필ㅇ에서 제공하는 사용자 객체로 변환해야 하는 일을 해야한다.
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email:" + usernameOrEmail));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    //설정 규칙을 간단한 컬렉션에 매핑
    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        //SimpleGrantedAuthority() 인터페이스를 구현하기 위해 다시 영역을 구현하는 클래스
        //role.getName())).collect(Collectors.toList() : 결과를 수정하고
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
