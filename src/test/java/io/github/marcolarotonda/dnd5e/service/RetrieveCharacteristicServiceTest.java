package io.github.marcolarotonda.dnd5e.service;

import io.github.marcolarotonda.dnd5e.ServiceTest;
import io.github.marcolarotonda.dnd5e.entity.CharacterEntity;
import io.github.marcolarotonda.dnd5e.entity.CharacteristicValueEntity;
import io.github.marcolarotonda.dnd5e.enumeration.CharacteristicEnum;
import io.github.marcolarotonda.dnd5e.enumeration.CharacteristicTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static io.github.marcolarotonda.utlis.EntityInitializerUtils.initializeCharacter;
import static io.github.marcolarotonda.utlis.EntityInitializerUtils.initializeCharacteristicValues;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@Import(RetrieveCharacteristicService.class)
class RetrieveCharacteristicServiceTest extends ServiceTest {

    private CharacterEntity character;
    private List<CharacteristicValueEntity> characteristics;

    private final RetrieveCharacteristicService retrieveCharacteristicService;


    @Autowired
    RetrieveCharacteristicServiceTest(RetrieveCharacteristicService retrieveCharacteristicService) {
        this.retrieveCharacteristicService = retrieveCharacteristicService;
    }

    @BeforeEach
    void setup() {
        character = initializeCharacter();
        characteristics = initializeCharacteristicValues(character);
        character.setCharacteristicValueEntityList(characteristics);
    }

    @Test
    void shouldTemporaryStrengthBeEqualTo7() {
        int characteristic = retrieveCharacteristicService.getCharacteristic(character,
                CharacteristicEnum.STRENGTH,
                CharacteristicTypeEnum.TEMPORARY);
        assertThat(characteristic).isEqualTo(7);
    }

    @Test
    void shouldPermanentStrengthBeEqualTo8() {
        int characteristic = retrieveCharacteristicService.getCharacteristic(character,
                CharacteristicEnum.STRENGTH,
                CharacteristicTypeEnum.PERMANENT);
        assertThat(characteristic).isEqualTo(8);
    }

    @Test
    void shouldTemporaryDexterityBeEqualTo9() {
        int characteristic = retrieveCharacteristicService.getCharacteristic(character,
                CharacteristicEnum.DEXTERITY,
                CharacteristicTypeEnum.TEMPORARY);
        assertThat(characteristic).isEqualTo(9);
    }

    @Test
    void shouldPermanentDexterityBeEqualTo10() {
        int characteristic = retrieveCharacteristicService.getCharacteristic(character,
                CharacteristicEnum.DEXTERITY,
                CharacteristicTypeEnum.PERMANENT);
        assertThat(characteristic).isEqualTo(10);
    }

    @Test
    void shouldTemporaryConstitutionBeEqualTo11() {
        int characteristic = retrieveCharacteristicService.getCharacteristic(character,
                CharacteristicEnum.CONSTITUTION,
                CharacteristicTypeEnum.TEMPORARY);
        assertThat(characteristic).isEqualTo(11);
    }

    @Test
    void shouldPermanentConstitutionBeEqualTo12() {
        int characteristic = retrieveCharacteristicService.getCharacteristic(character,
                CharacteristicEnum.CONSTITUTION,
                CharacteristicTypeEnum.PERMANENT);
        assertThat(characteristic).isEqualTo(12);
    }

    @Test
    void shouldTemporaryIntelligenceBeEqualTo13() {
        int characteristic = retrieveCharacteristicService.getCharacteristic(character,
                CharacteristicEnum.INTELLIGENCE,
                CharacteristicTypeEnum.TEMPORARY);
        assertThat(characteristic).isEqualTo(13);
    }

    @Test
    void shouldPermanentIntelligenceBeEqualTo14() {
        int characteristic = retrieveCharacteristicService.getCharacteristic(character,
                CharacteristicEnum.INTELLIGENCE,
                CharacteristicTypeEnum.PERMANENT);
        assertThat(characteristic).isEqualTo(14);
    }

    @Test
    void shouldTemporaryWisdomBeEqualTo15() {
        int characteristic = retrieveCharacteristicService.getCharacteristic(character,
                CharacteristicEnum.WISDOM,
                CharacteristicTypeEnum.TEMPORARY);
        assertThat(characteristic).isEqualTo(15);
    }

