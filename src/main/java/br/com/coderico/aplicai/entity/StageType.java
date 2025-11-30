package br.com.coderico.aplicai.entity;

import br.com.coderico.aplicai.converter.StringEnumConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum StageType {

    INTERVIEW("Interview"),
    TEST("Test"),
    CULTURAL_FIT("Cultural Fit"),
    GROUP_DYNAMIC("Group Dynamic"),;

    private final String type;

    private static final Map<String, StageType> map = new HashMap<>();

    static {
        for (StageType type : StageType.values())
            map.put(type.type, type);
    }

    public static StageType of(String type) {
        return Optional.of(map.get(type))
                .orElseThrow(IllegalArgumentException::new);
    }

    public static class Converter implements StringEnumConverter<StageType> {

        @Override
        public String convertToDatabaseColumn(StageType enumType) {
            return enumType.type;
        }

        @Override
        public StageType convertToEntityAttribute(String stringType) {
            return of(stringType);
        }
    }
}
