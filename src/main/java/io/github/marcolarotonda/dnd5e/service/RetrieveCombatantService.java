package io.github.marcolarotonda.dnd5e.service;

import io.github.marcolarotonda.dnd5e.entity.Combatant;
import io.github.marcolarotonda.dnd5e.repository.CharacterRepository;
import io.github.marcolarotonda.dnd5e.repository.EnemyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetrieveCombatantService {

    private final CharacterRepository characterRepository;
    private final EnemyRepository enemyRepository;

    @Autowired
    public RetrieveCombatantService(CharacterRepository characterRepository,
                                    EnemyRepository enemyRepository) {
        this.characterRepository = characterRepository;
        this.enemyRepository = enemyRepository;
    }

    public List<Combatant> execute() {
        List<Combatant> combatants = characterRepository.findAllProjectedBy();
        List<Combatant> combatants1 = enemyRepository.findAllProjectedBy();
        combatants.addAll(combatants1);
        return combatants;
    }


}
