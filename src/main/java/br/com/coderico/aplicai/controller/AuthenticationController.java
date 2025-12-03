package br.com.coderico.aplicai.controller;

import br.com.coderico.aplicai.dto.ApiResponse;
import br.com.coderico.aplicai.dto.JwtResponse;
import br.com.coderico.aplicai.dto.LoginRequest;
import br.com.coderico.aplicai.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<JwtResponse>> authenticate(@RequestBody LoginRequest request) {
        JwtResponse jwt = authenticationService.authenticate(request);
        var response = new ApiResponse<>("Usuário autenticado com sucesso", jwt);
        return ResponseEntity.ok(response);
    }
}
