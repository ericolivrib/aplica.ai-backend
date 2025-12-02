package br.com.coderico.aplicai.entity.resume;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
public class Education {

    @Field
    private String institution;

    @Field
    private String course;

    @Field
    private String city;

    @Field
    private LocalDate startDate;

    @Field
    private LocalDate endDate;

    @Field
    private boolean current;
}
