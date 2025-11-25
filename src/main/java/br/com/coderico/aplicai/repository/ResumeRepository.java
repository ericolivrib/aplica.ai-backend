package br.com.coderico.aplicai.repository;

import br.com.coderico.aplicai.entity.resume.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends MongoRepository<Resume, String> {

    List<Resume> findByUserId(Long userId);
}
