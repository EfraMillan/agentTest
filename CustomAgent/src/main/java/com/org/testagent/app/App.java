package com.org.testagent.app;

import java.lang.instrument.Instrumentation;

/**
 * Hello world!
 *
 */
public class App {
		
    public static void premain(String agentArgs, Instrumentation inst) {
        final AgentTransform transformer = new AgentTransform();
        inst.addTransformer(transformer);
    }
	

}
