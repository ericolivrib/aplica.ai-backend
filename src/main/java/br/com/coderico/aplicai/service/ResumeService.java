package br.com.coderico.aplicai.service;

import br.com.coderico.aplicai.dto.ResumeCreateRequest;
import br.com.coderico.aplicai.entity.resume.Resume;
import br.com.coderico.aplicai.exception.EntityNotFoundException;
import br.com.coderico.aplicai.exception.InvalidAccessException;
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

    public Resume createResume(ResumeCreateRequest resumeCreateRequest, Long userId) {
        Resume resume = mapper.toResumeEntity(resumeCreateRequest);
        resume.setUserId(userId);

        repository.save(resume);
        return resume;
    }

    public Resume getResume(String id, Long userId) {
        Resume resume = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Currículo não encontrado"));

        if (!resume.getUserId().equals(userId)) {
            throw new InvalidAccessException("Acesso não permitido a este currículo");
        }

        return resume;
    }

    public List<Resume> getUserResumes(Long userId) {
        return repository.findByUserId(userId);
    }
}
