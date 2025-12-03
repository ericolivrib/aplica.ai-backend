package br.com.coderico.aplicai.mapper;

import br.com.coderico.aplicai.dto.JobApplicationCreateRequest;
import br.com.coderico.aplicai.dto.JobApplicationCreatedResponse;
import br.com.coderico.aplicai.dto.JobApplicationResponse;
import br.com.coderico.aplicai.entity.JobApplication;
import br.com.coderico.aplicai.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface JobApplicationMapper {

    @Mapping(target = "userId", source = "user.id")
    JobApplicationCreatedResponse toJobApplicationCreatedResponse(JobApplication entity);

    @Mapping(target = "userId", source = "user.id")
    JobApplicationResponse toJobApplicationResponse(JobApplication dto);

    default JobApplication toJobApplication(JobApplicationCreateRequest dto, User user) {
        var application = new JobApplication();
        application.setUser(user);
        application.setCompany(dto.company());
        application.setPosition(dto.position());
        application.setUrl(dto.url());

        return application;
    }
}
