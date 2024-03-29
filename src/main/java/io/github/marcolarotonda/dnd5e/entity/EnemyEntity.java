package io.github.marcolarotonda.dnd5e.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "`enemy`")
public class EnemyEntity implements Combatant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name = "generic_enemy";
    @Basic
    @Column(name = "initiative_bonus")
    private Integer initiativeBonus = 0;
    @Basic
    @Column(name = "damage_taken")
    private Integer damageTaken = 0;
}
