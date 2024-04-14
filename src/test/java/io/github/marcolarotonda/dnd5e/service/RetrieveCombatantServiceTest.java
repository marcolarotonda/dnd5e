package io.github.marcolarotonda.dnd5e.service;

import io.github.marcolarotonda.dnd5e.ServiceTest;
import io.github.marcolarotonda.dnd5e.entity.*;
import io.github.marcolarotonda.dnd5e.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static io.github.marcolarotonda.utlis.EntityInitializerUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RetrieveCombatantServiceTest extends ServiceTest {

    @Mock
    private CharacterRepository characterRepository;
    @Mock
    private EnemyRepository enemyRepository;
    @InjectMocks
    private RetrieveCombatantService retrieveCombatantService;

    @Test
    void shouldReturnTwoCombatants() {
        CharacterEntity characterEntity = initializeCharacter();
        EnemyEntity enemyEntity = initializeEnemy();

        List<Combatant> characterEntityList = new ArrayList<>(List.of(characterEntity));
        List<Combatant> enemyEntityList = new ArrayList<>(List.of(enemyEntity));

        List<Combatant> combatantList = new ArrayList<>();
        combatantList.addAll(characterEntityList);
        combatantList.addAll(enemyEntityList);

        when(characterRepository.findAllProjectedByAliveTrue()).thenReturn(characterEntityList);
        when(enemyRepository.findAllProjectedByAliveTrue()).thenReturn(enemyEntityList);

        List<Combatant> execute = retrieveCombatantService.execute();

        verify(characterRepository, times(1)).findAllProjectedByAliveTrue();
        verify(enemyRepository, times(1)).findAllProjectedByAliveTrue();
        assertThat(execute).isEqualTo(combatantList);

    }

}
