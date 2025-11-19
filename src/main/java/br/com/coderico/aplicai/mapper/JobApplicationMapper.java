package br.com.coderico.aplicai.mapper;

import br.com.coderico.aplicai.dto.JobApplicationCreateRequest;
import br.com.coderico.aplicai.dto.JobApplicationCreatedResponse;
import br.com.coderico.aplicai.dto.JobApplicationResponse;
import br.com.coderico.aplicai.entity.JobApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface JobApplicationMapper {

    @Mappings({
            @Mapping(target = "id",  ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "resumePath", ignore = true),
            @Mapping(target = "coverLetterPath", ignore = true)
    })
    JobApplication toJobApplication(JobApplicationCreateRequest dto);

    @Mapping(target = "userId", source = "user.id")
    JobApplicationCreatedResponse toJobApplicationCreatedResponse(JobApplication entity);

    @Mapping(target = "userId", source = "user.id")
    JobApplicationResponse toJobApplicationResponse(JobApplication dto);
}
