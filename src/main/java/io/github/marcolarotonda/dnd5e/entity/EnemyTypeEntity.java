package io.github.marcolarotonda.dnd5e.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "`enemy_type`")
public class EnemyTypeEntity implements Combatant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "`id`")
    private int id;
    @Basic
    @Column(name = "`name`")
    private String name = "generic_enemy";
    @Basic
    @Column(name = "`initiative_bonus`")
    private Integer initiativeBonus = 0;

    public int getInitiativeModifier() {
        return this.initiativeBonus;
    }

    public Combatant getInitiativeSource() {
        return this;
    }

}
