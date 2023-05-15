package com.ass.brainbitesprototype.security;

import com.ass.brainbitesprototype.models.UserEntity;
import com.ass.brainbitesprototype.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Load core user information when user attempts to authenticate with Spring security.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findFirstByUsername(username);
        if (user != null) {
            User authenticatedUser = new User(
                    user.getEmail(),
                    user.getPassword(),
                    // Each role represented as a single authority.
                    user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList())
            );
            return authenticatedUser;
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
