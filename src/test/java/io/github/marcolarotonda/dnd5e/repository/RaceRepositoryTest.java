package io.github.marcolarotonda.dnd5e.repository;

import io.github.marcolarotonda.dnd5e.RepositoryTest;
import io.github.marcolarotonda.dnd5e.entity.RaceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@Sql(value = {"/sql/tb/create_tb_race.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/tb/drop_tb_race.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class RaceRepositoryTest extends RepositoryTest {

    private final RaceRepository raceRepository;

    @Autowired
    RaceRepositoryTest(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Test
    void shouldReturn1RowWhenPersisting() {
        RaceEntity raceEntity = new RaceEntity();
        raceEntity.setName("Umano");
        raceEntity.setSize("M");
        raceEntity.setSpeed(9);

        raceRepository.save(raceEntity);

        List<RaceEntity> all = raceRepository.findAll();
        assertThat(all).hasSize(1);
    }

    @Test
    void shouldThrowDataIntegrityViolationWhenPersistingWithoutName() {
        RaceEntity raceEntity = new RaceEntity();
        raceEntity.setSize("M");
        raceEntity.setSpeed(9);

        assertThatThrownBy(() -> raceRepository.save(raceEntity)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void shouldThrowDataIntegrityViolationWhenPersistingWithoutSize() {
        RaceEntity raceEntity = new RaceEntity();
        raceEntity.setName("Umano");
        raceEntity.setSpeed(9);

        assertThatThrownBy(() -> raceRepository.save(raceEntity)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void shouldThrowDataIntegrityViolationWhenPersistingWithoutSpeed() {
        RaceEntity raceEntity = new RaceEntity();
        raceEntity.setName("Umano");
        raceEntity.setSize("M");

        assertThatThrownBy(() -> raceRepository.save(raceEntity)).isInstanceOf(DataIntegrityViolationException.class);
    }
}
