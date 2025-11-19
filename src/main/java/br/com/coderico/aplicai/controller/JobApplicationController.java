package br.com.coderico.aplicai.controller;

import br.com.coderico.aplicai.dto.*;
import br.com.coderico.aplicai.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/applications")
public class JobApplicationController {

    private final JobApplicationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<JobApplicationCreatedResponse>> createJobApplication(@Valid @RequestBody JobApplicationCreateRequest request) {
        Long userId = 1L;
        JobApplicationCreatedResponse application = service.createJobApplication(request, userId);

        var response = new ApiResponse<>("Candidatura criada com sucesso", application);
        URI location = URI.create("/api/v1/applications/" + application.id());

        return ResponseEntity.created(location)
                .body(response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiListResponse<JobApplicationResponse>> getUserApplications() {
        Long userId = 1L;
        List<JobApplicationResponse> applications = service.getUserApplications(userId);

        String message = applications.isEmpty() ? "Usuário ainda não possui candidaturas" : "Candidaturas do usuário";

        var response = new ApiListResponse<>(message, applications);
        return ResponseEntity.ok(response);
    }
}
