package com.example.camunda.camundademo.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Andy on 05.09.2017.
 */
@Service("delegateEatMeal")
public class DelegateEatMeal implements JavaDelegate {
    Logger logger = LoggerFactory.getLogger(DelegateEatMeal.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String supplier = (String)execution.getVariable("supplier");
        String desiredDish = (String)execution.getVariable("desiredDish");

        logger.info("Mahlzeit {} von Lieferant {} verspeist!", desiredDish, supplier);
    }
}
