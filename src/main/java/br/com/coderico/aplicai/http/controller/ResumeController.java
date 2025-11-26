package br.com.coderico.aplicai.http.controller;

import br.com.coderico.aplicai.dto.ApiListResponse;
import br.com.coderico.aplicai.dto.ApiResponse;
import br.com.coderico.aplicai.dto.ResumeCreateRequest;
import br.com.coderico.aplicai.entity.resume.Resume;
import br.com.coderico.aplicai.security.UserAuthenticated;
import br.com.coderico.aplicai.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<ApiResponse<Resume>> createResume(@RequestBody ResumeCreateRequest request, @AuthenticationPrincipal UserAuthenticated currentUser) {
        Resume resume = resumeService.createResume(request, currentUser.getId());
        var response = new ApiResponse<>("Currículo criado com sucesso", resume);

        return ResponseEntity.created(URI.create("/api/v1/resumes/" + resume.getId()))
                .body(response);
    }

    @GetMapping
    public ResponseEntity<ApiListResponse<Resume>> getUserResumes(@AuthenticationPrincipal UserAuthenticated currentUser) {
        List<Resume> resumes = resumeService.getUserResumes(currentUser.getId());
        var response = new ApiListResponse<>("Currículos do usuário", resumes);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<ApiResponse<Resume>> getResume(@PathVariable("resumeId") String resumeId, @AuthenticationPrincipal UserAuthenticated currentUser) {
        Resume resume = resumeService.getResume(resumeId, currentUser.getId());
        var response = new ApiResponse<>("Currículo solicitado", resume);
        return ResponseEntity.ok(response);
    }
}
