package io.github.marcolarotonda.dnd5e.converter;

import io.github.marcolarotonda.dnd5e.UnitTest;
import io.github.marcolarotonda.dnd5e.enumeration.CharacteristicEnum;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

class ObjectToCharacteristicConverterTest extends UnitTest {

    private final ObjectToCharacteristicConverter converter;


    ObjectToCharacteristicConverterTest() {
        this.converter = new ObjectToCharacteristicConverter();
    }

    @Test
    void shouldBeEqualWhenConvertingToEntityAttribute() {
        Consumer<CharacteristicEnum> equal = ce -> assertThat(converter.convertToEntityAttribute(ce.getName())).isEqualTo(ce);

        equal.accept(CharacteristicEnum.STRENGTH);
        equal.accept(CharacteristicEnum.DEXTERITY);
        equal.accept(CharacteristicEnum.CONSTITUTION);
        equal.accept(CharacteristicEnum.INTELLIGENCE);
        equal.accept(CharacteristicEnum.WISDOM);
        equal.accept(CharacteristicEnum.CHARISMA);
    }

    @Test
    void shouldBeEqualWhenConvertingToDatabaseColumn() {
        Consumer<String> equal = s -> assertThat(converter.convertToDatabaseColumn(CharacteristicEnum.valueOf(s.toUpperCase()))).isEqualTo(s.toUpperCase());

        equal.accept("Strength");
        equal.accept("Dexterity");
        equal.accept("Constitution");
        equal.accept("Intelligence");
        equal.accept("Wisdom");
        equal.accept("Charisma");
    }

}
