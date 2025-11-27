package br.com.coderico.aplicai.http.controller;

import br.com.coderico.aplicai.dto.*;
import br.com.coderico.aplicai.security.UserAuthenticated;
import br.com.coderico.aplicai.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/applications")
public class JobApplicationController {

    private final JobApplicationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<JobApplicationCreatedResponse>> createJobApplication(@Valid @RequestBody JobApplicationCreateRequest request, @AuthenticationPrincipal UserAuthenticated currentUser) {
        JobApplicationCreatedResponse application = service.createJobApplication(request, currentUser.getId());

        var response = new ApiResponse<>("Candidatura criada com sucesso", application);
        URI location = URI.create("/api/v1/applications/" + application.id());

        return ResponseEntity.created(location)
                .body(response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiListResponse<JobApplicationResponse>> getUserApplications(@AuthenticationPrincipal UserAuthenticated currentUser) {
        List<JobApplicationResponse> applications = service.getUserApplications(currentUser.getId());

        String message = applications.isEmpty() ? "Usuário ainda não possui candidaturas" : "Candidaturas do usuário";

        var response = new ApiListResponse<>(message, applications);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<ApiResponse<JobApplicationResponse>> getJobApplication(@PathVariable Long applicationId, @AuthenticationPrincipal UserAuthenticated currentUser) {
        JobApplicationResponse application = service.getJobApplication(currentUser.getId(), applicationId);

        var response = new ApiResponse<>("Candidatura solicitada", application);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{applicationId}")
    public ResponseEntity<ApiResponse<JobApplicationResponse>> updateJobApplication(@PathVariable Long applicationId,
                                                                                    @Valid @RequestBody JobApplicationCreateRequest request,
                                                                                    @AuthenticationPrincipal UserAuthenticated currentUser) {
        JobApplicationResponse application = service.updateJobApplication(currentUser.getId(), applicationId, request);
        var response = new ApiResponse<>("Candidatura atualizada com sucesso", application);
        return ResponseEntity.ok(response);
    }
}
