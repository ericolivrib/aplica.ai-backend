package br.com.coderico.aplicai.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String profession;

    @OneToMany(mappedBy = "user",  fetch = FetchType.LAZY)
    private List<JobApplication> jobApplications;
}
