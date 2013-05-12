package com.scoutin.utilities;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.scoutin.application.interfaces.AccountBeanRemote;
import com.scoutin.application.interfaces.AuthenticateBeanRemote;
import com.scoutin.application.interfaces.CardBeanRemote;
import com.scoutin.application.interfaces.AlbumBeanRemote;
import com.scoutin.application.interfaces.RecommendBeanRemote;

public class EJBUtils {
	private final static String module = "ejb:Scoutin/ScoutinEJB/";
	private final static String authenticatePath = module + "AuthenticateBean!"
			+ AuthenticateBeanRemote.class.getName();
	private final static String accountPath = module + "AccountBean!"
			+ AccountBeanRemote.class.getName();
	private final static String cardPath = module + "CardBean!"
			+ CardBeanRemote.class.getName();
	private final static String albumPath= module + "AlbumBean!"
			+ AlbumBeanRemote.class.getName();
	private final static String recommendPath= module + "RecommendBean!"
			+ RecommendBeanRemote.class.getName();
	
	static final Properties  jndiProps = new Properties();
	static {
//		jndiProps.put(InitialContext.INITIAL_CONTEXT_FACTORY,
//				"org.jboss.naming.remote.client.InitialContextFactory");
//		jndiProps.put(InitialContext.PROVIDER_URL,
//				"remote://localhost:4447");// create a context passing these properties
//		// username
//		jndiProps.put(InitialContext.SECURITY_PRINCIPAL, "user");
//		// password
//		jndiProps.put(InitialContext.SECURITY_CREDENTIALS, "12345");
		//jndiProps.put("jboss.naming.client.ejb.context", true);
		jndiProps.put(InitialContext.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");			
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
	
	public static final AuthenticateBeanRemote authenticateBeanRemote = (AuthenticateBeanRemote) EJBUtils.obtainBean(authenticatePath);
	public static final AccountBeanRemote accountBeanRemote = (AccountBeanRemote) EJBUtils.obtainBean(accountPath);
	public static final CardBeanRemote cardBeanRemote = (CardBeanRemote) EJBUtils.obtainBean(cardPath);
	public static final AlbumBeanRemote albumBeanRemote = (AlbumBeanRemote) EJBUtils.obtainBean(albumPath);
	public static final RecommendBeanRemote recommendBeanRemote = (RecommendBeanRemote) EJBUtils.obtainBean(recommendPath);
	

}
