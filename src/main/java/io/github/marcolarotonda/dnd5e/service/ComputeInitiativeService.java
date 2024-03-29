package io.github.marcolarotonda.dnd5e.service;

import io.github.marcolarotonda.dnd5e.entity.Combatant;
import io.github.marcolarotonda.dnd5e.entity.EnemyEntity;
import io.github.marcolarotonda.dnd5e.enumeration.CharacteristicEnum;
import io.github.marcolarotonda.dnd5e.repository.CharacterRepository;
import io.github.marcolarotonda.dnd5e.entity.CharacterEntity;
import io.github.marcolarotonda.dnd5e.repository.EnemyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

@Service
public class ComputeInitiativeService {
    private final CharacterRepository characterRepository;
    private final EnemyRepository enemyRepository;
    private final RetrieveCharacteristicService retrieveCharacteristicService;


    @Autowired
    public ComputeInitiativeService(CharacterRepository characterRepository,
                                    RetrieveCharacteristicService retrieveCharacteristicService,
                                    EnemyRepository enemyRepository) {
        this.characterRepository = characterRepository;
        this.enemyRepository = enemyRepository;
        this.retrieveCharacteristicService = retrieveCharacteristicService;
    }

    public Map<Combatant, Integer> computeInitiativeBonus() {
        Map<Combatant, Integer> map = computeInitiativeBonusForCharacter();
        Map<Combatant, Integer> map1 = computeInitiativeBonusForEnemy();
        map.putAll(map1);
        return map;
    }

    public Map<Combatant, Integer> computeInitiativeBonusForCharacter() {

        List<CharacterEntity> characters = characterRepository.findAllByAliveTrue();

        ToIntFunction<CharacterEntity> getInitiativeForCharacter = character -> {
            int initiativeBonus = character.getInitiativeBonus();
            int dexModifier = retrieveCharacteristicService.getModifier(character, CharacteristicEnum.DEXTERITY);
            return initiativeBonus + dexModifier;
        };

        return characters.stream()
                .collect(Collectors.toMap(character -> character,
                        getInitiativeForCharacter::applyAsInt));
    }

    public Map<Combatant, Integer> computeInitiativeBonusForEnemy() {

        List<EnemyEntity> enemies = enemyRepository.findAll();
        return enemies.stream()
                .collect(Collectors.toMap(enemy -> enemy,
                        EnemyEntity::getInitiativeBonus));

    }

}
