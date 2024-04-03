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
    @ManyToOne
    @JoinColumn(name = "`enemy_type_id`", nullable = false)
    private EnemyTypeEntity enemyType;
    @Basic
    @Column(name = "damage_taken")
    private Integer damageTaken = 0;


    public String getName() {
        return this.getEnemyType().getName();
    }

    public Integer getInitiativeBonus() {
        return this.getEnemyType().getInitiativeBonus();
    }
}
