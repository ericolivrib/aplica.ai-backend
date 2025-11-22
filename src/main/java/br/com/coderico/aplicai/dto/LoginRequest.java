package br.com.coderico.aplicai.dto;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = "E-mail é obrigatório")
        String email,

        @NotEmpty(message = "Senha é obrigatória")
        String password
) {
}
