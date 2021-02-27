package com.evatool.requirements.domain.event;

import com.evatool.global.event.dimension.DimensionCreatedEvent;
import com.evatool.requirements.entity.RequirementDimension;
import com.evatool.requirements.error.exceptions.EventEntityAlreadyExistsException;
import com.evatool.requirements.error.exceptions.InvalidEventPayloadException;
import com.evatool.requirements.events.listener.RequirementEventListener;
import com.evatool.requirements.repository.RequirementDimensionRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@ActiveProfiles(profiles = "non-async")
public class RequirementDimensionCreateEventListener {

    @Autowired
    private RequirementDimensionRepository requirementDimensionRepository;

    @Autowired
    private RequirementEventListener requirementEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    void testOnApplicationEvent_PublishEvent_DimensionCreated() {
        // given
        UUID id = UUID.randomUUID();
        String  title = "name";
        String json = String.format("{\"id\":\"%s\",\"title\":\"%s\"}", id.toString(), title);

        // when
        DimensionCreatedEvent dimensionCreatedEvent = new DimensionCreatedEvent(applicationEventPublisher, json);
        applicationEventPublisher.publishEvent(dimensionCreatedEvent);

        // then
        Optional<RequirementDimension> createdByEvent = requirementDimensionRepository.findById(id);
        assertThat(createdByEvent).isPresent();
        assertThat(createdByEvent.get().getTitle()).isEqualTo(title);
    }


    @Test
    void testOnApplicationEvent_DimensionAlreadyExists_ThrowEventEntityAlreadyExistsException() {

        // given
        UUID id = UUID.randomUUID();
        String title = "title";
        String json = String.format("{\"id\":\"%s\",\"title\":\"%s\"}", id.toString(), title);

        RequirementDimension requirementDimension;

        try {
            var jsonObject = new JSONObject(json);
            requirementDimension = new RequirementDimension();
            requirementDimension.setTitle(jsonObject.getString("title"));
            requirementDimension.setId(UUID.fromString(jsonObject.getString("id")));
        } catch (JSONException jex) {
            throw new InvalidEventPayloadException(json, jex);
        }

        requirementDimensionRepository.save(requirementDimension);

        // when
        DimensionCreatedEvent dimensionCreatedEvent = new DimensionCreatedEvent(applicationEventPublisher, json);

        // then
        assertThatExceptionOfType(EventEntityAlreadyExistsException.class).isThrownBy(() -> applicationEventPublisher.publishEvent(dimensionCreatedEvent));

    }

}
