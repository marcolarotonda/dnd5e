package io.github.marcolarotonda.dnd5e.repository;

import io.github.marcolarotonda.dnd5e.RepositoryTest;
import io.github.marcolarotonda.dnd5e.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static io.github.marcolarotonda.utlis.EntityInitializerUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@Sql(value = {"/sql/tb/create_tb_player.sql",
        "/sql/tb/create_tb_race.sql",
        "/sql/tb/create_tb_character.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/tb/drop_tb_character.sql",
        "/sql/tb/drop_tb_player.sql",
        "/sql/tb/drop_tb_race.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CharacterRepositoryTest extends RepositoryTest {

    private final CharacterRepository characterRepository;
    private final PlayerRepository playerRepository;
    private final RaceRepository raceRepository;
    private final CharacteristicRepository characteristicRepository;
    private final CharacteristicValueRepository characteristicValueRepository;


    @Autowired
    CharacterRepositoryTest(CharacterRepository characterRepository,
                            PlayerRepository playerRepository,
                            RaceRepository raceRepository,
                            CharacteristicRepository characteristicRepository,
                            CharacteristicValueRepository characteristicValueRepository) {
        this.characterRepository = characterRepository;
        this.playerRepository = playerRepository;
        this.raceRepository = raceRepository;
        this.characteristicRepository = characteristicRepository;
        this.characteristicValueRepository = characteristicValueRepository;

    }

    @Test
    void shouldReturnOneCombatant() {
        CharacterEntity characterEntity = initializeCharacter();
        raceRepository.save(characterEntity.getRace());
        playerRepository.save(characterEntity.getPlayer());
        characterRepository.save(characterEntity);

        List<Combatant> allProjectedBy = characterRepository.findAllProjectedBy();
        assertThat(allProjectedBy).hasSize(1);
    }

    @Test
    void shouldReturnOneCharacter() {
        CharacterEntity characterEntity = initializeCharacter();
        raceRepository.save(characterEntity.getRace());
        playerRepository.save(characterEntity.getPlayer());
        characterRepository.save(characterEntity);

        List<CharacterEntity> all = characterRepository.findAll();
        assertThat(all).hasSize(1);
    }

    @Test
    void shouldThrowDataIntegrityViolationWhenPersistingWithoutPlayer() {
        CharacterEntity characterEntity = initializeCharacter(CharacterField.PLAYER);
        raceRepository.save(characterEntity.getRace());

        assertThatThrownBy(() -> characterRepository.save(characterEntity)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void shouldThrowDataIntegrityViolationWhenPersistingWithoutRace() {
        CharacterEntity characterEntity = initializeCharacter(CharacterField.RACE);
        playerRepository.save(characterEntity.getPlayer());

        assertThatThrownBy(() -> characterRepository.save(characterEntity)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void shouldThrowDataIntegrityViolationWhenPersistingWithoutPassivePerception() {
        CharacterEntity characterEntity = initializeCharacter(CharacterField.PASSIVE_PERCEPTION);
        raceRepository.save(characterEntity.getRace());
        playerRepository.save(characterEntity.getPlayer());

        assertThatThrownBy(() -> characterRepository.save(characterEntity)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void shouldThrowDataIntegrityViolationWhenPersistingWithoutAlive() {
        CharacterEntity characterEntity = initializeCharacter(CharacterField.ALIVE);
        raceRepository.save(characterEntity.getRace());
        playerRepository.save(characterEntity.getPlayer());

        assertThatThrownBy(() -> characterRepository.save(characterEntity)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void shouldThrowDataIntegrityViolationWhenPersistingWithoutInitiativeBonus() {
        CharacterEntity characterEntity = initializeCharacter(CharacterField.INITIATIVE_BONUS);
        raceRepository.save(characterEntity.getRace());
        playerRepository.save(characterEntity.getPlayer());

        assertThatThrownBy(() -> characterRepository.save(characterEntity)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void shouldThrowDataIntegrityViolationWhenPersistingWithoutCompetenceBonus() {
        CharacterEntity characterEntity = initializeCharacter(CharacterField.COMPETENCE_BONUS);
        raceRepository.save(characterEntity.getRace());
        playerRepository.save(characterEntity.getPlayer());

        assertThatThrownBy(() -> characterRepository.save(characterEntity)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void shouldThrowDataIntegrityViolationWhenPersistingWithoutClassArmor() {
        CharacterEntity characterEntity = initializeCharacter(CharacterField.CLASS_ARMOR);
        raceRepository.save(characterEntity.getRace());
        playerRepository.save(characterEntity.getPlayer());

        assertThatThrownBy(() -> characterRepository.save(characterEntity)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void shouldThrowDataIntegrityViolationWhenPersistingWithoutSpeed() {
        CharacterEntity characterEntity = initializeCharacter(CharacterField.SPEED);
        raceRepository.save(characterEntity.getRace());
        playerRepository.save(characterEntity.getPlayer());

        assertThatThrownBy(() -> characterRepository.save(characterEntity)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void shouldThrowDataIntegrityViolationWhenPersistingWithoutName() {
        CharacterEntity characterEntity = initializeCharacter(CharacterField.NAME);
        raceRepository.save(characterEntity.getRace());
        playerRepository.save(characterEntity.getPlayer());

        assertThatThrownBy(() -> characterRepository.save(characterEntity)).isInstanceOf(DataIntegrityViolationException.class);
    }

}
