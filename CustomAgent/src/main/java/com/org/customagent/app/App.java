package com.org.customagent.app;

import java.lang.instrument.Instrumentation;

/**
 * Hello world!
 *
 */
public class App {
		
    public static void premain(String agentArgs, Instrumentation inst) {
        final AgentTransform transformer = new AgentTransform();
        inst.addTransformer(transformer);
        System.out.println("f4fg5g54gh45hg54h45h");
    }
	

}
