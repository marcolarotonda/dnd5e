package io.github.marcolarotonda.dnd5e.service;

import io.github.marcolarotonda.dnd5e.entity.EnemyTypeEntity;
import io.github.marcolarotonda.dnd5e.repository.EnemyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnemyTypeService {

    private final EnemyTypeRepository enemyTypeRepository;

    @Autowired
    public EnemyTypeService(EnemyTypeRepository enemyTypeRepository) {
        this.enemyTypeRepository = enemyTypeRepository;
    }

    public List<EnemyTypeEntity> findAll() {
        return enemyTypeRepository.findAll();
    }

    public List<EnemyTypeEntity> findDistinctTypes() {
        return enemyTypeRepository.findDistinctTypes();
    }

    public Optional<EnemyTypeEntity> findByNameAndInitiativeModifier(String name, int initiativeModifier) {
        return enemyTypeRepository.findByNameAndInitiativeModifier(name, initiativeModifier);
    }

}
