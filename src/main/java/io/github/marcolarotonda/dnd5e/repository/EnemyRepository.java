package io.github.marcolarotonda.dnd5e.repository;

import io.github.marcolarotonda.dnd5e.entity.EnemyEntity;
import io.github.marcolarotonda.dnd5e.entity.Combatant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnemyRepository extends JpaRepository<EnemyEntity, Integer> {

    @Query
    List<Combatant> findAllProjectedBy();

    @Modifying
    @Query(value = "truncate table `enemy`", nativeQuery = true)
    void truncate();

    @Modifying
    @Query(value = "delete from `enemy` where alive = 0", nativeQuery = true)
    void deleteDeadEnemies();


}
