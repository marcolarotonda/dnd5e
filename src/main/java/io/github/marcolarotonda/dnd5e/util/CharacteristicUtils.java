package io.github.marcolarotonda.dnd5e.util;

import io.github.marcolarotonda.dnd5e.entity.CharacterEntity;
import io.github.marcolarotonda.dnd5e.enumeration.CharacteristicEnum;
import io.github.marcolarotonda.dnd5e.enumeration.CharacteristicTypeEnum;

import static java.lang.String.format;

public class CharacteristicUtils {

    private CharacteristicUtils() {}

    public static int getCharacteristic(CharacterEntity character, CharacteristicEnum characteristic, CharacteristicTypeEnum type) {
        return character.getCharacteristicValueEntityList()
                .stream()
                .filter(cve -> cve.getCharacteristic().getName().equals(characteristic))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(format("Characteristic %s not defined for character %s", characteristic, character)))
                .getValue(type);
    }

    public static int getModifier(CharacterEntity character, CharacteristicEnum characteristic) {
        return getModifier(character, characteristic, CharacteristicTypeEnum.TEMPORARY);
    }

    public static int getModifier(CharacterEntity character, CharacteristicEnum characteristic, CharacteristicTypeEnum type) {
        return computeModifier(getCharacteristic(character, characteristic, type));
    }

    public static int computeModifier(int value) {
        return (int) Math.floor((value - 10) / 2.0);
    }
}
