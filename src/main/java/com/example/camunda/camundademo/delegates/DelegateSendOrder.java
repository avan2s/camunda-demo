package com.example.camunda.camundademo.delegates;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.model.dmn.instance.Variable;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by Andy on 05.09.2017.
 */
@Service("delegateSendOrder")
public class DelegateSendOrder implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("Send order...");
        Random random = new Random();
        int max = 10;
        int min = 1;
        int randomNumber = random.nextInt(max - min + 1) + min;

        if (randomNumber % 3 == 3) {
            throw new BpmnError("Error_OnlineOrderNotPossible");
        } else {
            RuntimeService runtimeService = delegateExecution.getProcessEngineServices().getRuntimeService();

            String businessKey = "BK-" + delegateExecution.getProcessInstanceId();
            VariableMap variablesForNewInstance = Variables.createVariables()
                    .putValue("desiredDish", delegateExecution.getVariable("desiredDish"))
                    .putValue("OrderNo", businessKey);
            runtimeService.correlateMessage("Message_order", businessKey, variablesForNewInstance);
        }

    }
}
