package io.github.marcolarotonda.dnd5e.repository;

import io.github.marcolarotonda.dnd5e.RepositoryTest;
import io.github.marcolarotonda.dnd5e.entity.EnemyEntity;
import io.github.marcolarotonda.dnd5e.entity.EnemyTypeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static io.github.marcolarotonda.utlis.EntityInitializerUtils.initializeEnemy;
import static org.assertj.core.api.Assertions.assertThat;

@Sql(value = {"/sql/tb/create_tb_enemy_type.sql",
        "/sql/tb/create_tb_enemy.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/tb/drop_tb_enemy.sql",
        "/sql/tb/drop_tb_enemy_type.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class EnemyTypeRepositoryTest extends RepositoryTest {

    private final EnemyTypeRepository enemyTypeRepository;
    private final EnemyRepository enemyRepository;
    private EnemyTypeEntity enemyType;

    @Autowired
    EnemyTypeRepositoryTest(EnemyTypeRepository enemyTypeRepository,
                            EnemyRepository enemyRepository) {
        this.enemyTypeRepository = enemyTypeRepository;
        this.enemyRepository = enemyRepository;
    }

    @BeforeEach
    void setup() {
        enemyType = new EnemyTypeEntity();
    }

    @Test
    void shouldReturnOneEnemyType() {
        enemyTypeRepository.save(enemyType);

        List<EnemyTypeEntity> all = enemyTypeRepository.findAll();
        assertThat(all).hasSize(1);
    }

    @Test
    void checkDefaultName() {
        enemyTypeRepository.save(enemyType);

        String defaultName = enemyTypeRepository.findById(enemyType.getId())
                .get()
                .getName();
        assertThat(defaultName).isEqualTo("generic_enemy");
    }

    @Test
    void checkDefaultInitiativeBonus() {
        enemyTypeRepository.save(enemyType);

        Integer defaultInitiativeBonus = enemyTypeRepository.findById(enemyType.getId())
                .get()
                .getInitiativeModifier();
        assertThat(defaultInitiativeBonus).isZero();
    }

    @Test
    void shouldReturnTwoTypes() {
        EnemyTypeEntity enemyType1 = new EnemyTypeEntity();
        enemyType1.setName("goblin");
        enemyType1.setInitiativeModifier(2);

        EnemyTypeEntity enemyType2 = new EnemyTypeEntity();
        enemyType1.setName("troll");
        enemyType1.setInitiativeModifier(0);

        EnemyEntity enemy1 = initializeEnemy(enemyType1);
        EnemyEntity enemy2 = initializeEnemy(enemyType1);
        EnemyEntity enemy3 = initializeEnemy(enemyType2);

        enemyTypeRepository.saveAll(List.of(enemyType1, enemyType2));
        enemyRepository.saveAll(List.of(enemy1, enemy2, enemy3));

        List<EnemyTypeEntity> distinctTypes = enemyTypeRepository.findDistinctTypes();
        assertThat(distinctTypes).hasSize(2);
    }

    @Test
    @Tag("CurrentTest")
    void shouldSaveEnemyTypeWithNegativeInitiativeModifier() {
        enemyType.setInitiativeModifier(-2);
        enemyTypeRepository.save(enemyType);
        EnemyTypeEntity enemyTypeSaved = enemyTypeRepository.findById(enemyType.getId()).get();
        assertThat(enemyTypeSaved.getInitiativeModifier()).isEqualTo(-2);

    }

}
