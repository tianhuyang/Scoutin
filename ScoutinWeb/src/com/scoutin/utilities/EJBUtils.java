package com.scoutin.utilities;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EJBUtils {
	static Properties  jndiProps = new Properties();
	static {
		jndiProps.put(InitialContext.INITIAL_CONTEXT_FACTORY,
				"org.jboss.naming.remote.client.InitialContextFactory");
		jndiProps.put(InitialContext.PROVIDER_URL,
				"remote://localhost:4447");// create a context passing these properties
		// username
		jndiProps.put(InitialContext.SECURITY_PRINCIPAL, "user");
		// password
		jndiProps.put(InitialContext.SECURITY_CREDENTIALS, "12345");
		jndiProps.put("jboss.naming.client.ejb.context", true);
		//jndiProps.put(InitialContext.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");			
	}
	public static Object obtainBean(String beanName)
	{
		Object bean=null;
		try {
			InitialContext ctx = new InitialContext(jndiProps);
			bean = ctx.lookup(beanName);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}
}
