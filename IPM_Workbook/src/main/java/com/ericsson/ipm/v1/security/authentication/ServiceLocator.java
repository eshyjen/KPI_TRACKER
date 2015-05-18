/**
 * 
 */
package com.ericsson.ipm.v1.security.authentication;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.context.util.LogUtil;

/**
 * @author ihkhan
 * 
 */
public class ServiceLocator {
	private static final Log log = LogFactory.getLog(ServiceLocator.class);

	private ServiceLocator() {
	}

	public static Object getEJB(final String jndiName) {
		Object object = null;
		try {
			InitialContext ctx = new InitialContext();
			object = ctx.lookup(jndiName);
		} catch (Exception e) {
			//log.error("Lookup failed for EJB " + jndiName + " stack trace:: "
					//+ LogUtil.getStackTrace(e));
		}
		return object;
	}
}
