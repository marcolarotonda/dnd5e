package io.github.marcolarotonda.dnd5e.repository;

import io.github.marcolarotonda.dnd5e.RepositoryTest;
import io.github.marcolarotonda.dnd5e.entity.CharacteristicEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static io.github.marcolarotonda.utlis.EntityInitializerUtils.initializeCharacteristic;
import static org.assertj.core.api.Assertions.assertThat;


@Sql(value = {"/sql/tb/create_tb_characteristic.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/tb/drop_tb_characteristic.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CharacteristicRepositoryTest extends RepositoryTest {

    private final CharacteristicRepository characteristicRepository;

    @Autowired
    CharacteristicRepositoryTest(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    @Test
    void shouldReturn6Rows() {
        List<CharacteristicEntity> characteristics = initializeCharacteristic();
        characteristicRepository.saveAll(characteristics);

        List<CharacteristicEntity> all = characteristicRepository.findAll();
        assertThat(all).hasSize(6);
    }

}
