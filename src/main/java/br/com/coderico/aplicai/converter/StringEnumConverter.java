package br.com.coderico.aplicai.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public interface StringEnumConverter<T extends Enum<T>> extends AttributeConverter<T, String> {
}
