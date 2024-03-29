package io.github.marcolarotonda.dnd5e.entity;

import io.github.marcolarotonda.dnd5e.enumeration.CharacteristicTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode
@Entity
@Table(name = "characteristic_value")
public class CharacteristicValueEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne()
    @JoinColumn(name = "character_id", nullable = false)
    private CharacterEntity character;
    @ManyToOne
    @JoinColumn(name = "characteristic_id", nullable = false)
    private CharacteristicEntity characteristic;
    @Basic
    @Column(name = "permanent_value", nullable = false)
    private int permanentValue;
    @Basic
    @Column(name = "temporary_value", nullable = false)
    private int temporaryValue;

    public int getValue(CharacteristicTypeEnum type) {
        return type == CharacteristicTypeEnum.PERMANENT ? getPermanentValue() : getTemporaryValue();
    }

}
