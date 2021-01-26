package com.myLearning.accountSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myLearning.accountSystem.entity.User;
import com.myLearning.accountSystem.entity.UserDetailsImpl;
import com.myLearning.accountSystem.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(login + " not found"));

        System.out.println(UserDetailsImpl.getUserDetails(user).getAuthorities());
        return UserDetailsImpl.getUserDetails(user);
    }
}
