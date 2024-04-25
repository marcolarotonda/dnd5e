package io.github.marcolarotonda.dnd5e.repository;

import io.github.marcolarotonda.dnd5e.RepositoryTest;
import io.github.marcolarotonda.dnd5e.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

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


    @Autowired
    CharacterRepositoryTest(CharacterRepository characterRepository,
                            PlayerRepository playerRepository,
                            RaceRepository raceRepository) {
        this.characterRepository = characterRepository;
        this.playerRepository = playerRepository;
        this.raceRepository = raceRepository;
    }

    @Test
    void shouldReturnOneCombatant() {
        CharacterEntity characterEntity = initializeCharacter();
        raceRepository.save(characterEntity.getRace());
        playerRepository.save(characterEntity.getPlayer());
        characterRepository.save(characterEntity);

        List<Combatant> allProjectedBy = characterRepository.findAllProjectedByAliveTrue();
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
    void shouldNotReturnDeadCharacter() {
        RaceEntity race = initializeRace();

        PlayerEntity player1 = new PlayerEntity();
        player1.setName("pl1");

        CharacterEntity character1 = new CharacterEntity();
        character1.setRace(race);
        character1.setPlayer(player1);
        character1.setSpeed(9);
        character1.setClassArmor(12);
        character1.setPassivePerception(10);
        character1.setInitiativeBonus(0);
        character1.setCompetenceBonus(2);
        character1.setName("ch1");
        character1.setAlive(true);

        PlayerEntity player2 = new PlayerEntity();
        player2.setName("pl2");

        CharacterEntity character2 = new CharacterEntity();
        character2.setRace(race);
        character2.setPlayer(player2);
        character2.setSpeed(9);
        character2.setClassArmor(12);
        character2.setPassivePerception(10);
        character2.setInitiativeBonus(0);
        character2.setCompetenceBonus(2);
        character2.setName("ch2");
        character2.setAlive(false);


        raceRepository.save(race);
        playerRepository.saveAll(List.of(player1, player2));
        characterRepository.saveAll(List.of(character1, character2));

        List<CharacterEntity> allByAliveTrue = characterRepository.findAllByAliveTrue();

        assertThat(allByAliveTrue).hasSize(1);
    }

    @Test
    void shouldNotReturnDeadCombatant() {
        RaceEntity race = initializeRace();

        PlayerEntity player1 = new PlayerEntity();
        player1.setName("pl1");

        CharacterEntity character1 = new CharacterEntity();
        character1.setRace(race);
        character1.setPlayer(player1);
        character1.setSpeed(9);
        character1.setClassArmor(12);
        character1.setPassivePerception(10);
        character1.setInitiativeBonus(0);
        character1.setCompetenceBonus(2);
        character1.setName("ch1");
        character1.setAlive(true);

        PlayerEntity player2 = new PlayerEntity();
        player2.setName("pl2");

        CharacterEntity character2 = new CharacterEntity();
        character2.setRace(race);
        character2.setPlayer(player2);
        character2.setSpeed(9);
        character2.setClassArmor(12);
        character2.setPassivePerception(10);
        character2.setInitiativeBonus(0);
        character2.setCompetenceBonus(2);
        character2.setName("ch2");
        character2.setAlive(false);


        raceRepository.save(race);
        playerRepository.saveAll(List.of(player1, player2));
        characterRepository.saveAll(List.of(character1, character2));

        List<Combatant> allByAliveTrue = characterRepository.findAllProjectedByAliveTrue();

        assertThat(allByAliveTrue).hasSize(1);
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

    @Test
    void shouldReturnNullTag() {
        CharacterEntity characterEntity = initializeCharacter();
        Optional<String> tag = characterEntity.getTag();
        assertThat(tag).isEmpty();
    }

    @Test
    void shouldSaveCharacterWithoutTag() {
        CharacterEntity character = initializeCharacter();
        raceRepository.save(character.getRace());
        playerRepository.save(character.getPlayer());
        characterRepository.save(character);

        CharacterEntity returned = characterRepository.findById(character.getId()).get();
        assertThat(returned.getTag()).isEmpty();
    }



}
