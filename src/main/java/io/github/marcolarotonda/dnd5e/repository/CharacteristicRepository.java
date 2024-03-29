package io.github.marcolarotonda.dnd5e.repository;

import io.github.marcolarotonda.dnd5e.entity.CharacteristicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicRepository extends JpaRepository<CharacteristicEntity, Integer> {
}
