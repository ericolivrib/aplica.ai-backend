package br.com.coderico.aplicai.dto;

import br.com.coderico.aplicai.entity.resume.ContactInfo;
import br.com.coderico.aplicai.entity.resume.Education;
import br.com.coderico.aplicai.entity.resume.Experience;
import br.com.coderico.aplicai.entity.resume.Skill;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ResumeCreateRequest(

        @NotBlank
        String name,

        @NotBlank
        String title,

        @NotNull
        ContactInfo contactInfo,

        @NotBlank
        String summary,

        @NotNull
        List<Skill> skills,

        @NotNull
        List<Experience> experiences,

        @NotNull
        List<Education> educations
) {
}
