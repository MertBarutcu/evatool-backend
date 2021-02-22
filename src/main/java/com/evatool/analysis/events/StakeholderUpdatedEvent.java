package com.evatool.analysis.events;

import com.evatool.analysis.dto.StakeholderDTO;
import com.evatool.analysis.model.Stakeholder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class StakeholderUpdatedEvent extends ApplicationEvent {
    private StakeholderDTO dto;

    public StakeholderUpdatedEvent(StakeholderDTO dto) {
        super(dto);
        this.dto = dto;
    }

    public StakeholderDTO getValue(){
        return this.dto;
    }
}
