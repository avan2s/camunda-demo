package com.example.camunda.camundademo.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

/**
 * Created by Andy on 05.09.2017.
 */
@Service("delegateEatMeal")
public class DelegateEatMeal implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("Mahlzeit verspeist.");
    }
}
