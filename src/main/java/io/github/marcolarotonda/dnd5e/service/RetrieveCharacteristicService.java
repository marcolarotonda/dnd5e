package io.github.marcolarotonda.dnd5e.service;

import io.github.marcolarotonda.dnd5e.entity.CharacterEntity;
import io.github.marcolarotonda.dnd5e.enumeration.CharacteristicEnum;
import io.github.marcolarotonda.dnd5e.enumeration.CharacteristicTypeEnum;
import io.github.marcolarotonda.dnd5e.util.CharacteristicUtils;
import org.springframework.stereotype.Service;


@Service
public class RetrieveCharacteristicService {


    public int getCharacteristic(CharacterEntity character, CharacteristicEnum characteristic, CharacteristicTypeEnum type) {
        return CharacteristicUtils.getCharacteristic(character, characteristic, type);
    }

    public int getModifier(CharacterEntity character, CharacteristicEnum characteristic) {
        return CharacteristicUtils.getModifier(character, characteristic, CharacteristicTypeEnum.TEMPORARY);
    }

    public int getModifier(CharacterEntity character, CharacteristicEnum characteristic, CharacteristicTypeEnum type) {
        return CharacteristicUtils.computeModifier(getCharacteristic(character, characteristic, type));
    }

    public int computeModifier(int value) {
        return CharacteristicUtils.computeModifier(value);
    }
}
