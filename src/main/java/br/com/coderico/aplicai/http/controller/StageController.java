package br.com.coderico.aplicai.http.controller;

import br.com.coderico.aplicai.dto.ApiListResponse;
import br.com.coderico.aplicai.dto.StageResponse;
import br.com.coderico.aplicai.security.UserAuthenticated;
import br.com.coderico.aplicai.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
