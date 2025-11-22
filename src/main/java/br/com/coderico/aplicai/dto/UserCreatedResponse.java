package br.com.coderico.aplicai.dto;

public record UserCreatedResponse(
        Long id,
        String name,
        String email,
        String profession
) {
}
