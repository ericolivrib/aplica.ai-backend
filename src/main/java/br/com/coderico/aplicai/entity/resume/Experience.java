package br.com.coderico.aplicai.entity.resume;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Data
public class Experience {

    @Field
    private String company;

    @Field
    private String position;

    @Field
    private String city;

    @Field
    private LocalDate startDate;

    @Field
    private LocalDate endDate;

    @Field
    private boolean current;

    private List<String> activities;
}
