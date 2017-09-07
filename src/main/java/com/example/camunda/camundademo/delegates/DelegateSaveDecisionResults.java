package com.example.camunda.camundademo.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

/**
 * Created by Andy on 07.09.2017.
 */
@Service("delegateSaveDecisionResults")
public class DelegateSaveDecisionResults implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        execution.setVariable("supplier", execution.getVariable(""));
    }
}
