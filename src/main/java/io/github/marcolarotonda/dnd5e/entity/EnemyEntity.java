package io.github.marcolarotonda.dnd5e.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Optional;

@Entity
@Data
@Table(name = "`enemy`")
public class EnemyEntity implements Combatant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "`enemy_type_id`", nullable = false)
    private EnemyTypeEntity enemyType;
    @Basic
    @Column(name = "damage_taken")
    private Integer damageTaken = 0;
    @Basic
    @Column(name = "alive")
    private boolean alive = true;
    @Basic
    @Column(name="tag")
    private String tag;

    public Optional<String> getTag() {
        return Optional.ofNullable(tag);
    }

    public String getName() {
        return this.getEnemyType().getName();
    }


    public int getInitiativeModifier() {
        return this.getEnemyType().getInitiativeModifier();
    }

    public InitiativeRoller getInitiativeSource() {
        return this.getEnemyType();
    }
}
