package io.github.marcolarotonda.dnd5e.entity;

import io.github.marcolarotonda.dnd5e.enumeration.CharacteristicEnum;
import io.github.marcolarotonda.dnd5e.enumeration.CharacteristicTypeEnum;
import io.github.marcolarotonda.dnd5e.util.CharacteristicUtils;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;
import java.util.Optional;


@Entity
@Setter
@Getter
@Table(name = "`character`")
@ToString
public class CharacterEntity implements InitiativeRoller, Combatant{
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
    @Basic
    @Column(name = "tag")
    private String tag;

    @ToString.Exclude
    @OneToMany(mappedBy = "character", fetch = FetchType.EAGER)
    private List<CharacteristicValueEntity> characteristicValueEntityList;

    public Optional<String> getTag() {
        return Optional.ofNullable(tag);
    }

    public int getInitiativeModifier() {
        return CharacteristicUtils.getModifier(this, CharacteristicEnum.DEXTERITY, CharacteristicTypeEnum.TEMPORARY) + this.getInitiativeBonus();
    }

    public InitiativeRoller getInitiativeSource() {
        return this;
    }


}
