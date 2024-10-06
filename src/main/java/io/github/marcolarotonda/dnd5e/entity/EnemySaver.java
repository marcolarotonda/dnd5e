package io.github.marcolarotonda.dnd5e.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EnemySaver {
    private String name;
    private String tag;
    private int initiativeModifier;
    private int quantity;
}
