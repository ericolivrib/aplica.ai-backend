package br.com.coderico.aplicai.dto;

public record ResumeFileResponse(
        byte[] file,
        String filename
) {
}
