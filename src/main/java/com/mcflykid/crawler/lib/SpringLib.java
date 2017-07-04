package com.mcflykid.crawler.lib;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public final class SpringLib {

	public static void closeContext(ApplicationContext context) {

		((AbstractApplicationContext) context).close();
	}

	public static ApplicationContext loadContextFromClasses(Class<?>... annotatedClasses) {
		return new AnnotationConfigApplicationContext(annotatedClasses);
	}
}
