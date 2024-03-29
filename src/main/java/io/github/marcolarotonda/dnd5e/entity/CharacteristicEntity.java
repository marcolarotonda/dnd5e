package io.github.marcolarotonda.dnd5e.entity;

import io.github.marcolarotonda.dnd5e.converter.ObjectToCharacteristicConverter;
import io.github.marcolarotonda.dnd5e.enumeration.CharacteristicEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "characteristic")
public class CharacteristicEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name", nullable = false)
    @Convert(converter = ObjectToCharacteristicConverter.class)
    private CharacteristicEnum name;
}
