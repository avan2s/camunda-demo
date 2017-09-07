package com.example.camunda.camundademo.delegates;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Andy on 05.09.2017.
 */
@Service("delegateSendOrder")
public class DelegateSendOrder implements JavaDelegate {

    private Logger logger = LoggerFactory.getLogger(DelegateSendOrder.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        logger.info("Try to send order to customer");
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        String orderId = (String) execution.getVariable("orderId");

        // inform the instance, which has ordered the meal
        MessageCorrelationResult messageCorrelationResult = runtimeService.createMessageCorrelation("MessageOrderedMeal")
                .processInstanceVariableEquals("orderId", orderId).correlateWithResult();
    }
}
