package br.com.coderico.aplicai.mapper;

import br.com.coderico.aplicai.dto.StageResponse;
import br.com.coderico.aplicai.entity.Stage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StageMapper {

    StageResponse toStageResponse(Stage stage);
}
