package br.com.coderico.aplicai.dto;

public record StageResponse(
        Long id,
        Long applicationId,
        String title,
        String type
) {
}
