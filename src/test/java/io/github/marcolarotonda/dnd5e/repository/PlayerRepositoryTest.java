package io.github.marcolarotonda.dnd5e.repository;

import io.github.marcolarotonda.dnd5e.RepositoryTest;
import io.github.marcolarotonda.dnd5e.entity.PlayerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@Sql(value = {"/sql/tb/create_tb_player.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/tb/drop_tb_player.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class PlayerRepositoryTest extends RepositoryTest {

    private final PlayerRepository playerRepository;

    @Autowired
    PlayerRepositoryTest(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Test
    void shouldReturn1RowWhenPersisting() {
        PlayerEntity player = new PlayerEntity();
        player.setName("Pippo");
        playerRepository.save(player);

        List<PlayerEntity> all = playerRepository.findAll();
        assertThat(all).hasSize(1);
    }

    @Test
    void shouldThrowDataIntegrityViolationWhenPersistingWithoutName() {
        PlayerEntity player = new PlayerEntity();

        assertThatThrownBy(() -> playerRepository.save(player)).isInstanceOf(DataIntegrityViolationException.class);
    }

}
