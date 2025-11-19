package br.com.coderico.aplicai.dto;

public record JobApplicationCreatedResponse(
        Long id,
        Long userId,
        String position,
        String company,
        String url
) {
}
