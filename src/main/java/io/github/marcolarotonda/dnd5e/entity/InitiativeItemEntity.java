package io.github.marcolarotonda.dnd5e.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
@Table(name = "`initiative_item`")
public class InitiativeItemEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name", nullable = false)
    private final String name;
    @Basic
    @Column(name = "initiative_value", nullable = false)
    private int initiativeValue;
    @Basic
    @Column(name = "tag", nullable = false)
    private String tag;
    @Basic
    @Column(name = "damage_taken", nullable = false)
    private int damageTaken;
}