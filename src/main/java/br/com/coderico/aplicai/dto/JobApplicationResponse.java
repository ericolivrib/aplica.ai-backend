package br.com.coderico.aplicai.dto;

public record JobApplicationResponse(
        Long id,
        Long userId,
        String position,
        String company,
        String url,
        String resumePath,
        String coverLetterPath
) {
}
