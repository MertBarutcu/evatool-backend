package com.evatool.impact.domain.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.evatool.impact.TestDataGenerator.getImpact;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ImpactTest {
    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -0.5, 0.0, 0.5, 1.0})
    public void testSetValue_LegalValue_DoNotThrowException(double value) {
        // given
        var impact = getImpact();

        // when
        impact.setValue(value);

        // then
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Double.MAX_VALUE, -2.0, -1.5, -1.1, -1.000001, 1.000001, 1.1, 1.5, 2.0, Double.MAX_VALUE})
    public void testSetValue_IllegalValue_ThrowException(double value) {
        // given
        var impact = getImpact();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> impact.setValue(value));
    }

    @Test
    public void testSetDescription_NullValue_ThrowException() {
        // given
        var impact = getImpact();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> impact.setDescription(null));
    }

    @Test
    public void testSetDimension_NullValue_ThrowException() {
        // given
        var impact = getImpact();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> impact.setDimension(null));
    }

    @Test
    public void testSetStakeholder_NullValue_ThrowException() {
        // given
        var impact = getImpact();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> impact.setStakeholder(null));
    }
}
