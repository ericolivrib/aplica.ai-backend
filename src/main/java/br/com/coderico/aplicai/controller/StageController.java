package br.com.coderico.aplicai.controller;

import br.com.coderico.aplicai.dto.ApiListResponse;
import br.com.coderico.aplicai.dto.ApiResponse;
import br.com.coderico.aplicai.dto.StageCreateRequest;
import br.com.coderico.aplicai.dto.StageResponse;
import br.com.coderico.aplicai.security.UserAuthenticated;
import br.com.coderico.aplicai.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/applications/{applicationId}/stages")
public class StageController {

    private final StageService stageService;

    @GetMapping
    public ResponseEntity<ApiListResponse<StageResponse>> getApplicationStages(@PathVariable Long applicationId, @AuthenticationPrincipal UserAuthenticated currentUser) {
        List<StageResponse> stages = stageService.getApplicationStages(applicationId, currentUser.getId());
        var response = new ApiListResponse<>("Etapas da candidatura", stages);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StageResponse>> createStage(@PathVariable Long applicationId,
                                                                  @AuthenticationPrincipal UserAuthenticated currentUser,
                                                                  @RequestBody StageCreateRequest request) {
        StageResponse createdStage = stageService.createStage(applicationId, currentUser.getId(), request);

        var response = new ApiResponse<>("Etapa adicionada com sucesso", createdStage);
        URI location = URI.create(String.format("/api/v1/applications/%d/stages/%d", applicationId, createdStage.id()));

        return ResponseEntity.created(location)
                .body(response);
    }
}
