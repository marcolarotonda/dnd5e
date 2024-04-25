package io.github.marcolarotonda.dnd5e.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InitiativeItem {

    private final String name;
    private int initiativeValue;
    private String tag;
    private int damageTaken;
}