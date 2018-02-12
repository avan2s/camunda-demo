package com.example.camunda.camundademo.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service("delegateLogTime")
public class DelegateLogTime implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(DelegateLogTime.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println("VARIABLES:\n");
        System.out.println(execution.getVariables());
        System.out.println("\n\nLOCAL VARIABLES:\n");
        System.out.println(execution.getVariableNamesLocal());

    }
}
