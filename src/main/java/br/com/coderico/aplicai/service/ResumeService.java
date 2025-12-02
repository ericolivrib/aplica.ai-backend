package br.com.coderico.aplicai.service;

import br.com.coderico.aplicai.dto.ResumeCreateRequest;
import br.com.coderico.aplicai.dto.ResumeFileResponse;
import br.com.coderico.aplicai.entity.resume.Resume;
import br.com.coderico.aplicai.exception.EntityNotFoundException;
import br.com.coderico.aplicai.mapper.ResumeMapper;
import br.com.coderico.aplicai.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResumeService {

    private final ResumeRepository repository;
    private final ResumeMapper mapper;
    private final FileGenerationService fileGenerationService;

    public Resume createResume(ResumeCreateRequest resumeCreateRequest, Long userId) {
        Resume resume = mapper.toResumeEntity(resumeCreateRequest);
        resume.setUserId(userId);

        repository.save(resume);
        return resume;
    }

    public Resume getResume(String id, Long userId) {
        Resume resume = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Currículo não encontrado"));

        resume.verifyOwner(userId);

        return resume;
    }

    public List<Resume> getUserResumes(Long userId) {
        return repository.findByUserId(userId);
    }

    public ResumeFileResponse getResumeFile(String resumeId, Long userId) {
        Resume resume = getResume(resumeId, userId);

        byte[] resumeFile = fileGenerationService.generatePdf(resume);
        String filename = resume.getName() + " - " + resume.getTitle() + ".pdf";

        return new ResumeFileResponse(resumeFile, filename);
    }
}
