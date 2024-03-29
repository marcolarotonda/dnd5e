package io.github.marcolarotonda.dnd5e.enumeration;

import io.github.marcolarotonda.dnd5e.UnitTest;
import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;

class CharacteristicEnumTest extends UnitTest {
    @Test
    void shouldBeEqual() {
        BiConsumer<CharacteristicEnum, String> equal = (en, s) -> assertThat(en.getName()).isEqualTo(s);
        equal.accept(CharacteristicEnum.STRENGTH, "Strength");
        equal.accept(CharacteristicEnum.DEXTERITY, "Dexterity");
        equal.accept(CharacteristicEnum.CONSTITUTION, "Constitution");
        equal.accept(CharacteristicEnum.INTELLIGENCE, "Intelligence");
        equal.accept(CharacteristicEnum.WISDOM, "Wisdom");
        equal.accept(CharacteristicEnum.CHARISMA, "Charisma");
    }

    @Test
    void shouldNotBeEqual() {
        BiConsumer<CharacteristicEnum, String> diff = (en, s) -> assertThat(en.getName()).isNotEqualTo(s);
        diff.accept(CharacteristicEnum.STRENGTH, "strength");
        diff.accept(CharacteristicEnum.STRENGTH, "Strengt");
        diff.accept(CharacteristicEnum.STRENGTH, " Strength");
        diff.accept(CharacteristicEnum.STRENGTH, "Strength ");
        diff.accept(CharacteristicEnum.STRENGTH, "Strength\n");
    }


}
