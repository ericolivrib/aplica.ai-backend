package br.com.coderico.aplicai.entity;

import br.com.coderico.aplicai.converter.CharEnumConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN('A'),
    USER('U');

    private final char role;

    private static final Map<Character, Role> roles;

    static {
        roles = new HashMap<>();
        roles.put('A', Role.ADMIN);
        roles.put('U', Role.USER);
    }

    public static Role of(char role) {
        return Optional.of(roles.get(role))
                .orElseThrow(IllegalArgumentException::new);
    }

    public static class Converter implements CharEnumConverter<Role> {

        @Override
        public Character convertToDatabaseColumn(Role role) {
            return role.getRole();
        }

        @Override
        public Role convertToEntityAttribute(Character role) {
            return Role.of(role);
        }
    }
}
