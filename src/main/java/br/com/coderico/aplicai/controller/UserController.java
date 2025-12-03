package br.com.coderico.aplicai.controller;

import br.com.coderico.aplicai.dto.ApiResponse;
import br.com.coderico.aplicai.dto.UserCreateRequest;
import br.com.coderico.aplicai.dto.UserCreatedResponse;
import br.com.coderico.aplicai.security.UserAuthenticated;
import br.com.coderico.aplicai.service.JobApplicationService;
import br.com.coderico.aplicai.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<UserCreatedResponse>> createUser(@Valid @RequestBody UserCreateRequest request) {
        UserCreatedResponse user =  userService.createUser(request);

        var response = new ApiResponse<>("Usuário criado com sucesso!", user);

        var location = URI.create("/api/v1/users");

        return ResponseEntity.created(location)
                .body(response);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<UserCreatedResponse>> getProfile(@AuthenticationPrincipal UserAuthenticated currentUser) {
        UserCreatedResponse user = userService.getUser(currentUser.getUsername());

        // TODO: Adicionar camada de cache

        var response = new ApiResponse<>("Perfil do usuário", user);

        return ResponseEntity.ok(response);
    }
}
