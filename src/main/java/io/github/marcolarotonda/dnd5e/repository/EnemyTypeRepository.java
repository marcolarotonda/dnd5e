package io.github.marcolarotonda.dnd5e.repository;

import io.github.marcolarotonda.dnd5e.entity.EnemyTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnemyTypeRepository extends JpaRepository<EnemyTypeEntity, Integer> {

    @Query(value = """
            select distinct *
            from `enemy_type`
            where exists (select *
                            from `enemy`
                            where `enemy`.enemy_type_id = `enemy_type`.id)
             """, nativeQuery = true)
    List<EnemyTypeEntity> findDistinctTypes();
}
