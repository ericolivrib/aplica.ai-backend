package br.com.coderico.aplicai.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

public record JobApplicationCreateRequest(
        @NotBlank(message = "Posição é obrigatória")
        String position,

        @NotBlank(message = "Empresa é obrigatória")
        String company,

        @URL(message = "URL inválida")
        String url
) {
}
