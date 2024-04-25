package io.github.marcolarotonda.dnd5e.entity;

import java.util.Optional;

public interface Combatant {

    String getName();

    Optional<String> getTag();

    InitiativeRoller getInitiativeSource();

}
