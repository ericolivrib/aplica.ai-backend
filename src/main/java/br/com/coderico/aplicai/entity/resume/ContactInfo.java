package br.com.coderico.aplicai.entity.resume;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Data
public class ContactInfo {

    @Field
    private String phone;

    @Field
    private String email;

    @Field
    private String currentCity;

    @Field
    private Map<String, String> socialMedias;
}
