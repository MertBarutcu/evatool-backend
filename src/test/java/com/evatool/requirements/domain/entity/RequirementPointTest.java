package com.evatool.requirements.domain.entity;

import com.evatool.requirements.entity.RequirementDimension;
import com.evatool.requirements.entity.RequirementPoint;
import com.evatool.requirements.entity.RequirementsAnalysis;
import com.evatool.requirements.entity.RequirementsVariant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;

import static com.evatool.requirements.common.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class RequirementPointTest {

    @Test
    void testSetRequirement_NullValue_ThrowException() {
        // given
        RequirementDimension requirementDimension = getRequirementDimension();

        RequirementsAnalysis requirementsAnalysis = getRequirementsAnalysis();
        Collection<RequirementsVariant > requirementsVariants = getRequirementsVariants();

        RequirementPoint requirementPoint = getRequirementGR(requirementDimension,requirementsAnalysis,requirementsVariants);

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementPoint.setRequirement(null));
    }

    @Test
    void testSetRequirementsImpacts_NullValue_ThrowException() {
        // given
        RequirementDimension requirementDimension = getRequirementDimension();

        RequirementsAnalysis requirementsAnalysis = getRequirementsAnalysis();
        Collection<RequirementsVariant > requirementsVariants = getRequirementsVariants();

        RequirementPoint requirementPoint = getRequirementGR(requirementDimension,requirementsAnalysis,requirementsVariants);

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementPoint.setRequirementsImpact(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {2})
    void testSetValue_NullValue_ThrowException(int value) {
        // given
        RequirementDimension requirementDimension = getRequirementDimension();
        RequirementsAnalysis requirementsAnalysis = getRequirementsAnalysis();
        Collection<RequirementsVariant > requirementsVariants = getRequirementsVariants();

        RequirementPoint requirementPoint = getRequirementGR(requirementDimension,requirementsAnalysis,requirementsVariants);

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementPoint.setPoints(value));
    }
}
