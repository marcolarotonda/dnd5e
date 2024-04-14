package io.github.marcolarotonda.dnd5e.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "`enemy_type`")
public class EnemyTypeEntity implements InitiativeRoller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "`id`")
    private int id;
    @Basic
    @Column(name = "`name`")
    private String name = "generic_enemy";
    @Basic
    @Column(name = "`initiative_modifier`")
    private int initiativeModifier = 0;

    public InitiativeRoller getInitiativeSource() {
        return this;
    }

}
