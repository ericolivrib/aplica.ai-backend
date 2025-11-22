package br.com.coderico.aplicai.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public interface CharEnumConverter<T extends Enum<T>> extends AttributeConverter<T, Character> {
}
