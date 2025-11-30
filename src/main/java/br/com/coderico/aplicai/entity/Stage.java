package br.com.coderico.aplicai.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "stages")
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_id", insertable = false, updatable = false)
    private Long applicationId;

    @Column
    private String title;

    @Column
    @Convert(converter = StageType.Converter.class)
    private StageType type;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private JobApplication application;
}
