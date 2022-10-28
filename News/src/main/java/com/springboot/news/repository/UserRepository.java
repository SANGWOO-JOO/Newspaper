package com.springboot.news.repository;

import com.springboot.news.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 쿼리 메서드 작성
    // 메일로 유저 검색
    Optional<User>findByEmail(String email);
    Optional<User>findByUsernameOrEmail(String username, String email);
    Optional<User>findByUsername(String username);
    //사용자 이름에 대한 확인여부
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
