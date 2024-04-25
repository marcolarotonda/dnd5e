package io.github.marcolarotonda.dnd5e.model;

import io.github.marcolarotonda.dnd5e.UnitTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InitiativeItemTest extends UnitTest {

    @Test
    void shouldSetDefaultTag() {
        InitiativeItem build = InitiativeItem.builder().build();
        assertThat(build.getTag()).isNull();
    }

}
