package io.github.marcolarotonda.dnd5e.converter;

import io.github.marcolarotonda.dnd5e.enumeration.CharacteristicEnum;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter
public class ObjectToCharacteristicConverter implements AttributeConverter<CharacteristicEnum, String> {

    @Override
    public String convertToDatabaseColumn(CharacteristicEnum value) {
        return Optional.ofNullable(value)
                .map(CharacteristicEnum::name)
                .orElse(null);
    }

    @Override
    public CharacteristicEnum convertToEntityAttribute(String value) {
        return Optional.ofNullable(value)
                .map(Object::toString)
                .map(String::toUpperCase)
                .map(CharacteristicEnum::valueOf)
                .orElse(null);
    }
}
