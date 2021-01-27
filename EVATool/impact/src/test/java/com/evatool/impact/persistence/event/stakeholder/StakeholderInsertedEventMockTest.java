package com.evatool.impact.persistence.event.stakeholder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.evatool.impact.persistence.TestDataGenerator.getStakeholder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class StakeholderInsertedEventMockTest {
    @Autowired
    private StakeholderInsertedEventPublisher publisher;

    @MockBean
    private StakeholderInsertedEventListener listener;

    @Test
    public void testOnApplicationEvent_PublishEvent_ReceivePublishedEventOnce() throws InterruptedException {
        // given
        var stakeholder = getStakeholder();

        // when
        publisher.onStakeholderInserted(stakeholder);
        Thread.sleep(100);

        // then
        verify(listener, times(1)).onApplicationEvent(any(StakeholderInsertedEvent.class));
    }
}
