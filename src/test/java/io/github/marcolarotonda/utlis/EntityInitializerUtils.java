package io.github.marcolarotonda.utlis;

import io.github.marcolarotonda.dnd5e.entity.*;
import io.github.marcolarotonda.dnd5e.enumeration.CharacteristicEnum;

import java.util.ArrayList;
import java.util.List;

public class EntityInitializerUtils {

    public static PlayerEntity initializePlayer() {
        PlayerEntity player = new PlayerEntity();
        player.setName("Franco");
        return player;
    }

    public static RaceEntity initializeRace() {
        RaceEntity race = new RaceEntity();
        race.setName("Umano");
        race.setSize("M");
        race.setSpeed(9);
        return race;
    }

    public static EnemyEntity initializeEnemy() {
        return initializeEnemy(5);
    }

    public static EnemyTypeEntity initializeEnemyType() {
        return initializeEnemyType("Nemico", 5);
    }

    public static EnemyTypeEntity initializeEnemyType(int initiativeBonus) {
        return initializeEnemyType("Nemico", initiativeBonus);
    }

    public static EnemyTypeEntity initializeEnemyType(String name, int initiativeBonus) {
        EnemyTypeEntity enemyTypeEntity = new EnemyTypeEntity();
        enemyTypeEntity.setName(name);
        enemyTypeEntity.setInitiativeModifier(initiativeBonus);
        return enemyTypeEntity;
    }

    public static EnemyEntity initializeEnemy(int initiativeBonus) {
        EnemyTypeEntity enemyType = initializeEnemyType(initiativeBonus);
        return initializeEnemy(enemyType);
    }

    public static EnemyEntity initializeEnemy(EnemyTypeEntity enemyType) {
        EnemyEntity enemyEntity = new EnemyEntity();
        enemyEntity.setEnemyType(enemyType);
        enemyEntity.setDamageTaken(2);

        return enemyEntity;
    }

    public static CharacterEntity initializeCharacter() {
        return initializeCharacter(null, 0);
    }

    public static CharacterEntity initializeCharacter(int initiativeBonus) {
        return initializeCharacter(null, initiativeBonus);
    }

    public static CharacterEntity initializeCharacter(CharacterField fieldToIgnore) {
        return initializeCharacter(fieldToIgnore, 0);
    }

    public static CharacterEntity initializeCharacter(CharacterField fieldToIgnore, int initiativeBonus) {
        CharacterEntity character = new CharacterEntity();
        if (fieldToIgnore != CharacterField.PLAYER) {
            character.setPlayer(initializePlayer());
        }
        if (fieldToIgnore != CharacterField.RACE) {
            character.setRace(initializeRace());
        }
        if (fieldToIgnore != CharacterField.PASSIVE_PERCEPTION) {
            character.setPassivePerception(10);
        }
        if (fieldToIgnore != CharacterField.ALIVE) {
            character.setAlive(true);
        }
        if (fieldToIgnore != CharacterField.INITIATIVE_BONUS) {
            character.setInitiativeBonus(initiativeBonus);
        }
        if (fieldToIgnore != CharacterField.COMPETENCE_BONUS) {
            character.setCompetenceBonus(2);
        }
        if (fieldToIgnore != CharacterField.CLASS_ARMOR) {
            character.setClassArmor(15);
        }
        if (fieldToIgnore != CharacterField.SPEED) {
            character.setSpeed(9);
        }
        if (fieldToIgnore != CharacterField.NAME) {
            character.setName("CiccioPizza");
        }
        return character;
    }

    public static List<CharacteristicEntity> initializeCharacteristic() {
        return new ArrayList<>(List.of(initializeStrength(),
                initializeDexterity(),
                initializeConstitution(),
                initializeIntelligence(),
                initializeWisdom(),
                initializeCharisma()));
    }

    public static CharacteristicEntity initializeStrength() {
        CharacteristicEntity strength = new CharacteristicEntity();
        strength.setId(1);
        strength.setName(CharacteristicEnum.STRENGTH);
        return strength;
    }

    public static CharacteristicEntity initializeDexterity() {
        CharacteristicEntity dexterity = new CharacteristicEntity();
        dexterity.setId(2);
        dexterity.setName(CharacteristicEnum.DEXTERITY);
        return dexterity;
    }

    public static CharacteristicEntity initializeConstitution() {
        CharacteristicEntity constitution = new CharacteristicEntity();
        constitution.setId(3);
        constitution.setName(CharacteristicEnum.CONSTITUTION);
        return constitution;
    }

    public static CharacteristicEntity initializeIntelligence() {
        CharacteristicEntity intelligence = new CharacteristicEntity();
        intelligence.setId(4);
        intelligence.setName(CharacteristicEnum.INTELLIGENCE);
        return intelligence;
    }

    public static CharacteristicEntity initializeWisdom() {
        CharacteristicEntity wisdom = new CharacteristicEntity();
        wisdom.setId(5);
        wisdom.setName(CharacteristicEnum.WISDOM);
        return wisdom;
    }

    public static CharacteristicEntity initializeCharisma() {
        CharacteristicEntity charisma = new CharacteristicEntity();
        charisma.setId(6);
        charisma.setName(CharacteristicEnum.CHARISMA);
        return charisma;
    }

    public static List<CharacteristicValueEntity> initializeCharacteristicValues(CharacterEntity character) {
        CharacteristicValueEntity strength = new CharacteristicValueEntity();
        strength.setCharacter(character);
        strength.setCharacteristic(initializeStrength());
        strength.setPermanentValue(8);
        strength.setTemporaryValue(7);

        CharacteristicValueEntity dexterity = new CharacteristicValueEntity();
        dexterity.setCharacter(character);
        dexterity.setCharacteristic(initializeDexterity());
        dexterity.setPermanentValue(10);
        dexterity.setTemporaryValue(9);

        CharacteristicValueEntity constitution = new CharacteristicValueEntity();
        constitution.setCharacter(character);
        constitution.setCharacteristic(initializeConstitution());
        constitution.setPermanentValue(12);
        constitution.setTemporaryValue(11);

        CharacteristicValueEntity intelligence = new CharacteristicValueEntity();
        intelligence.setCharacter(character);
        intelligence.setCharacteristic(initializeIntelligence());
        intelligence.setPermanentValue(14);
        intelligence.setTemporaryValue(13);

        CharacteristicValueEntity wisdom = new CharacteristicValueEntity();
        wisdom.setCharacter(character);
        wisdom.setCharacteristic(initializeWisdom());
        wisdom.setPermanentValue(16);
        wisdom.setTemporaryValue(15);

        CharacteristicValueEntity charisma = new CharacteristicValueEntity();
        charisma.setCharacter(character);
        charisma.setCharacteristic(initializeCharisma());
        charisma.setPermanentValue(19);
        charisma.setTemporaryValue(17);

        return new ArrayList<>(List.of(strength, dexterity, constitution, intelligence, wisdom, charisma));
    }


    public enum CharacterField {
        PLAYER, RACE, PASSIVE_PERCEPTION, ALIVE, INITIATIVE_BONUS, COMPETENCE_BONUS, CLASS_ARMOR, SPEED, NAME
    }
}
