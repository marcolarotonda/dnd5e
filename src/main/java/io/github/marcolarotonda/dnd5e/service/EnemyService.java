package io.github.marcolarotonda.dnd5e.service;

import io.github.marcolarotonda.dnd5e.entity.Combatant;
import io.github.marcolarotonda.dnd5e.entity.EnemyEntity;
import io.github.marcolarotonda.dnd5e.entity.EnemySaver;
import io.github.marcolarotonda.dnd5e.entity.EnemyTypeEntity;
import io.github.marcolarotonda.dnd5e.repository.EnemyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnemyService {

    private final EnemyRepository enemyRepository;
    private final EnemyTypeService enemyTypeService;

    @Autowired
    public EnemyService(EnemyRepository enemyRepository,
                        EnemyTypeService enemyTypeService) {
        this.enemyRepository = enemyRepository;
        this.enemyTypeService = enemyTypeService;
    }

    public List<EnemyEntity> findAllByAliveTrue() {
        return enemyRepository.findAllByAliveTrue();
    }

    public List<Combatant> findAllProjectedByAliveTrue() {
        return enemyRepository.findAllProjectedByAliveTrue();
    }

    @Transactional
    public void saveEnemies(EnemySaver enemySaver) {


        String name = enemySaver.getName();
        int initiativeModifier = enemySaver.getInitiativeModifier();
        Optional<EnemyTypeEntity> byNameAndInitiativeModifier = enemyTypeService.findByNameAndInitiativeModifier(name, initiativeModifier);

        EnemyTypeEntity enemyType;
        if (byNameAndInitiativeModifier.isPresent()) {
            enemyType = byNameAndInitiativeModifier.get();
        } else {
            enemyType = new EnemyTypeEntity();
            enemyType.setName(name);
            enemyType.setInitiativeModifier(initiativeModifier);
        }

        List<EnemyEntity> enemies = new ArrayList<>();
        int quantity = enemySaver.getQuantity();
        for (int i = 0; i < quantity; i++) {
            EnemyEntity enemy = new EnemyEntity();
            enemy.setEnemyType(enemyType);
            enemies.add(enemy);
        }

        enemyRepository.saveAll(enemies);
    }




}
