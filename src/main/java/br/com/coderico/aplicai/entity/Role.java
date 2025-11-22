package br.com.coderico.aplicai.entity;

import br.com.coderico.aplicai.converter.StringEnumConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("admin"),
    USER("user");

    private final String label;

    private static final Map<String, Role> map = new HashMap<>();

    static {
        for (Role role : Role.values())
            map.put(role.getLabel(), role);
    }

    public static Role of(String role) {
        return Optional.of(map.get(role))
                .orElseThrow(IllegalArgumentException::new);
    }

    public static class Converter implements StringEnumConverter<Role> {

        @Override
        public String convertToDatabaseColumn(Role role) {
            return role.getLabel();
        }

        @Override
        public Role convertToEntityAttribute(String role) {
            return Role.of(role);
        }
    }
}
