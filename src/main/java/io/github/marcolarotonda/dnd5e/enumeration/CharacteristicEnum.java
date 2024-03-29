package io.github.marcolarotonda.dnd5e.enumeration;

import lombok.Getter;

@Getter
public enum CharacteristicEnum {
    STRENGTH("Strength"),
    DEXTERITY("Dexterity"),
    CONSTITUTION("Constitution"),
    INTELLIGENCE("Intelligence"),
    WISDOM("Wisdom"),
    CHARISMA("Charisma");

    private final String name;

    CharacteristicEnum(String name) {
        this.name = name;
    }
}
