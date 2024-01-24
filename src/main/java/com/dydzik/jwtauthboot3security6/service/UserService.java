package com.dydzik.jwtauthboot3security6.service;

import com.dydzik.jwtauthboot3security6.entity.Role;
import com.dydzik.jwtauthboot3security6.entity.User;
import com.dydzik.jwtauthboot3security6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("User with this username already exists");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }

        return save(user);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Get User by Username
     * Used for Spring Security
     *
     * @return user
     */
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    /**
     * Get current user
     *
     * @return current user
     */
    public User getCurrentUser() {
        // getting username from Spring Security context
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    /**
     * Set current user's role to admin
     * <p>
     * Used for testing
     */
    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }
}
