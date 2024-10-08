package io.github.marcolarotonda.dnd5e.repository;

import io.github.marcolarotonda.dnd5e.RepositoryTest;
import io.github.marcolarotonda.dnd5e.entity.EnemyEntity;
import io.github.marcolarotonda.dnd5e.entity.Combatant;
import io.github.marcolarotonda.dnd5e.entity.EnemyTypeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static io.github.marcolarotonda.utlis.EntityInitializerUtils.initializeEnemyType;
import static org.assertj.core.api.Assertions.assertThat;

@Sql(value = {"/sql/tb/create_tb_enemy_type.sql",
        "/sql/tb/create_tb_enemy.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/tb/drop_tb_enemy.sql",
        "/sql/tb/drop_tb_enemy_type.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class EnemyRepositoryTest extends RepositoryTest {

    private final EnemyRepository enemyRepository;
    private final EnemyTypeRepository enemyTypeRepository;
    private EnemyTypeEntity enemyType;
    private EnemyEntity enemy;

    @Autowired
    EnemyRepositoryTest(EnemyRepository enemyRepository,
                        EnemyTypeRepository enemyTypeRepository) {
        this.enemyRepository = enemyRepository;
        this.enemyTypeRepository = enemyTypeRepository;
    }

    @BeforeEach
    void setup() {
        enemyType = initializeEnemyType();
        enemyTypeRepository.save(enemyType);
        enemy = new EnemyEntity();
        enemy.setEnemyType(enemyType);

    }

    @Test
    void shouldReturnOneEntity() {
        enemyRepository.save(enemy);

        List<EnemyEntity> all = enemyRepository.findAll();
        assertThat(all).hasSize(1);
    }

    @Test
    void shouldReturnOneCombatant() {
        enemyRepository.save(enemy);

        List<Combatant> allProjectedBy = enemyRepository.findAllProjectedByAliveTrue();
        assertThat(allProjectedBy).hasSize(1);
    }

    @Test
    void shouldNotReturnDeadEnemy() {
        EnemyEntity enemy1 = new EnemyEntity();
        enemy1.setEnemyType(enemyType);
        enemy1.setAlive(true);

        EnemyEntity enemy2 = new EnemyEntity();
        enemy2.setEnemyType(enemyType);
        enemy2.setAlive(false);

        enemyRepository.saveAll(List.of(enemy1, enemy2));

        List<EnemyEntity> allByAliveTrue = enemyRepository.findAllByAliveTrue();

        assertThat(allByAliveTrue).hasSize(1);
    }

    @Test
    void shouldNotReturnDeadCombatant() {
        EnemyEntity enemy1 = new EnemyEntity();
        enemy1.setEnemyType(enemyType);
        enemy1.setAlive(true);

        EnemyEntity enemy2 = new EnemyEntity();
        enemy2.setEnemyType(enemyType);
        enemy2.setAlive(false);

        enemyRepository.saveAll(List.of(enemy1, enemy2));

        List<Combatant> allProjectedByAliveTrue = enemyRepository.findAllProjectedByAliveTrue();

        assertThat(allProjectedByAliveTrue).hasSize(1);
    }

    @Test
    void saveEntityWithoutDamageTaken() {
        enemyRepository.save(enemy);
        assertThat(enemyRepository.findAll()).hasSize(1);
    }

    @Test
    void checkDefaultDamageTaken() {
        enemyRepository.save(enemy);
        Integer damageTaken = enemyRepository.findAll()
                .stream()
                .findFirst()
                .get()
                .getDamageTaken();
        assertThat(damageTaken).isZero();
    }

    @Test
    void shouldSaveEnemyType() {
        EnemyTypeEntity enemyType = new EnemyTypeEntity();
        enemyType.setName("new_enemy_type");
        EnemyEntity enemy = new EnemyEntity();
        enemy.setEnemyType(enemyType);

        List<EnemyTypeEntity> enemyTypesBeforeInsert = enemyTypeRepository.findAll();

        enemyRepository.save(enemy);

        List<EnemyTypeEntity> enemyTypesAfterInsert = enemyTypeRepository.findAll();

        assertThat(enemyTypesAfterInsert).hasSize(enemyTypesBeforeInsert.size() + 1);
    }

    @Test
    void shouldSaveOnlyOneEnemyType() {
        EnemyTypeEntity enemyType = new EnemyTypeEntity();
        enemyType.setName("new_enemy_type");
        EnemyEntity enemy1 = new EnemyEntity();
        enemy1.setEnemyType(enemyType);
        EnemyEntity enemy2 = new EnemyEntity();
        enemy2.setEnemyType(enemyType);

        List<EnemyTypeEntity> enemyTypesBeforeInsert = enemyTypeRepository.findAll();

        enemyRepository.saveAll(List.of(enemy1, enemy2));

        List<EnemyTypeEntity> enemyTypesAfterInsert = enemyTypeRepository.findAll();

        assertThat(enemyTypesAfterInsert).hasSize(enemyTypesBeforeInsert.size() + 1);
    }

}
