package com.security.auth;

import com.security.config.JwtService;
import com.security.token.Token;
import com.security.token.TokenRepository;
import com.security.token.ToketType;
import com.security.user.Role;
import com.security.user.User;
import com.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var savedUser = repository.save(user);
        var JwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, JwtToken);
        return AuthenticationResponse.builder()
                .token(JwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var JwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user,JwtToken);
        return AuthenticationResponse.builder()
                .token(JwtToken)
                .build();
    }

    private void revokeAllUserTokens(User user){
        var validTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validTokens.isEmpty()){
            return;
        }
        validTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validTokens);
    }

    private void saveUserToken(User user, String JwtToken) {
        var token = Token.builder()
                .user(user)
                .token(JwtToken)
                .tokenType(ToketType.Bearer)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
