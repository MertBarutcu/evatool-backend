package com.evatool.impact.domain.event.stakeholder;

import com.evatool.global.event.stakeholder.StakeholderCreatedEvent;
import com.evatool.impact.application.json.mapper.ImpactStakeholderJsonMapper;
import com.evatool.impact.common.exception.EventEntityAlreadyExistsException;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ImpactStakeholderCreatedEventListener implements ApplicationListener<StakeholderCreatedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ImpactStakeholderCreatedEventListener.class);

    private final ImpactStakeholderRepository stakeholderRepository;

    public ImpactStakeholderCreatedEventListener(ImpactStakeholderRepository stakeholderRepository) {
        this.stakeholderRepository = stakeholderRepository;
    }

    /**
     * Inserts the stakeholder received in the event.
     *
     * @param event that was received.
     * @throws EventEntityAlreadyExistsException if a Stakeholder with that id already exists.
     */
    @Override
    public void onApplicationEvent(final StakeholderCreatedEvent event) {
        logger.info("Event received");
        var jsonPayload = event.getJsonPayload();
        var stakeholder = ImpactStakeholderJsonMapper.fromJson(jsonPayload);
        if (stakeholderRepository.existsById(stakeholder.getId())) {
            throw new EventEntityAlreadyExistsException(ImpactStakeholder.class.getSimpleName());
        }
        stakeholderRepository.save(stakeholder);
        logger.info("Event successfully processed");
    }
}
