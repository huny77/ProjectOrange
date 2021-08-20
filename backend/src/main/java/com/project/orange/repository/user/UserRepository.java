package com.project.orange.repository.user;

import com.project.orange.entity.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    Users findByUsername(String username);
    Users findByEmail(String email);
    Users findByUserId(Long userId);
    void deleteById(Long userId);
}
