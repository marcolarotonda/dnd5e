package io.github.marcolarotonda.dnd5e.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Setter
@Getter
@Table(name = "`character`")
public class CharacterEntity implements Combatant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerEntity player;
    @Basic
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private RaceEntity race;
    @Basic
    @Column(name = "competence_bonus", nullable = false)
    private Integer competenceBonus;
    @Basic
    @Column(name = "speed", nullable = false)
    private Integer speed;
    @Basic
    @Column(name = "initiative_bonus", nullable = false)
    private Integer initiativeBonus;
    @Basic
    @Column(name = "passive_perception", nullable = false)
    private Integer passivePerception;
    @Basic
    @Column(name = "class_armor", nullable = false)
    private Integer classArmor;
    @Basic
    @Column(name = "alive", nullable = false)
    private Boolean alive;

    @OneToMany(mappedBy = "character")
    private List<CharacteristicValueEntity> characteristicValueEntityList;


}
