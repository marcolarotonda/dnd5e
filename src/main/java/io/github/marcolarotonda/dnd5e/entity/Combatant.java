package io.github.marcolarotonda.dnd5e.entity;

public interface Combatant {

    String getName();

    int getInitiativeModifier();

    InitiativeRoller getInitiativeSource();

}
