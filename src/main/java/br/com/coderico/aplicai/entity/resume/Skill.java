package br.com.coderico.aplicai.entity.resume;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class Skill {

    @Field
    private String category;

    @Field
    private List<String> items;
}