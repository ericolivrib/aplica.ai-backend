package br.com.coderico.aplicai.mapper;

import br.com.coderico.aplicai.dto.UserCreateRequest;
import br.com.coderico.aplicai.dto.UserCreatedResponse;
import br.com.coderico.aplicai.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User fromUserCreateRequest(UserCreateRequest userCreateRequest);

    UserCreatedResponse toUserCreatedResponse(User user);
}
