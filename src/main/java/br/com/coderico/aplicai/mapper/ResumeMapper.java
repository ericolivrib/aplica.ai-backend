package br.com.coderico.aplicai.mapper;

import br.com.coderico.aplicai.dto.ResumeCreateRequest;
import br.com.coderico.aplicai.entity.resume.Resume;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResumeMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "userId", ignore = true)
    })
    Resume toResumeEntity(ResumeCreateRequest dto);
}
