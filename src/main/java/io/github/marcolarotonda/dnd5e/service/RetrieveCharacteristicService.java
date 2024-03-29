package io.github.marcolarotonda.dnd5e.service;

import io.github.marcolarotonda.dnd5e.entity.CharacterEntity;
import io.github.marcolarotonda.dnd5e.enumeration.CharacteristicEnum;
import io.github.marcolarotonda.dnd5e.enumeration.CharacteristicTypeEnum;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class RetrieveCharacteristicService {


    public int getCharacteristic(CharacterEntity character, CharacteristicEnum characteristic, CharacteristicTypeEnum type) {
        return character.getCharacteristicValueEntityList()
                .stream()
                .filter(cve -> cve.getCharacteristic().getName().equals(characteristic))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(format("Characteristic %s not defined for character %s", characteristic, character)))
                .getValue(type);
    }

    public int getModifier(CharacterEntity character, CharacteristicEnum characteristic) {
        return getModifier(character, characteristic, CharacteristicTypeEnum.TEMPORARY);
    }

    public int getModifier(CharacterEntity character, CharacteristicEnum characteristic, CharacteristicTypeEnum type) {
        return computeModifier(getCharacteristic(character, characteristic, type));
    }

    public int computeModifier(int value) {
        return (int) Math.floor((value - 10) / 2.0);
    }
}
