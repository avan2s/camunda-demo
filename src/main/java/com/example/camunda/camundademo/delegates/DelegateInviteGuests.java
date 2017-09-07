package com.example.camunda.camundademo.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Andy on 07.09.2017.
 */
@Service("delegateInviteGuests")
public class DelegateInviteGuests implements JavaDelegate {

    private static Logger logger = LoggerFactory.getLogger(DelegateInviteGuests.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        long guestsCount = (long) execution.getVariable("guestCount");
        for (int i = 1; i <= guestsCount; i++) {
            logger.info("Invite guest{}", i);
        }
    }
}
