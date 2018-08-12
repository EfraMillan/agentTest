package com.org.testagent.app;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.NotFoundException;

public class AgentTransform implements ClassFileTransformer {
	@Override
	public byte[] transform(final ClassLoader loader, final String className, final Class<?> classBeingRedefined,
			final ProtectionDomain protectionDomain, final byte[] classfileBuffer) throws IllegalClassFormatException {

		if (className.endsWith("sun/net/www/protocol/http/HttpURLConnection")) {
			try {
				final ClassPool classPool = ClassPool.getDefault();
				final CtClass clazz = classPool.get("sun.net.www.protocol.http.HttpURLConnection");

				for (final CtConstructor constructor : clazz.getConstructors()) {
					constructor.insertAfter("System.out.println(this.getURL());");
				}

				byte[] byteCode = clazz.toBytecode();
				clazz.detach();

				return byteCode;
			} catch (NotFoundException | CannotCompileException | IOException ex) {
				ex.printStackTrace();
			}
		}

		return null;
	}
}