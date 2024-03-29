package io.github.marcolarotonda.dnd5e.repository;


import io.github.marcolarotonda.dnd5e.RepositoryTest;
import io.github.marcolarotonda.dnd5e.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static io.github.marcolarotonda.utlis.EntityInitializerUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

@Sql(value = {"/sql/tb/create_tb_player.sql",
        "/sql/tb/create_tb_race.sql",
        "/sql/tb/create_tb_character.sql",
        "/sql/tb/create_tb_characteristic.sql",
        "/sql/tb/create_tb_characteristic_value.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/tb/drop_tb_characteristic_value.sql",
        "/sql/tb/drop_tb_character.sql",
        "/sql/tb/drop_tb_player.sql",
        "/sql/tb/drop_tb_race.sql",
        "/sql/tb/drop_tb_characteristic.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CharacteristicValueRepositoryTest extends RepositoryTest {

    private final CharacteristicValueRepository characteristicValueRepository;
    private final PlayerRepository playerRepository;
    private final RaceRepository raceRepository;
    private final CharacteristicRepository characteristicRepository;
    private final CharacterRepository characterRepository;

    @Autowired
    CharacteristicValueRepositoryTest(CharacteristicValueRepository characteristicValueRepository,
                                      CharacteristicRepository characteristicRepository,
                                      CharacterRepository characterRepository,
                                      PlayerRepository playerRepository,
                                      RaceRepository raceRepository) {
        this.characteristicValueRepository = characteristicValueRepository;
        this.characteristicRepository = characteristicRepository;
        this.characterRepository = characterRepository;
        this.playerRepository = playerRepository;
        this.raceRepository = raceRepository;
    }

    @Test
    void shouldReturnSixValues() {
        CharacterEntity character = initializeCharacter();
        PlayerEntity player = character.getPlayer();
        RaceEntity race = character.getRace();

        character.setId(1);
        List<CharacteristicValueEntity> values = initializeCharacteristicValues(character);
        List<CharacteristicEntity> characteristics = values.stream()
                .map(CharacteristicValueEntity::getCharacteristic)
                .toList();

        playerRepository.save(player);
        raceRepository.save(race);
        characteristicRepository.saveAll(characteristics);
        characterRepository.save(character);
        characteristicValueRepository.saveAll(values);

        assertThat(characteristicValueRepository.findAll()).hasSize(6);
    }


}

