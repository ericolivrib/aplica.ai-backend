package br.com.coderico.aplicai.repository;

import br.com.coderico.aplicai.entity.JobApplication;
import br.com.coderico.aplicai.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByUserId(Long userId);

    Long user(User user);
}
