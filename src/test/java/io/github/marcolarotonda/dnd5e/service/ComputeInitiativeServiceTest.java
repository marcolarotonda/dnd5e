package io.github.marcolarotonda.dnd5e.service;

import io.github.marcolarotonda.dnd5e.ServiceTest;
import io.github.marcolarotonda.dnd5e.entity.CharacterEntity;
import io.github.marcolarotonda.dnd5e.entity.Combatant;
import io.github.marcolarotonda.dnd5e.enumeration.CharacteristicEnum;
import io.github.marcolarotonda.dnd5e.repository.CharacterRepository;
import io.github.marcolarotonda.dnd5e.repository.EnemyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.marcolarotonda.utlis.EntityInitializerUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComputeInitiativeServiceTest extends ServiceTest {

    @Mock
    private CharacterRepository characterRepository;
    @Mock
    private EnemyRepository enemyRepository;
    @Mock
    private RetrieveCharacteristicService retrieveCharacteristicService;
    @InjectMocks
    private ComputeInitiativeService computeInitiativeService;

    @Test
    void shouldComputeInitiativeForCharacters() {
        CharacterEntity character1 = initializeCharacter(5);
        CharacterEntity character2 = initializeCharacter(3);
        CharacterEntity character3 = initializeCharacter(7);
        CharacterEntity character4 = initializeCharacter();
        List<CharacterEntity> characters = new ArrayList<>(List.of(character1, character2, character3, character4));

        when(characterRepository.findAllByAliveTrue()).thenReturn(characters);
        when(retrieveCharacteristicService.getModifier(eq(character1), eq(CharacteristicEnum.DEXTERITY))).thenReturn(2);
        when(retrieveCharacteristicService.getModifier(eq(character2), eq(CharacteristicEnum.DEXTERITY))).thenReturn(0);
        when(retrieveCharacteristicService.getModifier(eq(character3), eq(CharacteristicEnum.DEXTERITY))).thenReturn(1);
        when(retrieveCharacteristicService.getModifier(eq(character4), eq(CharacteristicEnum.DEXTERITY))).thenReturn(4);

        Map<Combatant, Integer> actualMap = computeInitiativeService.computeInitiativeBonusForCharacters();
        Map<Combatant, Integer> expectedMap = new HashMap<>(Map.of(character1, 7,
                character2, 3,
                character3, 8,
                character4, 4));

        verify(characterRepository, times(1)).findAllByAliveTrue();
        verify(retrieveCharacteristicService, times(4)).getModifier(any(CharacterEntity.class), eq(CharacteristicEnum.DEXTERITY));
        assertThat(actualMap).isEqualTo(expectedMap);
    }


}
