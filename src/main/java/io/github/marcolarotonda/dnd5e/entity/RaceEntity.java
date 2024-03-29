package io.github.marcolarotonda.dnd5e.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "race")
public class RaceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name", nullable = false)
    private String name;
    @Basic
    @Column(name = "speed", nullable = false)
    private Integer speed;
    @Basic
    @Column(name = "size", nullable = false)
    private String size;
}
