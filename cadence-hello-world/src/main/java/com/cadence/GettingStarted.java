package com.cadence;

import com.uber.cadence.workflow.Workflow;
import com.uber.cadence.workflow.WorkflowMethod;
import org.slf4j.Logger;

public class GettingStarted {

    private static final Logger LOGGER = Workflow.getLogger(GettingStarted.class);

    public interface HelloWorld {
        @WorkflowMethod
        void sayHello(String name);
    }    
    public static class HelloWorldImpl implements HelloWorld {

        @Override
        public void sayHello(String name) {
        	LOGGER.info(String.format("Hello %s!",name));
        }
    }

}