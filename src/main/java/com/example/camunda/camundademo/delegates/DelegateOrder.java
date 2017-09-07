package com.example.camunda.camundademo.delegates;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by Andy on 07.09.2017.
 */
@Service("delegateOrder")
public class DelegateOrder implements JavaDelegate {
    private Logger logger = LoggerFactory.getLogger(DelegateOrder.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        logger.info("Try to order");
        Random random = new Random();
        int max = 10;
        int min = 1;
        int randomNumber = random.nextInt(max - min + 1) + min;

        // simulate an business error, we want to handle
        if (randomNumber % 3 == 3) {
            throw new BpmnError("Error_OnlineOrderNotPossible");
        } else {
            RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();

            // create a new instance with with an order id
            String orderId = "BK" + execution.getProcessInstanceId();
            // set the orderId
            execution.setVariable("orderId", orderId);

            VariableMap variablesForNewInstance = Variables.createVariables()
                    // the dish you want
                    .putValue("meal", execution.getVariable("meal"))
                    .putValue("orderId", orderId);


            // alternative way
            // runtimeService.createMessageCorrelation("messageName").

            // this way by create a process instance
            runtimeService.startProcessInstanceByMessage("Message_order", variablesForNewInstance);
        }

    }
}
