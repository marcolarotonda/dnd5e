package io.github.marcolarotonda.dnd5e.service;

import io.github.marcolarotonda.dicerollerutil.model.RollOption;
import io.github.marcolarotonda.dnd5e.entity.*;
import io.github.marcolarotonda.dnd5e.model.InitiativeItem;
import io.github.marcolarotonda.dnd5e.repository.CharacterRepository;
import io.github.marcolarotonda.dnd5e.repository.EnemyRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ComputeInitiativeService {
    private final CharacterRepository characterRepository;
    private final EnemyRepository enemyRepository;
    private final DiceService diceService;

    private List<Combatant> combatants;
    private List<InitiativeRoller> rollers;
    private Map<InitiativeRoller, Integer> initiativeModifiers;
    private Map<InitiativeRoller, Integer> initiativeRolls;
    private Map<Combatant, Integer> initiativeRollsExpanded;
    private List<Map.Entry<Combatant, Integer>> initiativeAsMapEntries;

    @Getter
    List<InitiativeItem> initiative;


    @Autowired
    public ComputeInitiativeService(CharacterRepository characterRepository,
                                    EnemyRepository enemyRepository,
                                    DiceService diceService) {
        this.characterRepository = characterRepository;
        this.enemyRepository = enemyRepository;
        this.diceService = diceService;
    }

    /**
     * Calls all the methods needed to calculate initiative
     */
    public void execute() {
        setup();
        initiativeModifiers = computeInitiativeModifier();
        initiativeRolls = rollInitiative();
        initiativeRollsExpanded = expandEnemies();
        initiativeAsMapEntries = assignInitiativeOrder();
        initiative = getCalculatedInitiative();

    }

    /**
     * Create all the objects needed to calculate initiative
     */
    private void setup() {
        List<CharacterEntity> characters = characterRepository.findAllByAliveTrue();
        List<EnemyEntity> enemies = enemyRepository.findAllByAliveTrue();
        combatants = new ArrayList<>();
        combatants.addAll(characters);
        combatants.addAll(enemies);
        Set<EnemyTypeEntity> enemyTypes = enemies.stream()
                .map(EnemyEntity::getEnemyType)
                .collect(Collectors.toSet());
        rollers = new ArrayList<>();
        rollers.addAll(characters);
        rollers.addAll(enemyTypes);


    }

    /**
     * Create a list that represents the initiative order. The first element of the list is the Combatant with the
     * highest initiative. The order is not relevant for Combatant with identical initiative. This method is called after
     * expandEnemies()
     * @return List&lt;Map.Entry&lt;Combatant, Integer&gt;&gt;
     */
    private List<Map.Entry<Combatant, Integer>> assignInitiativeOrder() {
        List<Map.Entry<Combatant, Integer>> initiativeOrderLocal = new ArrayList<>(initiativeRollsExpanded.entrySet());
        initiativeOrderLocal.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return initiativeOrderLocal;
    }

    /**
     * Assign the initiative roll to each Character and Enemy.
     * This method is called after rollInitiative()
     * @return Map&lt;Combatant, Integer&gt;
     */
    private Map<Combatant, Integer> expandEnemies() {
        Map<Combatant, InitiativeRoller> mapCombatantToInitiativeRoller = combatants.stream()
                .collect(Collectors.toMap(combatant -> combatant,
                        Combatant::getInitiativeSource));
        return combatants.stream()
                .collect(Collectors.toMap(combatant -> combatant,
                        combatant -> initiativeRolls.get(mapCombatantToInitiativeRoller.get(combatant))));
    }

    /**
     * Perform the initiative roll for each Character and EnemyType.
     * This method is called after computeInitiativeModifier()
     * @return Map&lt;Combatant, Integer&gt;
     */
    private Map<InitiativeRoller, Integer> rollInitiative() {
        return initiativeModifiers.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> diceService.rollGetTotal(RollOption.builder().modifier(entry.getValue()).build())));
    }

    /**
     * Assign the initiative modifier to each Character and EnemyType
     * @return Map&lt;Combatant, Integer&gt;
     */
    private Map<InitiativeRoller, Integer> computeInitiativeModifier() {
        return rollers.stream()
                .collect(Collectors.toMap(combatant -> combatant,
                        InitiativeRoller::getInitiativeModifier));
    }

    /**
     * Given a list List&lt;Map.Entry&lt;Combatant, Integer&gt;&gt;, each element of this list is transformed into an InitiativeItem
     * object.
     *
     * @return: Initiative order represented as List&lt;InitiativeItem&gt;
     */
    private List<InitiativeItem> getCalculatedInitiative() {

        Function<Integer, InitiativeItem> getInitiativeItem = i -> {
            Map.Entry<Combatant, Integer> entry = initiativeAsMapEntries.get(i);
            return InitiativeItem.builder()
                    .name(entry.getKey().getName())
                    .tag("")
                    .damageTaken(0)
                    .initiativeValue(entry.getValue())
                    .build();
        };

        initiative = IntStream.range(0, initiativeAsMapEntries.size())
                .boxed()
                .map(getInitiativeItem)
                .collect(Collectors.toList());

        return initiative;
    }


}
