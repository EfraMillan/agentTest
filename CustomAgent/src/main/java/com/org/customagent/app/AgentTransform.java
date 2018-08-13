package com.org.customagent.app;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.LocalVariableAttribute;

public class AgentTransform implements ClassFileTransformer {

	@Override
	public byte[] transform(final ClassLoader loader, final String className, final Class<?> classBeingRedefined,
			final ProtectionDomain protectionDomain, final byte[] classfileBuffer) throws IllegalClassFormatException {
		System.out.println("class : " + className);

		try {
			if (className.equals("com/org/testagent/app/App")) {
				String strClazz = className.replaceAll("/", ".");
				final ClassPool classPool = ClassPool.getDefault();
				final CtClass clazz = classPool.get(strClazz);

				System.out.println("---------------" + clazz.getName() + "-------------------------------");
				System.out.println("Fields : " + clazz.getFields().toString());
				System.out.println("package : " + clazz.getPackageName());
				System.out.println("Simple name : " + clazz.getSimpleName());

				final CtMethod[] existingMethods = clazz.getDeclaredMethods();
				for (int ctr = 0; ctr < existingMethods.length; ctr++) {
					final CtMethod method = existingMethods[ctr];
					AttributeInfo localVar = method.getMethodInfo().getAttribute(javassist.bytecode.LocalVariableAttribute.tag);
					System.out.println("Method long name : " + method.getLongName());
					System.out.println("Method name : " + method.getName());
					System.out.println("Method signature : " + method.getSignature());
					System.out.println("Method  parameters : " + method.getParameterTypes());
					CtClass[] parameters = method.getParameterTypes();
					for (int i = 0; i < parameters.length; i++) {
						final CtClass param = parameters[i];
						System.out.println("param class  : " + param.getName());
					}
				}

				System.out.println("----------------------------------------------------");

				byte[] byteCode = clazz.toBytecode();

				clazz.detach();

				return byteCode;
			}

		} catch (NotFoundException | IOException | CannotCompileException e) {
			System.out.println("Error : " + e.getMessage());
		}

		return null;
	}
}