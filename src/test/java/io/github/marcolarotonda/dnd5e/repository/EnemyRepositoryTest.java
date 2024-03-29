package io.github.marcolarotonda.dnd5e.repository;

import io.github.marcolarotonda.dnd5e.RepositoryTest;
import io.github.marcolarotonda.dnd5e.entity.EnemyEntity;
import io.github.marcolarotonda.dnd5e.entity.Combatant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static io.github.marcolarotonda.utlis.EntityInitializerUtils.initializeEnemy;
import static org.assertj.core.api.Assertions.assertThat;

@Sql(value = "/sql/tb/create_tb_enemy.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql/tb/drop_tb_enemy.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class EnemyRepositoryTest extends RepositoryTest {

    private final EnemyRepository enemyRepository;

    @Autowired
    EnemyRepositoryTest(EnemyRepository enemyRepository) {
        this.enemyRepository = enemyRepository;
    }

    @Test
    void shouldReturnOneEntity() {
        EnemyEntity enemyEntity = initializeEnemy();
        enemyRepository.save(enemyEntity);

        List<EnemyEntity> all = enemyRepository.findAll();
        assertThat(all).hasSize(1);
    }

    @Test
    void shouldReturnOneCombatant() {
        EnemyEntity enemyEntity = initializeEnemy();
        enemyRepository.save(enemyEntity);

        List<Combatant> allProjectedBy = enemyRepository.findAllProjectedBy();
        assertThat(allProjectedBy).hasSize(1);
    }

    @Test
    void saveEntityWithoutName() {
        enemyRepository.save(new EnemyEntity());
        assertThat(enemyRepository.findAll()).hasSize(1);
    }

    @Test
    void checkDefaultName() {
        enemyRepository.save(new EnemyEntity());
        String name = enemyRepository.findAll().stream().findFirst().get().getName();
        assertThat(name).isEqualTo("generic_enemy");
    }

    @Test
    void saveEntityWithoutInitiativeBonus() {
        enemyRepository.save(new EnemyEntity());
        assertThat(enemyRepository.findAll()).hasSize(1);
    }

    @Test
    void checkDefaultInitiativeBonus() {
        enemyRepository.save(new EnemyEntity());
        Integer initiativeBonus = enemyRepository.findAll().stream().findFirst().get().getInitiativeBonus();
        assertThat(initiativeBonus).isEqualTo(0);
    }

    @Test
    void saveEntityWithoutDamageTaken() {
        enemyRepository.save(new EnemyEntity());
        assertThat(enemyRepository.findAll()).hasSize(1);
    }

    @Test
    void checkDefaultDamageTaken() {
        enemyRepository.save(new EnemyEntity());
        Integer damageTaken = enemyRepository.findAll().stream().findFirst().get().getDamageTaken();
        assertThat(damageTaken).isEqualTo(0);
    }

}
