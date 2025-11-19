package br.com.coderico.aplicai.service;

import br.com.coderico.aplicai.dto.JobApplicationCreateRequest;
import br.com.coderico.aplicai.dto.JobApplicationCreatedResponse;
import br.com.coderico.aplicai.dto.JobApplicationResponse;
import br.com.coderico.aplicai.entity.JobApplication;
import br.com.coderico.aplicai.entity.User;
import br.com.coderico.aplicai.mapper.JobApplicationMapper;
import br.com.coderico.aplicai.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

        JobApplication application = new JobApplication();
        application.setUser(user);
        application.setCompany(request.company());
        application.setPosition(request.position());
        application.setUrl(request.url());

        repository.save(application);
        return mapper.toJobApplicationCreatedResponse(application);
    }

    public List<JobApplicationResponse> getUserApplications(Long userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(mapper::toJobApplicationResponse)
                .collect(Collectors.toList());
    }
}