    @Test
    void shouldPermanentWisdomBeEqualTo16() {
        int characteristic = retrieveCharacteristicService.getCharacteristic(character,
                CharacteristicEnum.WISDOM,
                CharacteristicTypeEnum.PERMANENT);
        assertThat(characteristic).isEqualTo(16);
    }

    @Test
    void shouldTemporaryCharismaBeEqualTo17() {
        int characteristic = retrieveCharacteristicService.getCharacteristic(character,
                CharacteristicEnum.CHARISMA,
                CharacteristicTypeEnum.TEMPORARY);
        assertThat(characteristic).isEqualTo(17);
    }

    @Test
    void shouldPermanentCharismaBeEqualTo19() {
        int characteristic = retrieveCharacteristicService.getCharacteristic(character,
                CharacteristicEnum.CHARISMA,
                CharacteristicTypeEnum.PERMANENT);
        assertThat(characteristic).isEqualTo(19);
    }

    @Test
    void shouldThrowIllegalStateExceptionWhenRetrievingAnUndefinedCharacteristic() {
        characteristics.removeIf(c -> c.getCharacteristic().getName().equals(CharacteristicEnum.DEXTERITY));
        character.setCharacteristicValueEntityList(characteristics);
        assertThatThrownBy(() -> retrieveCharacteristicService.getCharacteristic(character,
                CharacteristicEnum.DEXTERITY,
                CharacteristicTypeEnum.TEMPORARY))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Characteristic DEXTERITY not defined for character");
    }

    @Test
    void shouldReturnCorrectModifier() {
        assertThat(retrieveCharacteristicService.computeModifier(20)).isEqualTo(5);
        assertThat(retrieveCharacteristicService.computeModifier(19)).isEqualTo(4);
        assertThat(retrieveCharacteristicService.computeModifier(18)).isEqualTo(4);
        assertThat(retrieveCharacteristicService.computeModifier(17)).isEqualTo(3);
        assertThat(retrieveCharacteristicService.computeModifier(16)).isEqualTo(3);
        assertThat(retrieveCharacteristicService.computeModifier(15)).isEqualTo(2);
        assertThat(retrieveCharacteristicService.computeModifier(14)).isEqualTo(2);
        assertThat(retrieveCharacteristicService.computeModifier(13)).isEqualTo(1);
        assertThat(retrieveCharacteristicService.computeModifier(12)).isEqualTo(1);
        assertThat(retrieveCharacteristicService.computeModifier(11)).isZero();
        assertThat(retrieveCharacteristicService.computeModifier(10)).isZero();
        assertThat(retrieveCharacteristicService.computeModifier(9)).isEqualTo(-1);
        assertThat(retrieveCharacteristicService.computeModifier(8)).isEqualTo(-1);
        assertThat(retrieveCharacteristicService.computeModifier(7)).isEqualTo(-2);
        assertThat(retrieveCharacteristicService.computeModifier(6)).isEqualTo(-2);
    }

    @Test
    void shouldReturnTemporaryValue() {
        int defaultStrengthModifier = retrieveCharacteristicService.getModifier(character, CharacteristicEnum.STRENGTH);
        int strengthTempModifier = retrieveCharacteristicService.getModifier(character, CharacteristicEnum.STRENGTH, CharacteristicTypeEnum.TEMPORARY);
        assertThat(defaultStrengthModifier).isEqualTo(strengthTempModifier);
    }

    @Test
    void shouldTemporaryCharismaModifierBeEqualTo17() {
        int temporaryCharisma = retrieveCharacteristicService.getCharacteristic(character,
                CharacteristicEnum.CHARISMA,
                CharacteristicTypeEnum.TEMPORARY);
        assertThat(temporaryCharisma).isEqualTo(17);
    }

    @Test
    void shouldPermanentCharismaModifierBeEqualTo19() {
        int permanentCharisma = retrieveCharacteristicService.getCharacteristic(character,
                CharacteristicEnum.CHARISMA,
                CharacteristicTypeEnum.PERMANENT);
        assertThat(permanentCharisma).isEqualTo(19);
    }


}
