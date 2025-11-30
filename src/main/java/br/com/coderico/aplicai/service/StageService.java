package br.com.coderico.aplicai.service;

import br.com.coderico.aplicai.dto.StageCreateRequest;
import br.com.coderico.aplicai.dto.StageResponse;
import br.com.coderico.aplicai.entity.JobApplication;
import br.com.coderico.aplicai.entity.Stage;
import br.com.coderico.aplicai.mapper.StageMapper;
import br.com.coderico.aplicai.repository.StageRepository;
import br.com.coderico.aplicai.security.UserAuthenticated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StageService {

    private final StageRepository repository;
    private final StageMapper mapper;
    private final JobApplicationService applicationService;

    public List<StageResponse> getApplicationStages(Long applicationId, Long userId) {
        applicationService.getJobApplication(userId, applicationId);

        List<Stage> stages = repository.findByApplicationId(applicationId);

        return stages.stream()
                .map(mapper::toStageResponse)
                .toList();
    }

    public StageResponse createStage(Long applicationId, Long userid, StageCreateRequest stageCreateRequest) {
        JobApplication application = applicationService.getJobApplication(applicationId);
        application.verifyOwner(userid);

        Stage stage = mapper.fromStageCreateRequest(stageCreateRequest, application);
        repository.save(stage);
        return mapper.toStageResponse(stage);
    }
}
