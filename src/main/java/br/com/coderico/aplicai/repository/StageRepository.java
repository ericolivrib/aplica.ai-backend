package br.com.coderico.aplicai.repository;

import br.com.coderico.aplicai.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {

    List<Stage> findByApplicationId(Long applicationId);
}
