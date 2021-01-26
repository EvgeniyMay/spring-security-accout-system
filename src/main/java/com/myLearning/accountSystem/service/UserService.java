package com.myLearning.accountSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myLearning.accountSystem.dto.UserDTO;
import com.myLearning.accountSystem.entity.Role;
import com.myLearning.accountSystem.entity.User;
import com.myLearning.accountSystem.repository.UserRepository;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signupNewUser(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    private boolean loginExists(String login) {
        // == null
        return userRepository.findByLogin(login).isPresent();
    }
}
