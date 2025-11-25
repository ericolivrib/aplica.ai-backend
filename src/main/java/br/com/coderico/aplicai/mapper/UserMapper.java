package br.com.coderico.aplicai.mapper;

import br.com.coderico.aplicai.dto.UserCreateRequest;
import br.com.coderico.aplicai.dto.UserCreatedResponse;
import br.com.coderico.aplicai.entity.Role;
import br.com.coderico.aplicai.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "role", ignore = true),
            @Mapping(target = "jobApplications", ignore = true)
    })
    User fromUserCreateRequest(UserCreateRequest userCreateRequest);

    default User fromUserCreateRequest(UserCreateRequest dto, PasswordEncoder  passwordEncoder) {
        String encodedPassword = passwordEncoder.encode(dto.password());

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(encodedPassword);
        user.setProfession(dto.profession());
        user.setRole(Role.USER);

        return user;
    }

    default UserCreatedResponse toUserCreatedResponse(User user) {
        return new UserCreatedResponse(user.getId(), user.getName(), user.getEmail(), user.getProfession(), user.getRole().getLabel());
    }
}
