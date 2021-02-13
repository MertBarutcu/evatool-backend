package com.evatool.impact.domain.entity;

import com.evatool.impact.common.exception.InvalidUuidException;
import com.evatool.impact.common.exception.PropertyViolationException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class SuperEntityTest {

    @Test
    void testCreateEntity_CreatedSuperEntity_IdIsNull() {
        // given
        var superEntity = getSuperEntity();

        // when

        // then
        assertThat(superEntity.getId()).isNull();
    }

    @Test
    void testSetIdUuid_ValidToNullValue_ThrowPropertyViolationException() {
        // given
        var superEntity = getSuperEntity();

        // when
        superEntity.setId(UUID.randomUUID());

        // then
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> superEntity.setId((UUID) null));
    }

    @Test
    void testSetIdUuid_IllegalValueSequence_ThrowPropertyViolationException() {
        // given
        var superEntity = getSuperEntity();

        // when
        superEntity.setId(UUID.randomUUID());

        // then
        var id = UUID.randomUUID();
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> superEntity.setId(id));
    }

    @Test
    void testSetIdString_ValidToNullValue_ThrowPropertyViolationException() {
        // given
        var superEntity = getSuperEntity();

        // when
        superEntity.setId(UUID.randomUUID());

        // then
        assertThatExceptionOfType(InvalidUuidException.class).isThrownBy(() -> superEntity.setId((String) null));
    }

    @Test
    void testSetIdString_IllegalValueSequence_ThrowPropertyViolationException() {
        // given
        var superEntity = getSuperEntity();

        // when
        superEntity.setId(UUID.randomUUID());

        // then
        var id = UUID.randomUUID().toString();
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> superEntity.setId(id));
    }

    private SuperEntityImpl getSuperEntity() {
        return new SuperEntityImpl();
    }

    private static class SuperEntityImpl extends SuperEntity {

    }
}
