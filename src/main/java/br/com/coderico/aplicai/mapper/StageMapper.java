package br.com.coderico.aplicai.mapper;

import br.com.coderico.aplicai.dto.StageCreateRequest;
import br.com.coderico.aplicai.dto.StageResponse;
import br.com.coderico.aplicai.entity.JobApplication;
import br.com.coderico.aplicai.entity.Stage;
import br.com.coderico.aplicai.entity.StageType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mapping;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StageMapper {

    @Mapping(target = "applicationId", source = "application.id")
    StageResponse toStageResponse(Stage stage);

    default Stage fromStageCreateRequest(StageCreateRequest dto, JobApplication application) {
        Stage stage = new Stage();
        stage.setTitle(dto.title());
        stage.setType(StageType.of(dto.type()));
        stage.setApplication(application);
        return stage;
    }
}
