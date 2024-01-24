package com.dydzik.jwtauthboot3security6.service;

import com.dydzik.jwtauthboot3security6.dto.JwtAuthenticationResponse;
import com.dydzik.jwtauthboot3security6.dto.SignInRequest;
import com.dydzik.jwtauthboot3security6.dto.SignUpRequest;
import com.dydzik.jwtauthboot3security6.entity.Role;
import com.dydzik.jwtauthboot3security6.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * User Signup
     *
     * @param request userdata
     * @return token
     */
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    /**
     * User authentication
     *
     * @param request userdata
     * @return token
     */
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
