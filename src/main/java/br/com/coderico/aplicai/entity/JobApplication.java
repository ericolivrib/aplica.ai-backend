package br.com.coderico.aplicai.entity;

import br.com.coderico.aplicai.exception.InvalidAccessException;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String position;

    @Column
    private String company;

    @Column
    private String url;

    @Column(name = "resume_path")
    private String resumePath;

    @Column(name = "cover_letter_path")
    private String coverLetterPath;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void verifyOwner(Long userId) {
        if (!Objects.equals(user.getId(), userId)) {
            throw new InvalidAccessException("Acesso negado à esta candidatura");
        }
    }
}
