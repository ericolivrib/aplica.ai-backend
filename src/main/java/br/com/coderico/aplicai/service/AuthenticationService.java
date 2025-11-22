package br.com.coderico.aplicai.service;

import br.com.coderico.aplicai.dto.JwtResponse;
import br.com.coderico.aplicai.dto.LoginRequest;
import br.com.coderico.aplicai.security.UserAuthenticated;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public JwtResponse authenticate(LoginRequest login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.email(), login.password())
        );

        UserAuthenticated user = (UserAuthenticated) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        Instant expiration = jwtService.getTokenExpiration(token);

        return new JwtResponse(token, expiration);
    }
}
