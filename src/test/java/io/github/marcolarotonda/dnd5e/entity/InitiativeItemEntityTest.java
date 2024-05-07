package io.github.marcolarotonda.dnd5e.entity;

import io.github.marcolarotonda.dnd5e.UnitTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InitiativeItemEntityTest extends UnitTest {

    @Test
    void shouldSetDefaultTag() {
        InitiativeItemEntity build = InitiativeItemEntity.builder().build();
        assertThat(build.getTag()).isNull();
    }

}
