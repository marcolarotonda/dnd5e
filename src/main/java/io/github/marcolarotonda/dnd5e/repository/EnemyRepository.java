package io.github.marcolarotonda.dnd5e.repository;

import io.github.marcolarotonda.dnd5e.entity.EnemyEntity;
import io.github.marcolarotonda.dnd5e.entity.Combatant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnemyRepository extends JpaRepository<EnemyEntity, Integer> {

    @Query
    List<EnemyEntity> findAllByAliveTrue();
    @Query
    List<Combatant> findAllProjectedByAliveTrue();


}
