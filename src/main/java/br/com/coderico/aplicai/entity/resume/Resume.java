package br.com.coderico.aplicai.entity.resume;

import br.com.coderico.aplicai.exception.InvalidAccessException;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Builder
@Data
@Document(collection = "resumes")
public class Resume {

    @MongoId
    private String id;

    @Field
    private Long userId;

    @Field
    private String name;

    @Field
    private String title;

    private ContactInfo contactInfo;

    @Field
    private String summary;

    @Field
    private List<Skill> skills;

    @Field("experiences")
    private List<Experience> experiences;

    @Field("educations")
    private List<Education> educations;

    public void verifyOwner(Long userId) {
        if (!this.userId.equals(userId)) {
            throw new InvalidAccessException("Acesso não autorizado a este currículo");
        }
    }
}
