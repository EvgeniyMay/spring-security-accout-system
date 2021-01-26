package com.myLearning.accountSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myLearning.accountSystem.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
}
