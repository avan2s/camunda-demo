package com.example.camunda.camundademo.delegates;

import java.util.Map;
import java.util.Random;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Andy on 07.09.2017.
 */
@Service("delegateOrder")
public class DelegateOrder implements JavaDelegate {
	private Logger logger = LoggerFactory.getLogger(DelegateOrder.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String meal = (String) execution.getVariable("decDish");
		Map<String,Object> decSupplier = (Map<String,Object>) execution.getVariable("decSupplier");
		
		logger.info("Order meal '{}' from supplier '{}'", meal, decSupplier.get("supplier"));

		// simulate an business error, we want to handle
		RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();

		// create a new instance with with an order id
		String orderId = "BK" + execution.getProcessInstanceId();
		// set the orderId
		execution.setVariable("orderId", orderId);

		VariableMap variablesForNewInstance = Variables.createVariables()
				// the dish you want
				.putValue("meal", meal).putValue("orderId", orderId);

		// alternative way
		// runtimeService.createMessageCorrelation("messageName").

		// this way by create a process instance
		runtimeService.startProcessInstanceByMessage("Message_order", variablesForNewInstance);

	}
}
