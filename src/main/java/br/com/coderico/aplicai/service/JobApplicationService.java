package br.com.coderico.aplicai.service;

import br.com.coderico.aplicai.dto.JobApplicationCreateRequest;
import br.com.coderico.aplicai.dto.JobApplicationCreatedResponse;
import br.com.coderico.aplicai.dto.JobApplicationResponse;
import br.com.coderico.aplicai.entity.JobApplication;
import br.com.coderico.aplicai.entity.User;
import br.com.coderico.aplicai.exception.EntityNotFoundException;
import br.com.coderico.aplicai.mapper.JobApplicationMapper;
import br.com.coderico.aplicai.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;
    private final JobApplicationMapper mapper;
    private final UserService userService;

    public JobApplicationCreatedResponse createJobApplication(JobApplicationCreateRequest request, Long userId) {
        User user = userService.getUser(userId);

        JobApplication application = mapper.toJobApplication(request, user);

        repository.save(application);
        return mapper.toJobApplicationCreatedResponse(application);
    }

    public List<JobApplicationResponse> getUserApplications(Long userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(mapper::toJobApplicationResponse)
                .collect(Collectors.toList());
    }

    public JobApplication getJobApplication(Long applicationId) {
        return repository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("Candidatura não encontrada"));
    }

    public JobApplicationResponse getJobApplication(Long userId, Long applicationId) {
        JobApplication application = getJobApplication(applicationId);

        application.verifyOwner(userId);

        return mapper.toJobApplicationResponse(application);
    }

    public JobApplicationResponse updateJobApplication(Long applicationId, Long userId, JobApplicationCreateRequest application) {
        JobApplication applicationToUpdate = getJobApplication(applicationId);
        applicationToUpdate.verifyOwner(userId);

        applicationToUpdate.setUrl(application.url());
        applicationToUpdate.setCompany(application.company());
        applicationToUpdate.setPosition(application.position());
        repository.save(applicationToUpdate);

        return mapper.toJobApplicationResponse(applicationToUpdate);
    }
}
